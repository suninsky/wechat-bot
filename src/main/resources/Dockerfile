FROM openjdk:11-jdk-alpine
VOLUME /tmp
ADD spring-boot-wechat-bot-1.0.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar","--spring.profiles.active=prod","-Xms512m","-Xmx512m","-Xmn200m","-Xss128k"]
