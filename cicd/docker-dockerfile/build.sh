#!/bin/bash
cd ../../
echo "Checking out main branch"
git checkout main
echo "Building and pushing Docker images"
echo "Building service-registry"
cd service-registry
./mvnw clean package
docker build -f .dockerfile  -t tyaremenko949/service-registry .
docker tag tyaremenko949/service-registry tyaremenko949/service-registry:latest
docker push tyaremenko949/service-registry:latest
cd ../
echo "Building config-server"
cd config-server
./mvnw clean package
docker build -f .dockerfile  -t tyaremenko949/config-server .
docker tag tyaremenko949/config-server tyaremenko949/config-server:latest
docker push tyaremenko949/config-server:latest
cd ../
echo "Building gateway"
cd gateway
./mvnw clean package
docker build -f .dockerfile  -t tyaremenko949/gateway .
docker tag tyaremenko949/gateway tyaremenko949/gateway:latest
docker push tyaremenko949/gateway:latest
cd ../
echo "Building user-service"
cd user-service
./mvnw clean package
docker build -f .dockerfile  -t tyaremenko949/user-service .
docker tag tyaremenko949/user-service tyaremenko949/user-service:latest
docker push tyaremenko949/user-service:latest
cd ../
echo "Building content-service"
cd content-service
./mvnw clean package
docker build -f .dockerfile  -t tyaremenko949/content-service .
docker tag tyaremenko949/content-service tyaremenko949/content-service:latest
docker push tyaremenko949/content-service:latest
cd ../
echo "Building comment-service"
cd comment-service
./mvnw clean package
docker build -f .dockerfile  -t tyaremenko949/comment-service .
docker tag tyaremenko949/comment-service tyaremenko949/comment-service:latest
docker push tyaremenko949/comment-service:latest
cd ../
echo "Building and pushing Docker images completed"
echo "Build finished"
