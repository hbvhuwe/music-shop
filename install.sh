#!/bin/bash

set -e

init() {
    # first parameter: what to create
    # second parameter: name
    # third parameter: file
    local found=$(docker $1 ls --format='{{.Name}}' --filter name=$2)
    if [ -z "${found}" -o "${found}" != $2 ]; then
        docker $1 create $2 $3
        echo "$1 $2 created successfully"
    else
        echo "Alreary have $1 $2, skipping..."
    fi
}

# create volume
docker volume create database_volume

# build images
cd music-shop-api
docker build -t ms-api .
cd ..
cd music-shop-web
docker build -t ms-web .
cd ..

# init swarm
is_swarm=$(docker info | grep 'Swarm: active')
if [[ $is_swarm == '' ]]; then
    docker swarm init
fi

# init configs and secrets
init config web-app.conf configs/web-app.conf
init secret user_password configs/user_password
openssl rand -base64 20 | init secret root_password -

docker stack deploy -c docker-compose.yml music_shop

echo "Music shop installation compete"