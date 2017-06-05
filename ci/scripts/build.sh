#!/bin/bash -eux

pushd dp-generator-api
  mvn -Dmaven.test.skip=true package && mv target/dp-generator-api-*.jar ../build
popd
