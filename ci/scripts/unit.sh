#!/bin/bash -eux

export GOPATH=$(pwd)/go

pushd dp-generator-api
  make test
popd
