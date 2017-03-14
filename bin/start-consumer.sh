#!/bin/sh

TOPIC=$1

bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic $TOPIC

