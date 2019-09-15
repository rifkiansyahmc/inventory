FROM openjdk:8
ADD build/libs/inventory-service-docker-0.1.jar inventory-service-docker-0.1.jar
EXPOSE 8100
VOLUME /tmp
ENTRYPOINT ["java","-jar","inventory-service-docker-0.1.jar"]