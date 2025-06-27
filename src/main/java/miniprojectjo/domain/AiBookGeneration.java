package miniprojectjo.domain;

import java.util.Date;
import javax.persistence.*;

import lombok.Data;
import miniprojectjo.AigenerationApplication;
// import miniprojectjo.domain.AiImageService; // 위치 확인!
// import miniprojectjo.domain.*;

@Entity
@Table(name = "AiBookGeneration_table")
@Data
public class AiBookGeneration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long manuscriptId;
    private String summary;
    private String coverImageUrl;
    private Integer subscriptionFee;
    private String status;
    private Date createdAt;
    private Date updatedAt;

    public static AiBookGenerationRepository repository() {
        return AigenerationApplication.applicationContext.getBean(AiBookGenerationRepository.class);
    }

    private static AiImageService aiImageService() {
        return AigenerationApplication.applicationContext.getBean(AiImageService.class);
    }

    // 1. 도서 요약 생성 요청
    public static void generateBookSummary(PublishingRequested event) {
        AiBookGeneration entity = new AiBookGeneration();
        entity.setManuscriptId(event.getId());

        // ✅ 요약 생성 API 호출
        String summary = aiImageService().generateSummary(event.getContent());
        entity.setSummary(summary);
        entity.setStatus("SUMMARY_CREATED");
        entity.setCreatedAt(new Date());
        entity.setUpdatedAt(new Date());
        repository().save(entity);

        BookSummaryGenerate published = new BookSummaryGenerate(entity);
        published.setCreatedAt(new Date());
        published.publishAfterCommit();
    }

    // 2. 표지 이미지 생성 요청
    public static void generateCoverImage(PublishingRequested event) {
        repository().findByManuscriptId(event.getId()).ifPresent(entity -> {
            // ✅ 이미지 생성 API 호출
            String imageUrl = aiImageService().generateCoverImage("책 제목: " + event.getTitle());
            entity.setCoverImageUrl(imageUrl);
            entity.setStatus("COVER_GENERATED");
            entity.setUpdatedAt(new Date());
            repository().save(entity);

            CoverImageGenerated published = new CoverImageGenerated(entity);
            published.publishAfterCommit();
        });
    }

    // 3. 요약 완료 후 등록 처리
    public static void registerProcessedBook(BookSummaryGenerate event) {
        repository().findByManuscriptId(event.getManuscriptId()).ifPresent(entity -> {
            entity.setUpdatedAt(new Date());
            repository().save(entity);

            Registered published = new Registered(entity);
            published.setCreatedAt(new Date());
            published.publishAfterCommit();
        });
    }

    // 4. 표지 완료 후 등록 처리
    public static void registerProcessedBook(CoverImageGenerated event) {
        repository().findByManuscriptId(event.getManuscriptId()).ifPresent(entity -> {
            entity.setUpdatedAt(new Date());
            repository().save(entity);

            Registered published = new Registered(entity);
            published.setCreatedAt(new Date());
            published.publishAfterCommit();
        });
    }

    // 5. 등록 완료 후 구독료 책정
    public static void subscriptionFeePolicy(Registered event) {
        repository().findByManuscriptId(event.getManuscriptId()).ifPresent(entity -> {
            entity.setSubscriptionFee(3900); // 예시 구독료
            entity.setStatus("PRICED");
            entity.setUpdatedAt(new Date());
            repository().save(entity);

            SubscriptionFeeCalculated published = new SubscriptionFeeCalculated(entity);
            published.setCalculatedAt(new Date());
            published.setCriteria("SUMMARY+IMAGE_LENGTH");
            published.publishAfterCommit();
        });
    }
}
