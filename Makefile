.PHONY: run build

install:
	mkdir -p maven
	docker compose build
	docker compose run java mvn clean install

run:
	rm -rf target
	docker compose run java mvn package
