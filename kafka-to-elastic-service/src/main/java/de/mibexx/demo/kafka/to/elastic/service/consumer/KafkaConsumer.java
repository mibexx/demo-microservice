package de.mibexx.demo.kafka.to.elastic.service.consumer;

import de.mibexx.demo.kafka.avro.model.TwitterAvroModel;
import org.apache.avro.specific.SpecificRecordBase;

import java.io.Serializable;
import java.util.List;

public interface KafkaConsumer<K extends Serializable, V extends SpecificRecordBase> {
    public void receive(List<TwitterAvroModel> messages, List<Integer> keys, List<Integer> partitions, List<Long> offsets);
}
