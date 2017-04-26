NOMAD_FILE=dp-generator-api.nomad

run:
	./run.sh

nomad:
	@sed -e s,DATA_CENTER,$(DATA_CENTER),g \
		-e s,S3_TAR_FILE,$(S3_TAR_FILE),g \
		-e s,DB_ACCESS_URL,$(DB_ACCESS),g \
		-e s,USER,$(DB_USER),g \
		-e s,PASSWORD,$(DB_PW),g \
			< dp-generator-api-template.nomad > $(NOMAD_FILE)

.PHONY: nomad
