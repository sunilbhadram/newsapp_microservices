FROM java:8
WORKDIR /usr/src
ENV MONGO_DATABASE=RecomServiceDB
ENV	MONGO_HOST=localhost
ENV	MONGO_PORT=27017
ENV EUREKA_HOST=localhost
ADD ./target/ArticleService-Docker.jar /usr/src/ArticleService-Docker.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","ArticleService-Docker.jar"]