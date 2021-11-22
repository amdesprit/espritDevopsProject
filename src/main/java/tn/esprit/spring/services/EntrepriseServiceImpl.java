package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tn.esprit.spring.controller.RestControlEntreprise;
import tn.esprit.spring.entities.Departement;

import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {
	
	private static final Logger log = Logger.getLogger(RestControlEntreprise.class);
	
	
	

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	
	public Entreprise ajouterEntreprise(Entreprise entreprise) {
		
		log.info("Dans ajouterEntreprise() : ");
		log.debug("Ajout de l'entreprise " + entreprise);
		
		
		try {
			entrepriseRepoistory.save(entreprise);
			
			log.debug("Ajout entreprise fait !!!");
			log.info("Sortie de ajouterEntreprise sans erreurs");
			
		} catch (Exception e) {
			log.error("Erreure dans ajouterEntreprise() : " + e);
		}
		return entreprise;
	}

	public Departement ajouterDepartement(Departement dep) {
		
    	log.info("Dans AjouterDepartement() : ");
    	log.debug("Ajouter departement " + dep);
	
		try {
			deptRepoistory.save(dep);
	    	log.debug("Departement " +dep +" ajouter");
	    	log.info("Sortie de AjouterDepartement sans erreurs");
		} catch (Exception e) {
			log.error("Erreur dans AjouterDepartement " + e);
		}
		return dep;
	}
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
	
		
		    	log.info("Dans affecterDepartementAEntreprise() : ");
		    	log.debug("Affectation du departement " + depId + " a l'entreprise " + entrepriseId);
		    	
	
				
				Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).orElse(null);
				
				
				Departement depManagedEntity = deptRepoistory.findById(depId).orElse(null);
				
				try {
					depManagedEntity.setEntreprise(entrepriseManagedEntity);
					deptRepoistory.save(depManagedEntity);
					
					log.debug("Affectation terminée !!!");  
					log.info("Sortie de affecterDepartementAEntreprise sans erreurs");
				} catch (Exception e) {
					log.error("Dans affecterDepartementAEntreprise() : "+ e);
				}
		
	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		
    	log.info("Dans getAllDepartementsNamesByEntreprise() : ");
    	log.debug("Afficher les departement de  " + entrepriseId);
		
		List<String> depNames = null;
		try {
			Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).orElse(null);
			depNames = new ArrayList<>();
			if (entrepriseManagedEntity != null) {
				
			
			for(Departement dep : entrepriseManagedEntity.getDepartements()){
				depNames.add(dep.getName());
				
				log.debug("Getall des departement fait ");
		    	log.info("Sortie de getAllDepartementsNamesByEntreprise sans erreurs");
			}
			}
		} catch (Exception e) {
			log.error("Erreur dans getAllDepartementsNamesByEntreprise " + e);
		}
		
		return depNames;
	}

	public void deleteEntrepriseById(int entrepriseId) {
    	log.info("Dans deleteEntrepriseById() : ");
    	log.debug("Suppression de l'entreprise " + entrepriseId);
    	
		try {
			entrepriseRepoistory.delete(entrepriseRepoistory.findById(entrepriseId).orElse(null));
			log.debug("Suppression de l'entreprise terminée");
			log.info("Sortie de deleteEntrepriseById sans erreurs");
		} catch (Exception e) {
			log.error("Erreur dans deleteEntrepriseById() : " + e);
		}	
	}

	public void deleteDepartementById(int depId) {
    	log.info("Dans deleteDepartementById() : ");
    	log.debug("Suppression du departement " + depId);
    	
		try {
			deptRepoistory.delete(deptRepoistory.findById(depId).orElse(null));
			log.debug("Suppression du departement terminée");
	    	log.info("Sortie de deleteDepartementById sans erreurs");
		} catch (Exception e) {
			log.error("Erreure dans deleteDepartementById " + e);
		}	
	}


	public Entreprise getEntrepriseById(int entrepriseId) {
    	log.info("Dans getEntrepriseById() : ");
    	log.debug("Get de l'entreprise " + entrepriseId);
    	
    	Entreprise ent = null;
		try {
			ent = entrepriseRepoistory.findById(entrepriseId).orElse(null);
			log.debug("Get de l'entreprise fait ");
			log.info("Sortie de getEntrepriseById sans erreurs");
		} catch (Exception e) {
			log.error("Erreure dans getEntrepriseById " + e);
		}
    	
		return ent;
	}

    @Override
    public Entreprise updateEntreprise(Entreprise entreprise) {
    	
    	log.info("Dans UpdateEntreprise() : ");
    	log.debug("Update de l'entreprise ");
   
            try {
				entrepriseRepoistory.save(entreprise);
				log.debug("Update de l'entreprise fait ");
				log.info("Sortie de UpdateEntreprise sans erreurs");
			} catch (Exception e) {
				log.error("Erreure dans UpdateEntreprise " + e);

			}
            return entreprise;                       
    }

    @Override
    public List<Entreprise> getallEntreprises() {
    	
    	log.info("Dans getallEntreprises() : ");
    	log.debug("Get de tous les entreprises ");
            List<Entreprise> entreprises = null;
			try {
				entreprises = (List<Entreprise>)entrepriseRepoistory.findAll();
				log.debug("Get de tous les entreprises fait ");
				log.info("Sortie de getallEntreprises sans erreurs");
			} catch (Exception e) {
				log.error("Erreure dans getallEntreprises " + e);

			}
            return entreprises;
    }


	

}
