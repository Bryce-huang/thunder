FROM  kdvolder/jdk8


RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /config

WORKDIR /config

EXPOSE 4001

ADD ./config/target/config.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar config.jar
