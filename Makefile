MAIN=dp-generator-api

BUILD?=build

NOMAD?=
NOMAD_SRC_DIR?=nomad
NOMAD_PLAN_TARGET?=$(BUILD)
NOMAD_PLAN=$(NOMAD_PLAN_TARGET)/$(MAIN).nomad

thisOS:=$(shell uname -s)
ifeq ($(thisOS),Darwin)
SED?=gsed
else
SED?=sed
endif

ifdef DEV
HUMAN_LOG?=1
else
HUMAN_LOG?=
endif

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
		-e s,\bDATA_CENTER\b,$(DATA_CENTER),g \
		-e s,\bS3_TAR_FILE\b,$(S3_TAR_FILE),g \
		-e s,\bDB_ACCESS_URL\b,$(DB_ACCESS),g \
		-e s,\bUSER\b,$(DB_USER),g \
		-e s,\bPASSWORD\b,$(DB_PW),g \
		-e 's,\bHUMAN_LOG_FLAG\b,$(HUMAN_LOG),g'		\
		-e 's,^(  *driver  *=  *)"exec",\1"'$$driver'",'	\
			< $(MAIN)-template.nomad > $(NOMAD_PLAN)

.PHONY: nomad
