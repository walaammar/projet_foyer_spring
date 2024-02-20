package tn.esprit.foyer.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.foyer.entities.Bloc;
import tn.esprit.foyer.entities.Chambre;
import tn.esprit.foyer.repository.BlocRepository;
import tn.esprit.foyer.repository.ChambreRepository;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class BlocServiceImpl implements IBlocService{


    BlocRepository blocRepository;
    ChambreRepository chambreRepository;

    @Override
    public List<Bloc> retrieveAllBlocs() {
        return blocRepository.findAll();
    }

    @Override
    public Bloc addBloc(Bloc b) {
        return blocRepository.save(b);
    }

    @Override
    public Bloc updateBloc(Bloc b) {
        return blocRepository.save(b);
    }

    @Override
    public Bloc retrieveBloc(Long idBloc) {
        return blocRepository.findById(idBloc).orElse(null);
    }

    @Override
    public void removeBloc(Long idBloc) {
        log.debug("debugging");
        blocRepository.deleteById(idBloc);

    }

    @Override
    public List<Bloc> findByFoyerUniversiteIdUniversite(Long idUniversite) {
      //  return blocRepository.findByFoyerUniversiteIdUniversite(idUniversite);
          return  blocRepository.findByFoyerUniversite(idUniversite);
    }
    @Override
    public Bloc affecterChambresABloc (List<Long> numChambre, String nomBloc) {
        Bloc bloc = blocRepository.findByNomBloc(nomBloc);
        // ou bien tu récupére la liste de tous les chambres par keyword
        // List<Chambre> findByNumChambre(List<Long> numChambre); chambreRepo
        numChambre.stream().forEach(
                chambreNumber -> {
                    Chambre c = chambreRepository.findByNumeroChambre(chambreNumber);
                    c.setBloc(bloc);
                    chambreRepository.save(c);
                }
        );
        return bloc;
    }

  //  @Scheduled(fixedRate = 60000)
    public void fixedRateMethod() {
        log.info("Method scheduler with fixed Rate");
    }











  //  @Scheduled(fixedRate = 60000)
    public void listeChambresParBloc() {
     List<Bloc> blocs = blocRepository.findAll();
     blocs.stream().forEach(
             bloc -> {
                 if(bloc.getChambres()!=null)
                 {
                     log.info("bloc : "+ bloc.getNomBloc()+ " ayant une capacité de : "+bloc.getCapaciteBloc());
                     log.info("Liste des chambres du bloc "+bloc.getNomBloc()+ " : ");
                     bloc.getChambres().stream().forEach(
                             chambre -> {
                                 log.info("chambre numéro "+ chambre.getNumeroChambre()+" de type "+chambre.getTypeC());
                             }
                     );
                 }
                 else
                 {
                     log.info("bloc : "+ bloc.getNomBloc()+ " ayant une capacité de : "+bloc.getCapaciteBloc());
                     log.info("bloc vide pour le moment");

                 }
             }
     );
    }

}
