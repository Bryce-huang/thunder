chown -R 1000:1000 /home/jenkins

docker run -it -u root -d -p 8000:8080 -p 50000:50000 --name jenkins \
-v /home/jenkins:/var/jenkins_home -e JAVA_OPTS=-Duser.timezone=Asia/Shanghai \
-v $(which docker):/usr/bin/docker -v /var/run/docker.sock:/var/run/docker.sock \
-v  /home/jenkins/apache-maven-3.5.0:/var/maven_home \
--privileged=true jenkins:2.150
