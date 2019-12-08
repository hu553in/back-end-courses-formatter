# formatter

[![Actions Status](https://github.com/hu553in/formatter/workflows/Java%20CI/badge.svg)](https://github.com/hu553in/formatter/actions)

## Table of contents

* [Description](#description)
* [How to run](#how-to-run)

## Description

Java source code formatter.

## How to run

1) Install [OpenJDK](https://openjdk.java.net) (≥ 1.8.0)
2) Install [Apache Maven](https://maven.apache.org)
3) Run following sequence of commands in the project root folder:

```console
$ mvn package
$ java -jar ./target/formatter-1.0-RELEASE-jar-with-dependencies.jar %INPUT_FILE_PATH% %OUTPUT_FILE_PATH%
```

**Note:** you must replace `%INPUT_FILE_PATH%` and `%OUTPUT_FILE_PATH%` stubs with your own paths to corresponding files.
