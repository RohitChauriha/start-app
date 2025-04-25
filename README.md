git pull the latest changes
cd ./front-end-demo
RUN docker build -t front-end-demo -f Dockerfile .
cd ../back-end-demo
RUN docker build -t back-end-demo -f Dockerfile .
docker stack deploy -c start-app.yml start-app
docker service scale <service-name>=<count>
Test the app using http://localhost:5000/hello
