FROM openjdk:17-jdk-alpine

LABEL MAINTAINER="support@diegodanelone.com.ar"
LABEL DETALLE="Config Service debe ejecutarse primero"

# Añadir el archivo JAR al contenedor
ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/myservice/config-service.jar

# Establecer el punto de entrada
ENTRYPOINT ["java", "-jar", "/usr/share/myservice/config-service.jar"]
