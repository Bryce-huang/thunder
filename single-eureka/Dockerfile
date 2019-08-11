FROM  kdvolder/jdk8
ARG JAR_FILE
ADD target/${JAR_FILE} /opt/app.jar
ENTRYPOINT java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom \
 -Duser.timezone=Asia/Shanghai \
 -Dfile.encoding=UTF-8  \
 -Dspring.profiles.active=${PROFILE}  \
 -jar /opt/app.jar \
 -Dserver.port=${EUREKA_PORT} \
 -Deureka.client.serviceUrl.defaultZone=http://gitlab.bryce.com:${EUREKA_PORT}/eureka/
