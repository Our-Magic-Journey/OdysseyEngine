FROM eclipse-temurin:21-jdk-alpine


RUN apk update && \
    apk add maven

ENV MAVEN_CONFIG /usr/share/maven/ref
RUN mkdir -p /.m2/repository
RUN chmod -R 777 /.m2
