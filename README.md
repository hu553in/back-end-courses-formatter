# formatter

[![Actions Status](https://github.com/hu553in/formatter/workflows/Maven%20CI/badge.svg)](https://github.com/hu553in/formatter/actions)

## Table of contents

* [Description](#description)
* [How to build](#how-to-build)
* [How to use](#how-to-use)

## Description

Java source code formatter.

## How to build

1) Install [OpenJDK](https://openjdk.java.net) (≥ 1.8.0)
2) Install [Apache Maven](https://maven.apache.org)
3) Run following command in the project root folder:

```console
$ mvn package
```

## How to use

Run following command in the project root folder:

```console
$ java -jar ./target/formatter-1.0-RELEASE-jar-with-dependencies.jar %INPUT_FILE_PATH% %OUTPUT_FILE_PATH%
```

**Note:** you must replace `%INPUT_FILE_PATH%` and `%OUTPUT_FILE_PATH%` with your own paths to corresponding files.
