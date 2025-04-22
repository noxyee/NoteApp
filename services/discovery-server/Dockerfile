FROM openjdk
ARG JAR_FILE=target/*.jar
COPY ./target/discovery-server-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]