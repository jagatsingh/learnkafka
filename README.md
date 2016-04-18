# Learning Kafka notes

[![Build Status](https://travis-ci.org/jagatsingh/learnkafka.svg?branch=master)](https://travis-ci.org/jagatsingh/learnkafka)

This project is learning related to upcoming book Kafka Definitive guide.

## Pull requests

If you want to add something , please raise the Pull requests.

## Preparation bits

* Kafka and Confluent

Download Confluent and extract it to some place.
Call it CONFLUENT_HOME

`cd $CONFLUENT_HOME`

Start confluent in separate terminals and leave them running

`./bin/zookeeper-server-start ./etc/kafka/zookeeper.properties`
`./bin/kafka-server-start ./etc/kafka/server.properties`
`./bin/schema-registry-start ./etc/schema-registry/schema-registry.properties`

* Source code for this repo

In another terminal lets clone the code lets call it LEARNKAFKA_HOME

Compile and make fat jar

`cd $LEARNKAFKA_HOME`
`git clone https://github.com/jagatsingh/learnkafka.git`
`cd learnkafka`
`mvn clean compile assembly:single`


# Chapter 3

##  Exercise : Create first topic

Create first topic with single partition and replication

`cd $CONFLUENT_HOME`
`bin/kafka-topics --zookeeper localhost:2181 --create --topic firsttopic  --partitions 1 --replication-factor 1`

##  Exercise : Send message to topic

Send messages to our first topic

`cd $LEARNKAFKA_HOME`
`java -cp ./target/learnkafka-1.0-SNAPSHOT-jar-with-dependencies.jar life.jugnu.learnkafka.ch03.FirstProducer`

Run it multiple times

##  Exercise : See messages in topic

See the messages from command line

`bin/kafka-console-consumer --zookeeper localhost:2181 --topic firsttopic --from-beginning`


##  Exercise : Send Avro messages to topic

`java -cp ./target/learnkafka-1.0-SNAPSHOT-jar-with-dependencies.jar life.jugnu.learnkafka.ch03.AvroProducer`


# Chapter 4

## Exercise : Consuming String messages from topic

In this we will Consuming messages from topic named firsttopic where we had (String, String) as (key, value) 

`java -cp ./target/learnkafka-1.0-SNAPSHOT-jar-with-dependencies.jar life.jugnu.learnkafka.ch04.FirstConsumer`

In other console start sending messages to Kafka topic using earlier command

`java -cp ./target/learnkafka-1.0-SNAPSHOT-jar-with-dependencies.jar life.jugnu.learnkafka.ch03.FirstProducer`

Watch the output of consumer

## Exercise : Consume Avro messages from topic

We will use build in console consumer first

Produce some records using

`java -cp ./target/learnkafka-1.0-SNAPSHOT-jar-with-dependencies.jar life.jugnu.learnkafka.ch03.AvroProducer`

Consume them using built in console consumer

`bin/kafka-console-consumer --zookeeper localhost:2181 --topic avrotopic --from-beginning \
--value-deserializer io.confluent.kafka.serializers.KafkaAvroDeserializer \
--key-deserializer org.apache.kafka.common.serialization.StringDeserializer`


##  Exercise : Consume Avro messages from topic via Java

Produce the messages , run it multiple times

`java -cp ./target/learnkafka-1.0-SNAPSHOT-jar-with-dependencies.jar life.jugnu.learnkafka.ch03.AvroProducer`

Consume the messages using custom Java code

`java -cp ./target/learnkafka-1.0-SNAPSHOT-jar-with-dependencies.jar life.jugnu.learnkafka.ch04.AvroConsumer`

