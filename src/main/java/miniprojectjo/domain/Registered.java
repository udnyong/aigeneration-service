package miniprojectjo.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniprojectjo.domain.*;
import miniprojectjo.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class Registered extends AbstractEvent {

    private Long id;
    private Long manuscriptId;
    private String summary;
    private String coverImageUrl;
    private Date createdAt;

    public Registered(AiBookGeneration aggregate) {
        super(aggregate);
    }

    public Registered() {
        super();
    }
}
//>>> DDD / Domain Event
