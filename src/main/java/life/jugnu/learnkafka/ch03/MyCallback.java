package life.jugnu.learnkafka.ch03;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

class MyCallback implements Callback {

    public void onCompletion(RecordMetadata metadata, Exception exception) {
        if ( exception != null)
            exception.printStackTrace();
        else
           System.out.println("Message posted call back success");
    }
}