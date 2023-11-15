.PHONY: run build


install:
    mkdir maven
	docker compose build
	docker compose run java mvn clean install  

run: 
	docker compose run java mvn package
	docker compose run java java -jar target/nebulaquest-1.0.0.jar
