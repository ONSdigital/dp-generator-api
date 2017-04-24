
export S3_TAR_FILE?=$(shell go env S3_TAR_FILE)
export DATA_CENTER?=$(shell go env DATA_CENTER)
export DB_ACCESS?=$(shell go env ELASTIC_URL)
export DB_USER?=$(shell go env ELASTIC_URL)
export DB_PW?=$(shell go env ELASTIC_URL)
NOMAD_FILE=dp-generator-api.nomad

nomad:
	@cp dp-generator-api-template.nomad $(NOMAD_FILE)
	@sed -i.bak s,DATA_CENTER,$(DATA_CENTER),g $(NOMAD_FILE)
	@sed -i.bak s,S3_TAR_FILE,$(S3_TAR_FILE),g $(NOMAD_FILE)
	@sed -i.bak s,DB_ACCESS_URL,$(DB_ACCESS),g $(NOMAD_FILE)
	@sed -i.bak s,USER,$(DB_USER),g $(NOMAD_FILE)
	@sed -i.bak s,PASSWORD,$(DB_PW),g $(NOMAD_FILE)

.PHONY: nomad