mkdir -p /var/log/start-app/frontend
mkdir -p /var/log/start-app/backend
#docker swarm init
docker stack deploy -c docker-compose.yml start-app