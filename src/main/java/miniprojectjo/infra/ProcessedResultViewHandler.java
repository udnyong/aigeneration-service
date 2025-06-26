package miniprojectjo.infra;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import miniprojectjo.config.kafka.KafkaProcessor;
import miniprojectjo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ProcessedResultViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private ProcessedResultRepository processedResultRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenBookSummaryGenerate_then_CREATE_1(
        @Payload BookSummaryGenerate bookSummaryGenerate
    ) {
        try {
            if (!bookSummaryGenerate.validate()) return;

            // view 객체 생성
            ProcessedResult processedResult = new ProcessedResult();
            // view 객체에 이벤트의 Value 를 set 함
            processedResult.setManuscriptId(
                bookSummaryGenerate.getManuscriptId()
            );
            processedResult.setSummary(bookSummaryGenerate.getSummary());
            processedResult.setStatus(SUMMARY_CREATED);
            processedResult.setUpdatedAt(bookSummaryGenerate.getCreatedAt());
            // view 레파지 토리에 save
            processedResultRepository.save(processedResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCoverImageGenerated_then_UPDATE_1(
        @Payload CoverImageGenerated coverImageGenerated
    ) {
        try {
            if (!coverImageGenerated.validate()) return;
            // view 객체 조회
            Optional<ProcessedResult> processedResultOptional = processedResultRepository.findByManuscriptId(
                coverImageGenerated.getManuscriptId()
            );

            if (processedResultOptional.isPresent()) {
                ProcessedResult processedResult = processedResultOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                processedResult.setCoverImageUrl(
                    coverImageGenerated.getCoverImageUrl()
                );
                processedResult.setUpdatedAt(
                    coverImageGenerated.getCreatedAt()
                );
                // view 레파지 토리에 save
                processedResultRepository.save(processedResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCoverImageGenerated_then_UPDATE_2(
        @Payload CoverImageGenerated coverImageGenerated
    ) {
        try {
            if (!coverImageGenerated.validate()) return;
            // view 객체 조회
            Optional<ProcessedResult> processedResultOptional = processedResultRepository.findByManuscriptId(
                coverImageGenerated.getManuscriptId()
            );

            if (processedResultOptional.isPresent()) {
                ProcessedResult processedResult = processedResultOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                processedResult.setStatus(COVER_GENERATED);
                processedResult.setUpdatedAt(
                    coverImageGenerated.getCreatedAt()
                );
                // view 레파지 토리에 save
                processedResultRepository.save(processedResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenSubscriptionFeeCalculated_then_UPDATE_3(
        @Payload SubscriptionFeeCalculated subscriptionFeeCalculated
    ) {
        try {
            if (!subscriptionFeeCalculated.validate()) return;
            // view 객체 조회
            Optional<ProcessedResult> processedResultOptional = processedResultRepository.findByManuscriptId(
                subscriptionFeeCalculated.getManuscriptId()
            );

            if (processedResultOptional.isPresent()) {
                ProcessedResult processedResult = processedResultOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                processedResult.setSubscriptionFee(
                    subscriptionFeeCalculated.getSubscriptionFee()
                );
                processedResult.setStatus(PRICED);
                processedResult.setUpdatedAt(
                    subscriptionFeeCalculated.getCalculatedAt()
                );
                // view 레파지 토리에 save
                processedResultRepository.save(processedResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenRegistered_then_UPDATE_4(@Payload Registered registered) {
        try {
            if (!registered.validate()) return;
            // view 객체 조회
            Optional<ProcessedResult> processedResultOptional = processedResultRepository.findByManuscriptId(
                registered.getManuscriptId()
            );

            if (processedResultOptional.isPresent()) {
                ProcessedResult processedResult = processedResultOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                processedResult.setStatus(DONE);
                processedResult.setUpdatedAt(registered.getCreatedAt());
                // view 레파지 토리에 save
                processedResultRepository.save(processedResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
