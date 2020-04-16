FROM openjdk:8
RUN mkdir /usr/src/search
COPY target/search-1.0-SNAPSHOT.jar /usr/src/search
EXPOSE 8080
WORKDIR /usr/src/search
CMD ["java", "-jar", "integration-1.0-SNAPSHOT.jar"]