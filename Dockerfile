FROM openjdk:8-jdk-alpine
WORKDIR /opt/app
ARG JAR_FILE=target/phonecodes-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]


#FROM openjdk:8
#ARG JAR_FILE=target/*.jar
#RUN mkdir /usr/src/service
#COPY target/phonecodes-1.0-SNAPSHOT.jar /usr/src/service
#EXPOSE 8080
#WORKDIR /usr/src/service
#CMD ["java", "-jar", "phonecodes-1.0-SNAPSHOT.jar"]