package miniprojectjo.domain;

import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "aiBookGenerations",
    path = "aiBookGenerations"
)
public interface AiBookGenerationRepository
    extends PagingAndSortingRepository<AiBookGeneration, Long> {

    Optional<AiBookGeneration> findByManuscriptId(Long manuscriptId);
}
