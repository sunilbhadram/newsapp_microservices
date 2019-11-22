FROM java:8
WORKDIR /usr/src
ENV EUREKA_HOST=localhost
ADD ./target/ZuulGatewayServer-Docker.jar /usr/src/ZuulGatewayServer-Docker.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","ZuulGatewayServer-Docker.jar"]