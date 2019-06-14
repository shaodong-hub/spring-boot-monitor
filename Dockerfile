FROM armv7/armhf-java8
MAINTAINER Shao (armv7/armhf-java8) java:8
ADD /target/spring-boot-monitor.jar monitor.jar
ADD /src/main/resources/application-alpha.properties  application.properties
EXPOSE 8080
ENTRYPOINT ["java","-jar","-Xms100m","-Xmx100m","monitor.jar","--spring.config.location=application.properties"]
VOLUME ["/tmp","/Users/shao/Downloads"]








