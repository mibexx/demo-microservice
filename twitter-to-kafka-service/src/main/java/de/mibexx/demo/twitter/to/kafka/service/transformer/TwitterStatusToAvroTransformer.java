package de.mibexx.demo.twitter.to.kafka.service.transformer;

import de.mibexx.demo.kafka.avro.model.TwitterAvroModel;
import twitter4j.Status;

public class TwitterStatusToAvroTransformer {
    public TwitterAvroModel getTwitterAvroModelFromStatus(Status status) {
        return TwitterAvroModel
                .newBuilder()
                .setId(status.getId())
                .setUserId(status.getUser().getId())
                .setText(status.getText())
                .setCreatedAt(status.getCreatedAt().getTime())
                .build();
    }
}
