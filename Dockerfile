FROM openjdk:8-jre-alpine

WORKDIR /usr/src

COPY . .

ENTRYPOINT ["java","-jar","target/dale-gg-1.0.0-SNAPSHOT.jar"]