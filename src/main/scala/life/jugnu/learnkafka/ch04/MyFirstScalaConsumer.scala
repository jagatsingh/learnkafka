package life.jugnu.learnkafka.ch04

import java.util.{Collections, Properties}

import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}

class MyFirstScalaConsumer {
  def main(args: Array[String]) {
    val p: Properties = new Properties
    p.put("bootstrap.servers", "")
    p.put("group.id", "")
    p.put("", "")

    val c: KafkaConsumer[String, String] = new KafkaConsumer[String, String](p)
    c.subscribe(Collections.singletonList("topic"))


    try {
      val rec: ConsumerRecords[String, String] = c.poll(100)
      import scala.collection.JavaConversions._
      for (r <- rec) {
        r.value
      }
    }
    catch {
      case e: Exception => {
        e.printStackTrace
      }
    } finally {
      c.close
    }
  }
}