#!/usr/bin/env bash
# spoj.sh - spojuva zadaca + site klasi sto gi koristi od src/ vo EDEN .java fajl,
#           bez package i bez mk.ukim.finki importi (za prakjanje na pregled / sistem).
#
#   bash spoj.sh lab 1 3            -> labs/lab01/task3
#   bash spoj.sh aud 2 1            -> aud/aud02/ex1
#   bash spoj.sh moi courses/voved/zad1
#   bash spoj.sh ispit jan2025/zad1
#   bash spoj.sh <pateka do .java fajl ili papka>
#
#   -p  go pecati kodot i vo terminalot
#
# Nisto ne se zacuvuva vo proektot: kodot odi vo clipboard (Ctrl+V),
# a ako nema clipboard alatka, se pecati vo terminalot.

ROOT="$(cd "$(dirname "$0")" && pwd)"
exec python3 - "$ROOT" "$@" <<'PY'
import os, re, sys, shutil, subprocess

root = sys.argv[1]
args = sys.argv[2:]
show = "-p" in args
args = [a for a in args if a != "-p"]
SRC = os.path.join(root, "src")
BASE = os.path.join(SRC, "mk", "ukim", "finki")

def usage():
    with open(os.path.join(root, "spoj.sh")) as f:
        lines = f.read().splitlines()[1:]
    lines = lines[:next(i for i, l in enumerate(lines) if not l.startswith("#"))]
    print("\n".join(l[2:] if l.startswith("# ") else l.lstrip("#") for l in lines))
    sys.exit(1)

# ---------- najdi ja zadacata ----------
def pad(n): return "%02d" % int(n)
if not args: usage()
kind = args[0]
if kind == "lab" and len(args) >= 3:   target = os.path.join(BASE, "labs", "lab" + pad(args[1]), "task" + args[2])
elif kind == "aud" and len(args) >= 3: target = os.path.join(BASE, "aud", "aud" + pad(args[1]), "ex" + args[2])
elif kind == "moi" and len(args) >= 2: target = os.path.join(BASE, "moi", args[1])
elif kind == "ispit" and len(args) >= 2: target = os.path.join(BASE, "ispiti", args[1])
elif kind in ("lab", "aud", "moi", "ispit"): usage()
else: target = os.path.abspath(kind)

if os.path.isdir(target):
    files = sorted(os.path.join(target, f) for f in os.listdir(target) if f.endswith(".java"))
    mains = [f for f in files if re.search(r"static\s+void\s+main\s*\(", open(f, encoding="utf-8").read())]
    if not mains:
        sys.exit("Nema fajl so main() vo: " + os.path.relpath(target, root))
    if len(mains) > 1:
        sys.exit("Povekje fajlovi so main(), kazi koj:\n  " + "\n  ".join(os.path.relpath(m, root) for m in mains))
    start = mains[0]
elif os.path.isfile(target) and target.endswith(".java"):
    start = target
else:
    sys.exit("Ne postoi: " + os.path.relpath(target, root))

# ---------- pomosni ----------
def strip_comments(code):
    """Gi trga komentarite, gi cuva stringovite i char-ovite."""
    out, i, n = [], 0, len(code)
    while i < n:
        c = code[i]
        if code.startswith("//", i):
            j = code.find("\n", i)
            i = n if j < 0 else j
        elif code.startswith("/*", i):
            j = code.find("*/", i + 2)
            seg = code[i:n if j < 0 else j + 2]
            out.append("\n" * seg.count("\n"))
            i = n if j < 0 else j + 2
        elif c in "\"'":
            j = i + 1
            while j < n and code[j] != c:
                j += 2 if code[j] == "\\" else 1
            out.append(code[i:j + 1]); i = j + 1
        else:
            out.append(c); i += 1
    return "".join(out)

def pkg_of(code):
    m = re.search(r"^\s*package\s+([\w.]+)\s*;", code, re.M)
    return m.group(1) if m else ""

def pkg_dir(pkg):
    return os.path.join(SRC, *pkg.split(".")) if pkg else None

def java_files(d):
    if not d or not os.path.isdir(d): return []
    return [os.path.join(d, f) for f in sorted(os.listdir(d)) if f.endswith(".java")]

def read(p):
    with open(p, encoding="utf-8") as f: return f.read()

def own_pkg(pkg):
    return pkg.startswith("mk.ukim.finki")

# ---------- sobiranje na zavisnosti ----------
start_dir = os.path.dirname(start)
order, seen, queue = [], set(), [start]
while queue:
    f = queue.pop(0)
    f = os.path.abspath(f)
    if f in seen: continue
    seen.add(f); order.append(f)
    code = read(f)
    clean = strip_comments(code)
    pkg = pkg_of(code)
    # importi od src/
    for m in re.finditer(r"^\s*import\s+(static\s+)?([\w.]+?)(\.\*)?\s*;", clean, re.M):
        name, star = m.group(2), m.group(3)
        if not own_pkg(name): continue
        if m.group(1):           # import static a.b.Klasa.metod
            name = name.rsplit(".", 1)[0]
        if star:
            queue += java_files(pkg_dir(name))
        else:
            p = os.path.join(SRC, *name.split(".")) + ".java"
            if os.path.isfile(p): queue.append(p)
    # klasi od istiot paket se koristat bez import
    for other in java_files(pkg_dir(pkg)):
        cls = os.path.basename(other)[:-5]
        if os.path.abspath(other) not in seen and re.search(r"\b%s\b" % re.escape(cls), clean):
            queue.append(other)

# ---------- spojuvanje ----------
java_imports, bodies, public_cls = [], [], None
for f in order:
    code = read(f)
    is_lib = os.path.dirname(f) != start_dir   # klasi od drugi papki (dataStructures...)
    if is_lib:
        code = strip_comments(code)
    keep = []
    for line in code.splitlines():
        s = line.strip()
        if re.match(r"package\s+[\w.]+\s*;", s):
            continue
        m = re.match(r"import\s+(static\s+)?([\w.]+?)(\.\*)?\s*;", s)
        if m:
            if not own_pkg(m.group(2)) and s not in java_imports:
                java_imports.append(s)
            continue
        if f != start:
            line = re.sub(r"^public\s+((?:(?:abstract|final|sealed)\s+)*(?:class|interface|enum|record)\b)", r"\1", line)
        keep.append(line)
    text = "\n".join(keep).strip("\n")
    text = re.sub(r"\n[ \t]*\n([ \t]*\n)+", "\n\n", text)      # bez prazni redovi po red
    if f == start:
        m = re.search(r"^public\s+(?:(?:abstract|final)\s+)*(?:class|interface|enum)\s+(\w+)", text, re.M)
        public_cls = m.group(1) if m else "Main"
    bodies.append((f, text))

# zadacata prva, posle drugite od istata papka, posle bibliotekite
bodies.sort(key=lambda t: (t[0] != start, os.path.dirname(t[0]) != start_dir))
result = ""
if java_imports:
    result += "\n".join(java_imports) + "\n\n"
result += "\n\n".join(b for _, b in bodies) + "\n"

# ---------- proverka: kompajliraj vo privremena papka, pa izbrisi ----------
import tempfile
compiled = None
if shutil.which("javac"):
    with tempfile.TemporaryDirectory() as tmp:
        p = os.path.join(tmp, public_cls + ".java")
        with open(p, "w", encoding="utf-8") as f:
            f.write(result)
        r = subprocess.run(["javac", "-nowarn", "-d", tmp, p], capture_output=True, text=True)
        compiled = r.returncode == 0
        errors = "\n".join(l for l in r.stderr.splitlines() if "JAVA_TOOL_OPTIONS" not in l)

# ---------- clipboard ----------
copied = None
for cmd in (["wl-copy"], ["xclip", "-selection", "clipboard"], ["xsel", "--clipboard", "--input"]):
    if shutil.which(cmd[0]):
        try:
            subprocess.run(cmd, input=result.encode("utf-8"), check=True, timeout=5)
            copied = cmd[0]; break
        except Exception:
            pass

if show or not copied:
    print(result)
    print("-" * 60)
print("Spoeni %d fajla:" % len(order))
for f in order:
    print("  " + os.path.relpath(f, SRC))
if compiled is True:
    print("Se kompajlira kako eden fajl.")
elif compiled is False:
    print("NE se kompajlira kako eden fajl:\n" + errors)
if copied:
    print("Kopirano vo clipboard - samo Ctrl+V. Nisto ne e zacuvano.")
else:
    print("Kopiraj go kodot od gore. (Za avtomatski clipboard: sudo apt install xclip)")
PY
