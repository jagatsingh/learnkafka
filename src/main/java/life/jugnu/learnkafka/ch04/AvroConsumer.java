package life.jugnu.learnkafka.ch04;

import java.util.Properties;
import java.util.Collections;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import life.jugnu.learnkafka.ch03.User;

public class AvroConsumer {
    public static void main(String[] args) {
        Properties p = new Properties();
        p.put("bootstrap.servers", "localhost:9092");
        p.put("group.id", "AvroConsumer");
        p.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        p.put("value.deserializer", "io.confluent.kafka.serializers.KafkaAvroDeserializer");
        p.put("schema.registry.url", "localhost:8081");

        KafkaConsumer<String, User> c = new KafkaConsumer<String, User>(p);
        c.subscribe(Collections.singletonList("avrotopic"));

        try {
            while (true) {
                ConsumerRecords<String, User> rec = c.poll(100);
                System.out.println("We got record count " + rec.count());
                for (ConsumerRecord<String, User> r : rec) {
                    System.out.println(r.value().getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.close();
        }

    }
}
