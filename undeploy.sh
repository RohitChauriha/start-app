docker stack rm start-app
docker swarm leave --force
docker network rm docker_gwbridge
docker image prune -f