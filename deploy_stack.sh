cd ./front-end-demo
docker build -t front-end-image -f Dockerfile .
cd ../back-end-demo
docker build -t back-end-image -f Dockerfile .
cd ../
#docker compose up
docker swarm init
docker stack deploy -c docker-compose.yml start-app
# docker service scale <service-name>=<count>
# Test the app using http://localhost:5000/hello