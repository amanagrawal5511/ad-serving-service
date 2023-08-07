# ad-serving-service

# to build the app
$ mvn clean install -DskipTests

# to create the images
$ docker-compose build

# to start in the services which are specified in docker-compose file
$ docker-compose up

# tag the images into docker-hub(ensure you are logged in using $docker login command)
## for springboot app
$ docker tag adservingservice chnd777/adservingservice:latest

## for postgres
$ docker tag postgres chnd777/postgres:latest

## for prometheus
$ docker tag prom/prometheus:latest chnd777/prometheus:latest

# push the images into dockerhub
$ docker push chnd777/adservingservice:latest
$ docker push chnd777/postgres:latest
$ docker push chnd777/prometheus:latest

# start minikube
$ minikube start --driver=docker

# Apply the Kubernetes manifest files
$ kubectl apply -f kube

# Accessing the Application
$ minikube service spring-app-service --url

# Accessing the prometheus
$ minikube service prometheus-service --url

# To restart pods
$ kubectl rollout restart deployment

# To list out the containers in docker 
$ docker ps

# To list the images
$ docker images

# To list out the pods
$ kubectl get pods

# To see the logs of a pod
$ kubectl logs pod_name

# To access the postgres container in kubernetes
$ kubectl exec -it postgres-pod-name -- /bin/bash
$ psql -U user-name -d table-name

# To see the running files
$ kubectl get services

