FROM  kdvolder/jdk8

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /auth

WORKDIR /auth

EXPOSE 3000

ADD ./auth/target/auth.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar auth.jar
