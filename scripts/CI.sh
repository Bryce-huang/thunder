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
--restart always brycehuang/single-eureka:0.0.1-SNAPSHOT \
--network=host


# 运行config
docker run -p 8888:8888 \
--env JAVA_OPTS='-server -Xmx512m' \
--name single-config -d \
--restart always brycehuang/config:1.0-SNAPSHOT \
--network=host