package miniprojectjo.infra;
import java.util.Optional;
import miniprojectjo.domain.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "processedResults",
    path = "processedResults"
)
public interface ProcessedResultRepository
    extends JpaRepository<ProcessedResult, Long> {
        Optional<ProcessedResult> findByManuscriptId(Long manuscriptId);
    }
