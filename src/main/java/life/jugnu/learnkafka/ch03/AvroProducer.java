package life.jugnu.learnkafka.ch03;

import java.util.Properties;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.clients.producer.ProducerRecord;

public class AvroProducer {
    public static void main(String[] args) {
        Properties p = new Properties();

        // Properties are created similarly , note the KafkaAvroSerializer used here instead of StringSerializer
        p.put("bootstrap.servers", "localhost:9092");
        //p.put("key.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
        p.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        p.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
        p.put("schema.registry.url", "http://localhost:8081");

        Producer<String, User> pd = new KafkaProducer<>(p);

        User u = UserGenerator.getNext();
        ProducerRecord<String, User> rec = new ProducerRecord<String, User>("avrotopic", u.getName().toString(), u);
        try {
            pd.send(rec);
            // Capture the Future information and see which all things are reorted by Kafka
            Future<RecordMetadata> resultFuture = pd.send(rec);
            System.out.println("Avro Message sent to partition " + resultFuture.get().partition());
            System.out.println("Offset of message is " + resultFuture.get().offset());
            System.out.println("Topic of the message is " + resultFuture.get().topic());
        } catch (Exception e) {
            System.out.println("Failed to send Avro message");
            e.printStackTrace();
        }
    }
}
