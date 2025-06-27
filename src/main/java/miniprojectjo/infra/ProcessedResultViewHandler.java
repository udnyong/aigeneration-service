package miniprojectjo.infra;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import miniprojectjo.config.kafka.KafkaProcessor;
import miniprojectjo.domain.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true) 
public class ProcessedResultViewHandler {

    public static final String SUMMARY_CREATED = "SUMMARY_CREATED";
    public static final String COVER_GENERATED = "COVER_GENERATED";
    public static final String PRICED = "PRICED";
    public static final String DONE = "DONE";

    @Autowired
    private ProcessedResultRepository processedResultRepository;

    private Optional<ProcessedResult> getResultByManuscriptId(Long id) {
        return processedResultRepository.findByManuscriptId(id);
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenBookSummaryGenerate_then_CREATE_1(
        @Payload BookSummaryGenerate event
    ) {
        try {
            if (!event.validate()) return;

            ProcessedResult view = new ProcessedResult();
            view.setManuscriptId(event.getManuscriptId());
            view.setSummary(event.getSummary());
            view.setStatus(SUMMARY_CREATED);
            view.setUpdatedAt(event.getCreatedAt());

            processedResultRepository.save(view);
        } catch (Exception e) {
            log.error("Error processing BookSummaryGenerate event!!", e);
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCoverImageGenerated_then_UPDATE_1(
        @Payload CoverImageGenerated event
    ) {
        try {
            if (!event.validate()) return;

            getResultByManuscriptId(event.getManuscriptId()).ifPresent(view -> {
                view.setCoverImageUrl(event.getCoverImageUrl());
                view.setUpdatedAt(event.getCreatedAt());
                processedResultRepository.save(view);
            });
        } catch (Exception e) {
            log.error("Error processing CoverImageGenerated !!(image URL update)", e);
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCoverImageGenerated_then_UPDATE_2(
        @Payload CoverImageGenerated event
    ) {
        try {
            if (!event.validate()) return;

            getResultByManuscriptId(event.getManuscriptId()).ifPresent(view -> {
                view.setStatus(COVER_GENERATED);
                view.setUpdatedAt(event.getCreatedAt());
                processedResultRepository.save(view);
            });
        } catch (Exception e) {
            log.error("Error processing CoverImageGenerated !!(status update)", e);
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenSubscriptionFeeCalculated_then_UPDATE_3(
        @Payload SubscriptionFeeCalculated event
    ) {
        try {
            if (!event.validate()) return;

            getResultByManuscriptId(event.getManuscriptId()).ifPresent(view -> {
                view.setSubscriptionFee(event.getSubscriptionFee());
                view.setStatus(PRICED);
                view.setUpdatedAt(event.getCalculatedAt());
                processedResultRepository.save(view);
            });
        } catch (Exception e) {
            log.error("Error processing SubscriptionFeeCalculated event!!", e);
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenRegistered_then_UPDATE_4(@Payload Registered event) {
        try {
            if (!event.validate()) return;

            getResultByManuscriptId(event.getManuscriptId()).ifPresent(view -> {
                view.setStatus(DONE);
                view.setUpdatedAt(event.getCreatedAt());
                processedResultRepository.save(view);
            });
        } catch (Exception e) {
            log.error("Error processing Registered event!!", e);
        }
    }
}
