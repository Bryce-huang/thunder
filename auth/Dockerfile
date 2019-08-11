FROM  kdvolder/jdk8

ADD target/config-1.0-SNAPSHOT.jar /opt/app.jar
ENTRYPOINT java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom \
 -Duser.timezone=Asia/Shanghai \
 -Dfile.encoding=UTF-8  \
 -jar /opt/app.jar \

