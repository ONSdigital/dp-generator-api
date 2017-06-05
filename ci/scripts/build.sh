#!/bin/bash -eux

cwd=$(pwd)

export GOPATH=$cwd/go

pushd dp-generator-api
  make build && mv build/bin/* $cwd/build
popd
