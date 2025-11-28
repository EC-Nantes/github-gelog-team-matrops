package org.centrale.hceres.repository;
import java.util.List;
import org.centrale.hceres.items.Researcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// permet d'executer les requetes sur la base de donnees
@Repository
public interface ResearchRepository extends JpaRepository<Researcher, Integer> {
    @Query("SELECT DISTINCT r FROM Researcher r " +
           "LEFT JOIN FETCH r.publications " +
           "LEFT JOIN FETCH r.activities " +
           "LEFT JOIN FETCH r.projects")
    @Override
    List<Researcher> findAll();

}
