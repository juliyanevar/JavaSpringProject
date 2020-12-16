package by.nevar.gamefinder.repository;

import by.nevar.gamefinder.models.Championship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IChampionshipRepository extends JpaRepository<Championship, Integer> {
    List<Championship> findAllByName(String name);
    Championship findByName(String name);
    <S extends Championship> S save(S s);
}
