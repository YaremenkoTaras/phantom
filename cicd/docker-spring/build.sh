#!/bin/bash
cd ../../
git checkout main
cd service-registry
./mvnw spring-boot:build-image "-Dspring-boot.build-image.imageName=yaremenko/service-registry" "-Djava.net.useSystemProxies=true"
cd ../config-server
./mvnw spring-boot:build-image "-Dspring-boot.build-image.imageName=yaremenko/config-server"
cd ../gateway
./mvnw spring-boot:build-image "-Dspring-boot.build-image.imageName=yaremenko/gateway"
cd ../user-service
./mvnw spring-boot:build-image "-Dspring-boot.build-image.imageName=yaremenko/user-service"
cd ../content-service
./mvnw spring-boot:build-image "-Dspring-boot.build-image.imageName=yaremenko/content-service"
cd ../comment-service
./mvnw spring-boot:build-image "-Dspring-boot.build-image.imageName=yaremenko/comment-service"
cd ../
docker push yaremenko/service-registry
docker push yaremenko/config-server
docker push yaremenko/gateway
docker push yaremenko/user-service
docker push yaremenko/content-service
docker push yaremenko/comment-service
