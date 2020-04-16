FROM openjdk:8
RUN mkdir /usr/src/integration
COPY target/integration-1.0-SNAPSHOT.jar /usr/src/integration
EXPOSE 8081
WORKDIR /usr/src/integration
CMD ["java", "-jar", "integration-1.0-SNAPSHOT.jar"]