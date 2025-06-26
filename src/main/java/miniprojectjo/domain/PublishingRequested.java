package miniprojectjo.domain;

import java.util.*;
import lombok.*;
import miniprojectjo.domain.*;
import miniprojectjo.infra.AbstractEvent;

@Data
@ToString
public class PublishingRequested extends AbstractEvent {

    private Long id;
    private String title;
    private Object authorId;
    private Object status;
    private String content;
}
