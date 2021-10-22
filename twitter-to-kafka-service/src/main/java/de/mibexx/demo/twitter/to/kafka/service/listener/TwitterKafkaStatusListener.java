package de.mibexx.demo.twitter.to.kafka.service.listener;

import de.mibexx.demo.config.KafkaConfigData;
import de.mibexx.demo.kafka.avro.model.TwitterAvroModel;
import de.mibexx.demo.kafka.producer.config.service.KafkaProducer;
import de.mibexx.demo.twitter.to.kafka.service.transformer.TwitterStatusToAvroTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.StatusAdapter;

@Component
public class TwitterKafkaStatusListener extends StatusAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(TwitterKafkaStatusListener.class);

    private final KafkaConfigData kafkaConfigData;
    private final KafkaProducer<Long, TwitterAvroModel> kafkaProducer;
    private final TwitterStatusToAvroTransformer twitterStatusToAvroTransformer;

    public TwitterKafkaStatusListener(
            KafkaConfigData kafkaConfigData,
            KafkaProducer<Long, TwitterAvroModel> kafkaProducer,
            TwitterStatusToAvroTransformer twitterStatusToAvroTransformer
    ) {
        this.kafkaConfigData = kafkaConfigData;
        this.kafkaProducer = kafkaProducer;
        this.twitterStatusToAvroTransformer = twitterStatusToAvroTransformer;
    }

    @Override
    public void onStatus(Status status) {
        LOG.info("Twitter status with text {}", status.getText());
        TwitterAvroModel twitterAvroModel = twitterStatusToAvroTransformer.getTwitterAvroModelFromStatus(status);
        kafkaProducer.send(kafkaConfigData.getTopicName(), twitterAvroModel.getUserId(), twitterAvroModel);
    }
}
