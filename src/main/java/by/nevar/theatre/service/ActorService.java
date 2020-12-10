package by.nevar.theatre.service;

import by.nevar.theatre.exceptions.ActorNotFoundException;
import by.nevar.theatre.forms.ActorForm;
import by.nevar.theatre.models.Actor;
import by.nevar.theatre.repository.IActorRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ActorService {
    @Autowired
    private IActorRepository actorRepository;

    @Autowired
    public ActorService() {
    }

    public Actor loadActorByName(String name) throws ActorNotFoundException {
        Actor actor = actorRepository.findByName(name);

        if (actor == null) {
            throw new ActorNotFoundException("Actor with name: " + name + " not found");
        }

        return actor;
    }

    public Actor FromActorForm(ActorForm form) {
        Actor actor = new Actor();
        if (form == null) {
            return actor;
        }
        if (form.getId() != null) {
            actor.setId(form.getId());
        }
        if (form.getName() != "" && form.getName() != null) {
            actor.setName(form.getName());
        }

        return actor;
    }

    public Actor AddNewActor(Actor actor) {
        Actor actorFromDB = actorRepository.findByName(actor.getName());
        if (actorFromDB != null) {
            return null;
        }
        Actor newActor = new Actor();
        newActor.setName(actor.getName());
        actorRepository.save(newActor);
        return newActor;
    }

    public Actor EditActor(Actor actor) {
        Actor actorFromDB = actorRepository.findById(actor.getId()).get();
        if (actorFromDB == null) {
            return null;
        }
        actorRepository.save(actor);
        return actor;
    }

    public void DeleteActor(Actor actor) {
        Integer actorId = actorRepository.findById(actor.getId()).get().getId();
        if (actorId != null) {
            actorRepository.deleteById(actorId);
        }
    }
}
