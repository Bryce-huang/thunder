#安装jenkins
chown -R 1000:1000 /home/jenkins
docker run -it -u root -d -p 8000:8080 -p 50000:50000 --name jenkins \
-v /home/jenkins:/var/jenkins_home -e JAVA_OPTS=-Duser.timezone=Asia/Shanghai \
-v $(which docker):/usr/bin/docker -v /var/run/docker.sock:/var/run/docker.sock \
-v  /home/jenkins/apache-maven-3.5.0:/var/maven_home \
--privileged=true jenkins:2.150

# 运行eureka
docker run -p 8761:8761 \
--env JAVA_OPTS='-server -Xmx512m' \
--env PROFILE='dev' \
--env EUREKA_PORT='8761' \
--name single-eureka -d \
--add-host gitlab.bryce.com:192.168.0.103 \
--restart always brycehuang/single-eureka:0.0.1-SNAPSHOT \
--network=host


# 运行config
docker run -p 8888:8888 \
--env JAVA_OPTS='-server -Xmx512m' \
--name config -d \
--add-host gitlab.bryce.com:192.168.0.103 \
--restart always brycehuang/config:1.0-SNAPSHOT \
$(cat /etc/hosts|awk -F ' ' '{if(NR>2){print "--add-host " $2 ":" $1}}') \
--network=host


# 运行admin
docker run -p 4000:4000 \
--add-host gitlab.bryce.com:192.168.0.103 \
--env JAVA_OPTS='-server -Xmx512m' \
--name admin -d \
 brycehuang/admin:1.0-SNAPSHOT \
--network=host


# 运行auth
docker run -p 3000:3000 \
--add-host gitlab.bryce.com:192.168.0.103 \
--env JAVA_OPTS='-server -Xmx512m' \
--name auth -d \
 brycehuang/auth:1.0-SNAPSHOT \
--network=host

# 运行gateway
docker run -p 9999:9999 \
--add-host gitlab.bryce.com:192.168.0.103 \
--env JAVA_OPTS='-server -Xmx512m' \
--name gateway -d \
 brycehuang/gateway:1.0-SNAPSHOT \
--network=host


# 运行monitor
docker run -p 5001:5001 \
--add-host gitlab.bryce.com:192.168.0.103 \
--env JAVA_OPTS='-server -Xmx512m' \
--name monitor -d \
--restart always brycehuang/monitor:1.0-SNAPSHOT \
--network=host

# 运行zipkin
docker run -p 5002:5002 \
--add-host gitlab.bryce.com:192.168.0.103 \
--env JAVA_OPTS='-server -Xmx512m' \
--name zipkin -d \
--restart always brycehuang/zipkin:1.0-SNAPSHOT \
--network=host

