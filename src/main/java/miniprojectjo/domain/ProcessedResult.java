package miniprojectjo.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

//<<< EDA / CQRS
@Entity
@Table(name = "ProcessedResult_table")
@Data
public class ProcessedResult {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long manuscriptId;

    private String summary;
    private String coverImageUrl;
    private Integer subscriptionFee;
    private String status;
    private Date updatedAt;
}
