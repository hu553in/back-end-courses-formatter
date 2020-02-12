# formatter

## Table of contents

* [Description](#description)
* [How to use](#how-to-use)

## Description

Java source code formatter based on [finite-state machine](https://en.wikipedia.org/wiki/Finite-state_machine).

## Technology stack

* [OpenJDK](https://openjdk.java.net) 1.8.0
* [Gradle](https://gradle.org) 6.1.1

## How to use

1) Install [OpenJDK](https://openjdk.java.net) (≥ 1.8.0)
2) Install [Make](https://www.gnu.org/software/make)
3) Run following command in the project root folder:

```console
$ make IN=%INPUT_FILE_PATH% OUT=%OUTPUT_FILE_PATH%
```

**Note:** you must replace `%INPUT_FILE_PATH%` and `%OUTPUT_FILE_PATH%` with your own paths to corresponding files.

For additional info see [Makefile](./Makefile) contents.
