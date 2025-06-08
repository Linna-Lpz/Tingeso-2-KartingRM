FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} ms-rack.jar
ENTRYPOINT ["java","-jar","/ms-rack.jar"]