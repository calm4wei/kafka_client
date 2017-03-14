#!/bin/sh

Usage="""
    $0 <topic>
"""

if [ $# -ne 1 ]; then
    echo "$Usage"
    exit 1
fi

TOPIC=$1

bin/kafka-topics.sh --describe --zookeeper datacube201:9181,datacube202:9181,datacube203:9181 --topic $TOPIC

