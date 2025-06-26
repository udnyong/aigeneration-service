package miniprojectjo.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniprojectjo.domain.*;
import miniprojectjo.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class BookSummaryGenerate extends AbstractEvent {

    private Long id;
    private Long manuscriptId;
    private String summary;
    private Date createdAt;

    public BookSummaryGenerate(AiBookGeneration aggregate) {
        super(aggregate);
    }

    public BookSummaryGenerate() {
        super();
    }
}
//>>> DDD / Domain Event
