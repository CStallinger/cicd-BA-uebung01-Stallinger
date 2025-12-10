# ===== STAGE 1: Build =====
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Kopiere Maven-Konfiguration + Source-Code
COPY pom.xml .
COPY src ./src

# Baue das Projekt und zeige die erzeugten Dateien
RUN mvn -B -DskipTests=false package && ls -la target


# ===== STAGE 2: Runtime (absichtlich verwundbarer) =====
# Java 17 JDK auf Ubuntu/UBI-Basis, mit bekannten CVEs
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Zusätzliche Linux-Tools installieren (erhöht Angriffsfläche -> mehr CVEs)
RUN apt-get update && \
    apt-get install -y curl wget && \
    rm -rf /var/lib/apt/lists/*

# Gebautes JAR aus der Build-Stage kopieren
COPY --from=build /app/target/*.jar /app/app.jar

# Anwendung starten
ENTRYPOINT ["java", "-cp", "/app/app.jar", "com.example.cicd.App"]
# EXPOSE 8080
