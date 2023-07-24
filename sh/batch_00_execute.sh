#!/bin/bash

JAR_NAME=batch-0.0.1-SNAPSHOT.jar
JAR_PATH=../target/${JAR_NAME}

# e.g. sh batch_00_execute.sh -message hello
java -jar ${JAR_PATH} hoge huga "$@"
