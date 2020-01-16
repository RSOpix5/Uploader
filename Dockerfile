FROM openjdk:8-jdk-slim
RUN mkdir /app
WORKDIR /app
ADD ./target/uploader-*.jar /app/uploader.jar
ADD ./oauth/ouath.json /app/oauth.json
EXPOSE 8080
CMD ["java", "-jar", "uploader.jar"]