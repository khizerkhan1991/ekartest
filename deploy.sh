#!/bin/bash

cd /code/api &&
 mvn clean package &&
 cp target/*.jar target/test.jar &&
 java -jar target/test.jar
