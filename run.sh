#!/bin/bash

mvn package

java -jar target/dp-generator-api-0.1.0.jar
