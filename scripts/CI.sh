#安装jenkins
chown -R 1000:1000 /home/jenkins
docker run -it -u root -d -p 8000:8080 -p 50000:50000 --name jenkins \
-v /home/jenkins:/var/jenkins_home -e JAVA_OPTS=-Duser.timezone=Asia/Shanghai \
-v $(which docker):/usr/bin/docker -v /var/run/docker.sock:/var/run/docker.sock \
-v  /home/jenkins/apache-maven-3.5.0:/var/maven_home \
--privileged=true jenkins:2.150

# 运行eureka
docker run  \
--env JAVA_OPTS='-server -Xmx512m' \
--env PROFILE='dev' \
--env EUREKA_PORT='8761' \
--name single-eureka -d \
--add-host gitlab.bryce.com:192.168.0.103 \
--restart always brycehuang/single-eureka:0.0.1-SNAPSHOT \
--network=host


# 运行config
docker run  \
--env JAVA_OPTS='-server -Xmx512m' \
--name config -d \
--add-host gitlab.bryce.com:192.168.0.103 \
--restart always brycehuang/config:1.0-SNAPSHOT \
$(cat /etc/hosts|awk -F ' ' '{if(NR>2){print "--add-host " $2 ":" $1}}') \
--network=host


# 运行admin
docker run  \
--add-host gitlab.bryce.com:192.168.0.103 \
--env JAVA_OPTS='-server -Xmx512m' \
--name admin -d \
 brycehuang/admin:1.0-SNAPSHOT \
--network=host


# 运行auth
docker run  \
--add-host gitlab.bryce.com:192.168.0.103 \
--env JAVA_OPTS='-server -Xmx512m' \
--name auth -d \
 brycehuang/auth:1.0-SNAPSHOT \
--network=host

# 运行gateway
docker run  \
--add-host gitlab.bryce.com:192.168.0.103 \
--env JAVA_OPTS='-server -Xmx512m' \
--name gateway -d \
 brycehuang/gateway:1.0-SNAPSHOT \
--network=host


# 运行monitor
docker run  \
--add-host gitlab.bryce.com:192.168.0.103 \
--env JAVA_OPTS='-server -Xmx512m' \
--name monitor -d \
--restart always brycehuang/monitor:1.0-SNAPSHOT \
--network=host

# 运行zipkin
docker run  \
--add-host gitlab.bryce.com:192.168.0.103 \
--env JAVA_OPTS='-server -Xmx512m' \
--name zipkin -d \
--restart always brycehuang/zipkin:1.0-SNAPSHOT \
--network=host

