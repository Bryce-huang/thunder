FROM  kdvolder/jdk8
ARG JAR_FILE
ADD target/${JAR_FILE} /opt/app.jar
ENTRYPOINT java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom \
 -Duser.timezone=Asia/Shanghai \
 -Dfile.encoding=UTF-8  \
 -jar /opt/app.jar \

