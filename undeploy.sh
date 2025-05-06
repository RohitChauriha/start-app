docker stack rm start-app
docker swarm leave --force
docker rm -f $(docker ps -aq)
docker network rm docker_gwbridge
docker image prune -f
docker volume rm $(docker volume ls -q)