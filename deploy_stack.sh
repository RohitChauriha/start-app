mkdir -p /var/log/start-app/frontend
mkdir -p /var/log/start-app/backend
mkdir -p /var/log/start-app/consumer-service
#docker swarm init
docker stack deploy -c docker-compose.yml start-app