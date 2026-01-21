FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY pom.xml .
RUN apt-get update && apt-get install -y maven
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/urlshortener-0.0.1-SNAPSHOT.jar"]

