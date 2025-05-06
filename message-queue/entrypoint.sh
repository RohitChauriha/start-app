#!/bin/bash
set -e

# Start RabbitMQ in detached mode
rabbitmq-server -detached

# Wait for RabbitMQ to start
sleep 5

# Join the cluster if RABBITMQ_CLUSTER_NODE is set
if [ -n "$RABBITMQ_CLUSTER_NODE" ]; then
  rabbitmqctl stop_app
  rabbitmqctl reset
  rabbitmqctl join_cluster rabbit@$RABBITMQ_CLUSTER_NODE
  rabbitmqctl start_app
fi
