package miniprojectjo.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniprojectjo.domain.*;
import miniprojectjo.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class SubscriptionFeeCalculated extends AbstractEvent {

    private Long id;
    private Long manuscriptId;
    private Integer subscriptionFee;
    private String criteria;
    private Date calculatedAt;

    public SubscriptionFeeCalculated(AiBookGeneration aggregate) {
        super(aggregate);
    }

    public SubscriptionFeeCalculated() {
        super();
    }
}
//>>> DDD / Domain Event
