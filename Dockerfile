# ===== STAGE 1: Build =====
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Kopiere Maven-Konfiguration + Source-Code
COPY pom.xml .
COPY src ./src

# Baue das Projekt und zeige die erzeugten Dateien
RUN mvn -B -DskipTests=false package && ls -la target


# ===== STAGE 2: Runtime =====
FROM eclipse-temurin:17-jre
WORKDIR /app

# Kopiere das gebaute JAR aus dem ersten Stage
COPY --from=build /app/target/*.jar /app/app.jar

# Starte die Anwendung über die Main-Class (anpassen falls nötig)
ENTRYPOINT ["java", "-cp", "/app/app.jar", "com.example.cicd.App"]

# Falls HTTP-App: Port öffnen
# EXPOSE 8080
