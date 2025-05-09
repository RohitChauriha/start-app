cd ./back-end-demo
mvn clean install
if [ $? -ne 0 ]; then
  echo "Command failed with exit status: $?"
  exit ;
fi
docker build -t rodoch/back-end-image:latest -f Dockerfile .
cd ../consumer-service
mvn clean install
if [ $? -ne 0 ]; then
  echo "Command failed with exit status: $?"
  exit ;
fi
docker build -t rodoch/consumer-service-image:latest -f Dockerfile .
cd ../front-end-demo
docker build -t rodoch/front-end-image:latest -f Dockerfile .
cd ../message-queue
docker build -t rodoch/message-queue-image:latest -f Dockerfile .
docker login --username=rodoch
docker push rodoch/back-end-image
docker push rodoch/front-end-image
docker push rodoch/consumer-service-image
docker push message-queue-image
