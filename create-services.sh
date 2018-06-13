#!/bin/bash

cf create-service p-mysql 100mb order-status-db
#cf create-service p-service-registry standard service-registry
cf create-service p-rabbitmq standard rabbitmq-server
cf create-user-provided-service service-registry -p '{"url": "https://order-processor-service-registry.pcfbeta.io"}'
cf create-user-provided-service legacyorderservice -p '{"url": "https://legacy-order-management.pcfbeta.io"}'