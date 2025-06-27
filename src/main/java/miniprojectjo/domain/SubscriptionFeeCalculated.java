package miniprojectjo.domain;

import lombok.*;
import miniprojectjo.infra.AbstractEvent;

import java.util.Date;

//<<< DDD / Domain Event
@Data
@ToString
public class SubscriptionFeeCalculated extends AbstractEvent {

    private Long manuscriptId;
    private Integer subscriptionFee;
    private String criteria;     // 예: "summaryLength+image", 필요 시 유지
    private Date calculatedAt;

    public SubscriptionFeeCalculated(AiBookGeneration aggregate) {
        super(aggregate);
    }

    public SubscriptionFeeCalculated() {
        super();
    }
}
//>>> DDD / Domain Event
