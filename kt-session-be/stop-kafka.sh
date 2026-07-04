#!/bin/bash

echo "======================================"
echo "      KT Session - Stop Kafka"
echo "======================================"

echo "Stopping Kafka Containers..."

docker compose down

echo ""
echo "Stopping Docker Desktop..."

osascript -e 'quit app "Docker"'

echo ""
echo "Kafka stopped successfully."