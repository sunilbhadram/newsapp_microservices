FROM java:8
WORKDIR /usr/src
ENV HOST_NAME=localhost
ENV MYSQL_USERNAME=root
ENV MYSQL_PASSWORD=passw0rd
ENV MYSQL_URL=jdbc:mysql://${HOST_NAME}:3306/newsapp_db
ENV EUREKA_HOST=${HOST_NAME}
ADD ./target/UserService-Docker.jar /usr/src/UserService-Docker.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","UserService-Docker.jar"]