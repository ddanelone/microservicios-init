FROM eclipse-temurin:17

LABEL MAINTAINER="support@diegodanelone.com.ar"
LABEL DETALLE="Gateway Service debe ejecutarse tercero"

# Añadir el archivo JAR al contenedor
ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/diego/gateway-service.jar

# Establecer el punto de entrada
ENTRYPOINT ["java", "-jar", "/usr/share/diego/gateway-service.jar"]
