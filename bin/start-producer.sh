#!/bin/sh

TOPIC=$1

bin/kafka-console-producer.sh --broker-list localhost:9092 --topic $TOPIC

