#!/bin/bash

cd legacy-order-management
mvn clean package
cf push
cd ../eureka-server
mvn clean package
cf push
cd ../order-status-service
mvn clean package
cf push
cd ../message-processor
mvn clean package
cf push
cd ../order-api-frontend
mvn clean package
cf push
cd ..
