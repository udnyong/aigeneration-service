package miniprojectjo.infra;

import java.util.List;
import miniprojectjo.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "processedResults",
    path = "processedResults"
)
public interface ProcessedResultRepository
    extends PagingAndSortingRepository<ProcessedResult, Long> {}
