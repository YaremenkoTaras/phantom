#!/bin/bash
kubectl apply -f services/zipkin
kubectl apply -f services/rabbitmq
kubectl apply -f services/postgres