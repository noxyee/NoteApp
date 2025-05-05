FROM openjdk
ARG JAR_FILE=target/*.jar
COPY ./target/notification-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]