# Utiliza una imagen base de Java
FROM openjdk:17

# Copia el archivo JAR de tu aplicación en el contenedor
COPY target/power-up-arquetipo.jar /app.jar

# Expone el puerto en el que se ejecuta tu aplicación Spring Boot
EXPOSE 8094

# Comando para iniciar la aplicación
CMD ["java", "-jar", "/app.jar"]
