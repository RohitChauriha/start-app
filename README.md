git repo: [https://github.com/RohitChauriha/start-app.git](https://github.com/RohitChauriha/start-app.git)
 - run the **_docker swarm init_** on master node and copy the result command
 - run that result command in worker node
 - **_sudo ./deploy_stack.sh_** in master node will build images and deploy the stack
 - Test the application using url http://localhost:5000/hello
 - Individual services can be scaled up/down using following command 
   - _**docker service scale <service-name>=count**_ OR
   - update the replicas in docker-compose
 If running the app in single node then no need for deploying stack on swarm
 Simple _**docker compose up**_ command from start-app will start all services