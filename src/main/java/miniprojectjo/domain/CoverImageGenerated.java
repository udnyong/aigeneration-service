package miniprojectjo.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniprojectjo.domain.*;
import miniprojectjo.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class CoverImageGenerated extends AbstractEvent {

    private Long id;
    private Long manuscriptId;
    private String coverImageUrl;
    private Date createdAt;

    public CoverImageGenerated(AiBookGeneration aggregate) {
        super(aggregate);
    }

    public CoverImageGenerated() {
        super();
    }
}
//>>> DDD / Domain Event
