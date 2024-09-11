build:
	docker-compose build
	./mvnw package -DskipTests

run:
	docker-compose up -d
	java -jar ./target/url.shortener-0.0.1-SNAPSHOT.jar