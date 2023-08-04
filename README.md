# ad-serving-service

# to build the app
$ mvn clean install -DskipTests

# to create the images
$ docker-compose build

# to start in the services which are specified in docker-compose file
$ docker-compose up

# tag the images into docker-hub(ensure you are logged in using $docker login command)
## for springboot app
$ docker tag adservingservice dokcerhub-username/adservingservice:latest

## for postgres
$ docker tag postgres dokcerhub-username/psotgres:latest

# push the images into dockerhub
$ docker push dokcerhub-username/adservingservice:latest
$ docker push dokcerhub-username/postgres:latest

# start minikube
$ minikube start --driver=docker

# Apply the Kubernetes manifest files
$ kubectl apply -f kube

# Accessing the Application
$ minikube service spring-app-service --url

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

