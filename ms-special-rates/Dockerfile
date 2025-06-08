FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} ms-special-rates.jar
ENTRYPOINT ["java","-jar","/ms-special-rates.jar"]