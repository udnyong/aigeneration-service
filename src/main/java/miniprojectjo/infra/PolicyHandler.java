package miniprojectjo.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import miniprojectjo.config.kafka.KafkaProcessor;
import miniprojectjo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    AiBookGenerationRepository aiBookGenerationRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PublishingRequested'"
    )
    public void wheneverPublishingRequested_GenerateBookSummary(
        @Payload PublishingRequested publishingRequested
    ) {
        PublishingRequested event = publishingRequested;
        System.out.println(
            "\n\n##### listener GenerateBookSummary : " +
            publishingRequested +
            "\n\n"
        );

        // Sample Logic //
        AiBookGeneration.generateBookSummary(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PublishingRequested'"
    )
    public void wheneverPublishingRequested_GenerateCoverImage(
        @Payload PublishingRequested publishingRequested
    ) {
        PublishingRequested event = publishingRequested;
        System.out.println(
            "\n\n##### listener GenerateCoverImage : " +
            publishingRequested +
            "\n\n"
        );

        // Sample Logic //
        AiBookGeneration.generateCoverImage(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BookSummaryGenerate'"
    )
    public void wheneverBookSummaryGenerate_RegisterProcessedBook(
        @Payload BookSummaryGenerate bookSummaryGenerate
    ) {
        BookSummaryGenerate event = bookSummaryGenerate;
        System.out.println(
            "\n\n##### listener RegisterProcessedBook : " +
            bookSummaryGenerate +
            "\n\n"
        );

        // Sample Logic //
        AiBookGeneration.registerProcessedBook(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CoverImageGenerated'"
    )
    public void wheneverCoverImageGenerated_RegisterProcessedBook(
        @Payload CoverImageGenerated coverImageGenerated
    ) {
        CoverImageGenerated event = coverImageGenerated;
        System.out.println(
            "\n\n##### listener RegisterProcessedBook : " +
            coverImageGenerated +
            "\n\n"
        );

        // Sample Logic //
        AiBookGeneration.registerProcessedBook(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Registered'"
    )
    public void wheneverRegistered_SubscriptionFeePolicy(
        @Payload Registered registered
    ) {
        Registered event = registered;
        System.out.println(
            "\n\n##### listener SubscriptionFeePolicy : " + registered + "\n\n"
        );

        // Comments //
        //AI 기반 도서 요약, 이미지 등을 바탕으로 구독료를 자동으로 계산하는 정책

        // Sample Logic //
        AiBookGeneration.subscriptionFeePolicy(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
