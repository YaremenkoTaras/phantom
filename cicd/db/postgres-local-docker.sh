#!/bin/bash
docker network create db-network
docker run -d \
  --name postgres_container \
  --network db-network \
  -- restart unless-stopped \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -e PGDATA=/data/postgres \
  -v postgres:/data/postgres \
  -p 5432:5432 \
  postgres:alpine
docker run -d \
  --name pgadmin_container \
  --network db-network \
  --restart unless-stopped \
  -e PGADMIN_DEFAULT_EMAIL=admin@admin.com
  -e PGADMIN_DEFAULT_PASSWORD=admin \
  -e PGADMIN_CONFIG_SERVER_MODE=False \
  -v pgadmin:/var/lib/admin \
  -p 5050:5050 \
  fenglc/pgadmin4
docker exec -it pgadmin ping postgres

