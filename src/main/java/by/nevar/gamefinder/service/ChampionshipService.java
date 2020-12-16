package by.nevar.gamefinder.service;

import by.nevar.gamefinder.models.Championship;
import by.nevar.gamefinder.models.Status;
import by.nevar.gamefinder.repository.IChampionshipRepository;
import by.nevar.gamefinder.service.serviceInterfaces.IChampionshipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ChampionshipService implements IChampionshipService {
    private final IChampionshipRepository championshipRepository;

    @Autowired
    public ChampionshipService(IChampionshipRepository championshipRepository) {
        this.championshipRepository = championshipRepository;
    }

    @Override
    public List<Championship> findAll() {
        log.info("ChampionshipService : findAll");
        return this.championshipRepository.findAll();
    }

    @Override
    public void deleteChampionshipByName(String name) {
        Championship deChampionship = this.championshipRepository.findAllByName(name).get(0);
        deChampionship.setStatus(Status.DELETED);
        this.championshipRepository.save(deChampionship);
        log.info("ChampionshipService : deleteSubjectByName");
    }

    public Championship findByName(String name){
        return championshipRepository.findByName(name);
    }

    @Override
    public void addChampionship(String name) {
        Championship championship1 = new Championship();
        championship1.setStatus(Status.ACTIVE);
        championship1.setCreated(new Date());
        championship1.setUpdated(new Date());
        championship1.setName(name);
        this.championshipRepository.save(championship1);
        log.info("ChampionshipService : addSubject");
    }

}

