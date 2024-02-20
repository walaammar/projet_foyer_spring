package tn.esprit.foyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.foyer.entities.Chambre;
import tn.esprit.foyer.entities.Foyer;

public interface FoyerRepository extends JpaRepository<Foyer,Long> {

    Foyer findByNomFoyer(String nomFoyer);




}
