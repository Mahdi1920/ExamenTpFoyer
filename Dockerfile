FROM openjdk:17-jdk-alpine
EXPOSE 8089
COPY pom.xml .
COPY src .
COPY Dockerfile .
COPY docker-compose.yml .
#ADD target/tp-foyer-5.0.0.jar tp-foyer-5.0.0.jar
ENTRYPOINT ["java","-jar","/tp-foyer-5.0.0.jar"]