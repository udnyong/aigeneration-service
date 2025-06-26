package miniprojectjo.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import miniprojectjo.AigenerationApplication;
import miniprojectjo.domain.BookSummaryGenerate;
import miniprojectjo.domain.CoverImageGenerated;
import miniprojectjo.domain.Registered;
import miniprojectjo.domain.SubscriptionFeeCalculated;

@Entity
@Table(name = "AiBookGeneration_table")
@Data
//<<< DDD / Aggregate Root
public class AiBookGeneration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long manuscriptId;

    private String summary;

    private String coverImageUrl;

    private String status;

    private Date createdAt;

    private Date updatedAt;

    public static AiBookGenerationRepository repository() {
        AiBookGenerationRepository aiBookGenerationRepository = AigenerationApplication.applicationContext.getBean(
            AiBookGenerationRepository.class
        );
        return aiBookGenerationRepository;
    }

    //<<< Clean Arch / Port Method
    public static void generateBookSummary(
        PublishingRequested publishingRequested
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        AiBookGeneration aiBookGeneration = new AiBookGeneration();
        repository().save(aiBookGeneration);

        BookSummaryGenerate bookSummaryGenerate = new BookSummaryGenerate(aiBookGeneration);
        bookSummaryGenerate.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if publishingRequested.authorId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> manuscriptMap = mapper.convertValue(publishingRequested.getAuthorId(), Map.class);

        repository().findById(publishingRequested.get???()).ifPresent(aiBookGeneration->{
            
            aiBookGeneration // do something
            repository().save(aiBookGeneration);

            BookSummaryGenerate bookSummaryGenerate = new BookSummaryGenerate(aiBookGeneration);
            bookSummaryGenerate.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void generateCoverImage(
        PublishingRequested publishingRequested
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        AiBookGeneration aiBookGeneration = new AiBookGeneration();
        repository().save(aiBookGeneration);

        CoverImageGenerated coverImageGenerated = new CoverImageGenerated(aiBookGeneration);
        coverImageGenerated.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if publishingRequested.authorId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> manuscriptMap = mapper.convertValue(publishingRequested.getAuthorId(), Map.class);

        repository().findById(publishingRequested.get???()).ifPresent(aiBookGeneration->{
            
            aiBookGeneration // do something
            repository().save(aiBookGeneration);

            CoverImageGenerated coverImageGenerated = new CoverImageGenerated(aiBookGeneration);
            coverImageGenerated.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void registerProcessedBook(
        BookSummaryGenerate bookSummaryGenerate
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        AiBookGeneration aiBookGeneration = new AiBookGeneration();
        repository().save(aiBookGeneration);

        Registered registered = new Registered(aiBookGeneration);
        registered.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(bookSummaryGenerate.get???()).ifPresent(aiBookGeneration->{
            
            aiBookGeneration // do something
            repository().save(aiBookGeneration);

            Registered registered = new Registered(aiBookGeneration);
            registered.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void registerProcessedBook(
        CoverImageGenerated coverImageGenerated
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        AiBookGeneration aiBookGeneration = new AiBookGeneration();
        repository().save(aiBookGeneration);

        Registered registered = new Registered(aiBookGeneration);
        registered.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(coverImageGenerated.get???()).ifPresent(aiBookGeneration->{
            
            aiBookGeneration // do something
            repository().save(aiBookGeneration);

            Registered registered = new Registered(aiBookGeneration);
            registered.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void subscriptionFeePolicy(Registered registered) {
        //implement business logic here:

        /** Example 1:  new item 
        AiBookGeneration aiBookGeneration = new AiBookGeneration();
        repository().save(aiBookGeneration);

        SubscriptionFeeCalculated subscriptionFeeCalculated = new SubscriptionFeeCalculated(aiBookGeneration);
        subscriptionFeeCalculated.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(registered.get???()).ifPresent(aiBookGeneration->{
            
            aiBookGeneration // do something
            repository().save(aiBookGeneration);

            SubscriptionFeeCalculated subscriptionFeeCalculated = new SubscriptionFeeCalculated(aiBookGeneration);
            subscriptionFeeCalculated.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
