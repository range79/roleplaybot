FROM eclipse-temurin:17-jre

LABEL authors="range79"

WORKDIR /app

COPY bot/build/libs/app.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
