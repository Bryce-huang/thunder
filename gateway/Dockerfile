FROM  kdvolder/jdk8


RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /gateway

WORKDIR /gateway

EXPOSE 9999

ADD ./gateway/target/gateway.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar gateway.jar
