# Kafka-SpringBoot
1.Install kafka : 
$ tar -xzf kafka_2.13-3.5.0.tgz
$ cd kafka_2.13-3.5.0

2.Start the kafka environment : 
$ bin/zookeeper-server-start.sh config/zookeeper.properties

3.Create topic : 
$ bin/kafka-topics.sh --create --topic topicName --bootstrap-server localhost:9092

4.Run the Producer console : 
$ bin/kafka-console-producer.sh --topic topicName --bootstrap-server localhost:9092

5.Run the Consumer console : 
$ bin/kafka-console-consumer.sh --topic topicName --from-beginning --bootstrap-server localhost:9092
