package de.mibexx.demo.twitter.to.kafka.service;

import de.mibexx.demo.twitter.to.kafka.service.config.TwitterToKafkaServiceConfigData;
import de.mibexx.demo.twitter.to.kafka.service.runner.impl.TwitterKafkaStreamRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class TwitterToKafkaServiceApplication implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(TwitterToKafkaServiceApplication.class);

    private final TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData;
    private final TwitterKafkaStreamRunner twitterKafkaStreamRunner;

    public TwitterToKafkaServiceApplication(
            TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData,
            TwitterKafkaStreamRunner twitterKafkaStreamRunner
    ) {
        this.twitterToKafkaServiceConfigData = twitterToKafkaServiceConfigData;
        this.twitterKafkaStreamRunner = twitterKafkaStreamRunner;
    }

    public static void main(String[] args) {
        SpringApplication.run(TwitterToKafkaServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("App starts...");
        LOG.info(Arrays.toString(twitterToKafkaServiceConfigData.getTwitterKeywords().toArray(new String[] {})));
        LOG.info(twitterToKafkaServiceConfigData.getWelcomeMessage());

        twitterKafkaStreamRunner.start();
    }
}
