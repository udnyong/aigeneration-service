package miniprojectjo.infra;

import miniprojectjo.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class AiBookGenerationHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<AiBookGeneration>> {

    @Override
    public EntityModel<AiBookGeneration> process(
        EntityModel<AiBookGeneration> model
    ) {
        return model;
    }
}
