FROM openjdk:8
COPY . /usr/src/inventory
WORKDIR /usr/src/inventory
ADD build/libs/inventory-service-docker-0.1.jar inventory-service-docker-0.1.jar
EXPOSE 8100
VOLUME /tmp
ENTRYPOINT ["java","-jar","inventory-service-docker-0.1.jar"]