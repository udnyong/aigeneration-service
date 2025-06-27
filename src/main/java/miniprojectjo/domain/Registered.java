package miniprojectjo.domain;

import lombok.*;
import miniprojectjo.infra.AbstractEvent;

import java.util.Date;

//<<< DDD / Domain Event
@Data
@ToString
public class Registered extends AbstractEvent {

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
