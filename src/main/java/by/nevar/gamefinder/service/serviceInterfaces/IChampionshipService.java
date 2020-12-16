package by.nevar.gamefinder.service.serviceInterfaces;

import by.nevar.gamefinder.models.Championship;

import java.util.List;

public interface IChampionshipService {
    List<Championship> findAll();
    void deleteChampionshipByName(String name);
    void addChampionship(String name);
}
