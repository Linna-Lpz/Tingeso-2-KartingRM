FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} ms-booking.jar
ENTRYPOINT ["java","-jar","/ms-booking.jar"]