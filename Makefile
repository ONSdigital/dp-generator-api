MAIN=dp-generator-api
SHELL=bash

BUILD?=build
S3_REGION?=eu-west-1

NOMAD_SRC_DIR?=nomad
NOMAD_PLAN_TARGET?=$(BUILD)
NOMAD_PLAN=$(NOMAD_PLAN_TARGET)/$(MAIN).nomad

PORT?=8092

thisOS:=$(shell uname -s)
ifeq ($(thisOS),Darwin)
SED?=gsed
else
SED?=sed
endif

ifdef DEV
NOMAD?=
HUMAN_LOG?=1
DATA_CENTER?=dc1
else
HUMAN_LOG?=
NOMAD?=1
DATA_CENTER?=$(S3_REGION)
endif

build:
	mvn package

run:
ifdef NOMAD
	@if [[ ! -f $(NOMAD_PLAN) ]]; then echo Cannot see $(NOMAD_PLAN); exit 1; fi; echo nomad run $(NOMAD_PLAN); nomad run $(NOMAD_PLAN)
else
	./run.sh
endif

nomad:
	@test -d $(NOMAD_PLAN_TARGET) || mkdir -p $(NOMAD_PLAN_TARGET)
	@driver=exec; [[ -n "$(DEV)" ]] && driver=raw_exec;	\
	$(SED) -r \
		-e 's,\bDATA_CENTER\b,$(DATA_CENTER),g' \
		-e 's,\bS3_TAR_FILE\b,$(S3_TAR_FILE),g' \
		-e 's,\bDB_ACCESS_URL\b,$(DB_ACCESS),g' \
		-e 's,\bUSER\b,$(DB_USER),g' \
		-e 's,\bPASSWORD\b,$(DB_PW),g' \
		-e 's,\bGENERATOR_PORT\b,$(PORT),g' \
		-e 's,\bHUMAN_LOG_FLAG\b,$(HUMAN_LOG),g' \
		-e 's,^(  *driver  *=  *)"exec",\1"'$$driver'",' \
			< $(MAIN)-template.nomad > $(NOMAD_PLAN)

.PHONY: nomad build
