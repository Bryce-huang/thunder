FROM  kdvolder/jdk8

MAINTAINER cherbini@qq.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /monitor

WORKDIR /monitor

EXPOSE 5001

ADD ./visual/monitor/target/monitor.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar monitor.jar
