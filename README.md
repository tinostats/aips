# АИПС — Algorithms and Data Structures (FINKI, 2026/27)

![build](https://github.com/tinostats/aips/actions/workflows/build.yml/badge.svg)

My solutions to the lab exercises, classroom (auditory) exercises and exam tasks for the
**Algorithms and Data Structures** course at FINKI, together with my own from-scratch
implementations of the core data structures. Java 11, no external libraries.

## Layout

```text
src/mk/ukim/finki/
├── dataStructures/     course implementations (Array, SLL, DLL, Stack, Queue, PriorityQueue)
├── moi/
│   ├── strukturi/      my own implementations of the same structures
│   └── <task>/         tasks I practise on my own
├── aud/audNN/exN/      classroom exercises, by session number
├── labs/labNN/taskN/   lab exercises, by lab number
└── ispiti/<task>/      past exam / colloquium tasks
```

Every task lives in its own package with its own `Main`, so any task can be run on its own.

## Progress

### Labs

| Lab | Task | Topic |
|-----|------|-------|
| 01 | [task1](src/mk/ukim/finki/labs/lab01/task1/Main.java) | Inheritance and polymorphism: employee salary hierarchy |
| 01 | [task2](src/mk/ukim/finki/labs/lab01/task2/Main.java) | Generics: product container with bounded type parameter |

### Classroom exercises

| Session | Exercise | Topic | Status |
|---------|----------|-------|--------|
| 01 | [ex1](src/mk/ukim/finki/aud/aud01/ex1/ChangeArrays.java) | Remove equal elements at matching positions (Array / ArrayList) | ⬜ |

## Working on it

```bash
git pull                     # always first
bash nova.sh lab 2 1         # new lab task   -> labs/lab02/task1/Main.java
bash nova.sh aud 2 1         # new exercise   -> aud/aud02/ex1/ (copies the course starters if available)
bash nova.sh moi imeZadaca   # own practice task
bash nova.sh ispit imeZadaca # exam task
idea .                       # open the project in IntelliJ IDEA
git add .
git commit -m "lab02 task1: <what was done>"
git push
```

`nova.sh aud` copies the starter code from a local clone of the course repository placed next
to this one (`../aps-26-27`):

```bash
cd ..
git clone https://github.com/Itonkdong/aps-26-27
```

Compile everything from the terminal (same check the GitHub build runs):

```bash
javac -d out $(find src -name "*.java")
java -cp out mk.ukim.finki.labs.lab01.task1.Main
```

## Credits

The `dataStructures` package and the exercise starter code come from the course repository
[Itonkdong/aps-26-27](https://github.com/Itonkdong/aps-26-27), maintained by
Viktor Kostadinoski for the course. Everything under `labs/`, `moi/`, `ispiti/` and the
solutions under `aud/` are my own work.
