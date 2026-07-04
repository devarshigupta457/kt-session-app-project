#!/bin/bash

echo "======================================"
echo "      KT Session - Start Kafka"
echo "======================================"

# Start Docker Desktop
echo "Starting Docker Desktop..."
open -a Docker

# Wait for Docker
echo "Waiting for Docker to start..."

until docker info >/dev/null 2>&1
do
    printf "."
    sleep 2
done

echo ""
echo "Docker is running."

echo ""
echo "Starting Kafka Containers..."

docker compose up -d

echo ""
echo "Waiting for Kafka to initialize..."
sleep 10

echo ""
echo "Running Containers:"
docker ps

echo ""
echo "======================================"
echo "Kafka Started Successfully"
echo "Kafka Broker : localhost:9092"
echo "Kafka UI     : http://localhost:8081"
echo "======================================"