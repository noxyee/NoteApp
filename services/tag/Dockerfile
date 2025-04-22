FROM openjdk
ARG JAR_FILE=target/*.jar
COPY ./target/tag-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]