# Dockerfile per il servizio enigmi
FROM openjdk:11-jdk
ADD build/libs/enigmi.jar enigmi.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Xmx128m", "-Xms128m", "-jar", "enigmi.jar"]
HEALTHCHECK --start-period=30s --interval=10s CMD curl -f http://api-gateway:8080/actuator/health || exit 1