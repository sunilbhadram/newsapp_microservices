FROM java:8
WORKDIR /usr/src
ENV MONGO_DATABASE=FavServiceDB
ENV	MONGO_HOST=localhost
ENV	MONGO_PORT=27017
ENV EUREKA_HOST=localhost
ENV RABBIT_HOST=127.0.0.1
ENV RABBIT_PORT=5672
ADD ./target/FavouriteService-Docker.jar /usr/src/FavouriteService-Docker.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","FavouriteService-Docker.jar"]