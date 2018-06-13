#!/bin/bash

#apps
cf delete -r -f order-api-frontend
cf delete -r -f message-processor
cf delete -r -f order-status-service
cf delete -r -f eureka-server
cf delete -r -f legacy-order-management

#services
cf delete-service -f legacyorderservice
cf delete-service -f rabbitmq-server
cf delete-service -f order-status-db
cf delete-service -f service-registry