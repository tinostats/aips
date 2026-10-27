#!/usr/bin/env bash
# nova.sh - pravi nova zadaca so tocen package i Main.java
#
#   bash nova.sh lab 2 1        -> src/mk/ukim/finki/labs/lab02/task1/
#   bash nova.sh aud 2 1        -> src/mk/ukim/finki/aud/aud02/ex1/
#                                  (gi kopira starters od repoto na asistentot, ako go imas)
#   bash nova.sh moi imeZadaca  -> src/mk/ukim/finki/moi/imeZadaca/
#   bash nova.sh ispit imeZadaca -> src/mk/ukim/finki/ispiti/imeZadaca/

set -e
ROOT="$(cd "$(dirname "$0")" && pwd)"
BASE="$ROOT/src/mk/ukim/finki"
# repoto na asistentot (git clone https://github.com/Itonkdong/aps-26-27) do aips papkata
ASIST="$ROOT/../aps-26-27/src/mk/ukim/finki"

usage() {
    sed -n '2,9p' "$0" | sed 's/^# \{0,1\}//'
    exit 1
}

pad() { printf "%02d" "$((10#$1))"; }

case "$1" in
    lab)
        [ -n "$2" ] && [ -n "$3" ] || usage
        sub="labs/lab$(pad "$2")/task$3" ;;
    aud)
        [ -n "$2" ] && [ -n "$3" ] || usage
        sub="aud/aud$(pad "$2")/ex$3"
        starters="$ASIST/aud/aud$((10#$2))/ex$3/starters" ;;
    moi)
        [ -n "$2" ] || usage
        sub="moi/$2" ;;
    ispit)
        [ -n "$2" ] || usage
        sub="ispiti/$2" ;;
    *) usage ;;
esac

dir="$BASE/$sub"
pkg="mk.ukim.finki.${sub//\//.}"

if [ -e "$dir" ]; then
    echo "Vekje postoi: src/mk/ukim/finki/$sub"
    exit 1
fi
mkdir -p "$dir"

if [ -n "$starters" ] && [ -d "$starters" ]; then
    old_pkg="mk.ukim.finki.aud.aud$((10#$2)).ex$3.starters"
    for f in "$starters"/*.java; do
        sed "s/^package ${old_pkg//./\\.};/package $pkg;/" "$f" > "$dir/$(basename "$f")"
    done
    echo "Kopirani starters od asistentot:"
else
    [ -n "$starters" ] && echo "(Nema starters za ova vo ../aps-26-27 - napraviv prazen Main.java)"
    cat > "$dir/Main.java" <<EOF
package $pkg;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

    }
}
EOF
fi

ls -1 "$dir" | sed "s|^|  src/mk/ukim/finki/$sub/|"
