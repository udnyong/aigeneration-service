package miniprojectjo.domain;

import java.util.Date;
import lombok.*;
import miniprojectjo.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
@NoArgsConstructor
public class CoverImageGenerated extends AbstractEvent {

    private Long id;
    private Long manuscriptId;
    private String coverImageUrl;
    private Date createdAt;

    public CoverImageGenerated(AiBookGeneration aggregate) {
        super(aggregate);
        this.id = aggregate.getId();
        this.manuscriptId = aggregate.getManuscriptId();
        this.coverImageUrl = aggregate.getCoverImageUrl();
        this.createdAt = new Date(); // 그대로 두는 것도 OK
    }
}
//>>> DDD / Domain Event
