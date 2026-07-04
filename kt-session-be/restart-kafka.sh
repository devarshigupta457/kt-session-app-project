#!/bin/bash

echo "Restarting Kafka..."

./stop-kafka.sh

sleep 5

./start-kafka.sh