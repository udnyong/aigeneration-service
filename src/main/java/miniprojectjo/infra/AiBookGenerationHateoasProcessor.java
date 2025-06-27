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
    public EntityModel<AiBookGeneration> process(EntityModel<AiBookGeneration> model) {
        AiBookGeneration entity = model.getContent();

        if (entity != null && entity.getId() != null) {
            Long id = entity.getId();

            model.add(Link.of("/aiBookGenerations/" + id + "/generate-summary").withRel("generate-summary"));
            model.add(Link.of("/aiBookGenerations/" + id + "/generate-cover").withRel("generate-cover"));
            model.add(Link.of("/aiBookGenerations/" + id + "/calculate-subscription-fee").withRel("calculate-subscription-fee"));
            model.add(Link.of("/aiBookGenerations/" + id + "/register").withRel("register-book"));
        }

        return model;
    }
}
