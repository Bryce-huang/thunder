FROM  kdvolder/jdk8

MAINTAINER cherbini@qq.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /zipkin

WORKDIR /zipkin

EXPOSE 5002

ADD ./visual/zipkin/target/zipkin.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar zipkin.jar
