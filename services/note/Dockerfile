FROM openjdk
ARG JAR_FILE=target/*.jar
COPY ./target/note-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]