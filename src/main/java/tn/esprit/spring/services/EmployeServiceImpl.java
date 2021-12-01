package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.controller.RestControlEmploye;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class EmployeServiceImpl implements IEmployeService {
	
	private static final Logger log = Logger.getLogger(RestControlEmploye.class);


	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;

	public int ajouterEmploye(Employe employe) {
		log.info("Dans ajouterEmploye() : ");
		log.debug("Ajout de l'emplyé " + employe);
		try {
			employeRepository.save(employe);
			log.debug("Ajout Employé fait !!!");
			log.info("Sortie de ajouterEmployé sans erreurs");
			
		} catch (Exception e) {
			log.error("Erreure dans ajouterEmploye() : " + e);
		}

		return employe.getId();
		
	}
	
	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		   log.info("** start  mettreAjourEmailByEmployeId : ");
	        try {
	        	Employe employe = employeRepository.findById(employeId).get();
	    		employe.setEmail(email);
	    		employeRepository.save(employe);
	            log.debug(" -- N° 2 : the employee :" + employeId +" updated successfully " );
	            log.info("** end  mettreAjourEmailByEmployeId without error ");
	        }catch (Exception e ){
	            log.info("** end  mettreAjourEmailByEmployeId with error : "+e);
	        }
		
	}
	
	public void affecterEmployeADepartement(int employeId, int depId) {
		log.info("Dans affecterEmployeADepartement() : ");
    	log.debug("Affectation du Departement " + depId + " a l'Employe " + employeId);
		Departement depManagedEntity = deptRepoistory.findById(depId).get();
		Employe employeManagedEntity = employeRepository.findById(employeId).get();

		if(depManagedEntity.getEmployes() == null){
			List<Employe> employes = new ArrayList<>();
			try {
				employes.add(employeManagedEntity);
				depManagedEntity.setEmployes(employes);
				log.debug("Affectation terminée !!!");  
				log.info("Sortie de affecterEmployeADepartement sans erreurs");
				
			} catch (Exception e) {
				log.error("Dans affecterEmployeADepartement() : "+ e);
			}
			
		}else{

			try {
				depManagedEntity.getEmployes().add(employeManagedEntity);
				
			}catch(Exception exp){
				log.error("Dans affecterEmployeADepartement() : "+ exp);
			}
			

		}

	}
	@Transactional
	//supprimer l'employe du departement
	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		log.info("Dans desaffecterEmployeDuDepartement() : ");
    	
		Departement dep = deptRepoistory.findById(depId).get();

		int employeNb = dep.getEmployes().size();
		for(int index = 0; index < employeNb; index++){
			if(dep.getEmployes().get(index).getId() == employeId){
				try {
					dep.getEmployes().remove(index);
					log.debug("desaffectation terminée !!!");  
					log.info("Sortie de desaffecterEmployeDuDepartement sans erreurs");
					
				}catch(Exception exp){
					log.error("Dans desaffecterEmployeDuDepartement() : "+ exp);
				}
			
			}
		}
	}

	public int ajouterContrat(Contrat contrat) {
		log.info("Dans ajouterContrat() : ");
		log.debug("Ajout du contrat " + contrat);
		
		try {
			contratRepoistory.save(contrat);
			
			log.debug("Ajout Contrat fait !!!");
			log.info("Sortie de ajouterContrat sans erreurs");
			
		} catch (Exception e) {
			log.error("Erreure dans ajouterContrat() : " + e);
		}
		
		return contrat.getReference();
	}

	public void affecterContratAEmploye(int contratId, int employeId) {
		log.info("Dans affecterContratAEmploye() : ");
		log.debug("affecter du contrat " + contratId + "à l'employé" + employeId );
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).get();
		Employe employeManagedEntity = employeRepository.findById(employeId).get();
		try {
			contratManagedEntity.setEmploye(employeManagedEntity);
			contratRepoistory.save(contratManagedEntity);
			log.debug("affectation du Contrat fait !!!");
			log.info("Sortie de affecterContratAEmploye sans erreurs");
			
		} catch (Exception e) {
			log.error("Erreure dans affecterContratAEmploye() : " + e);
		}
		
	}

	public String getEmployePrenomById(int employeId) {
		Employe employeManagedEntity = employeRepository.findById(employeId).get();
		return employeManagedEntity.getPrenom();
	}
	public void deleteEmployeById(int employeId)
	{
		log.info("Dans deleteEmployeById() : ");
		log.debug("supperssion de l employé " + employeId);
		
		Employe employe = employeRepository.findById(employeId).get();

		//Desaffecter l'employe de tous les departements
		//c'est le bout master qui permet de mettre a jour
		//la table d'association
		for(Departement dep : employe.getDepartements()){
			try {
				dep.getEmployes().remove(employe);
				
				log.debug("suppression employé fait !!!");
				log.info("désaffectation de l'employé");
				
			} catch (Exception e) {
				log.error("Erreure : " + e);
			}
			
		}
		try {
			employeRepository.delete(employe);
			log.debug("suppression employé fait !!!");
			log.info("Sortie de deleteEmployeById sans erreurs");
			
		} catch (Exception e) {
			log.error("Erreure dans deleteEmployeById() : " + e);
		}
	}

	public void deleteContratById(int contratId) {
		log.info("Dans deleteEmployeById() : ");
		log.debug("supperssion de l employé " + contratId);
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).get();
		try {
			contratRepoistory.delete(contratManagedEntity);
			log.debug("suppression faite !!!");
			log.info("Sortie de deleteContratById sans erreurs");
		} catch (Exception e) {
			log.error("Erreure dans deleteContratById() : " + e);
		}
	}

	public int getNombreEmployeJPQL() {
		return employeRepository.countemp();
	}
	
	public List<String> getAllEmployeNamesJPQL() {
		return employeRepository.employeNames();

	}
	
	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		return employeRepository.getAllEmployeByEntreprisec(entreprise);
	}

	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);

	}
	public void deleteAllContratJPQL() {
         employeRepository.deleteAllContratJPQL();
	}
	
	public float getSalaireByEmployeIdJPQL(int employeId) {
		
		log.info("Dans getSalaireByEmployeIdJPQL() : ");
    	log.debug("Afficher salaire de l'employe " + employeId);
		
		float salaire = 0;
		try {
			salaire = employeRepository.getSalaireByEmployeIdJPQL(employeId);
	    	log.debug("getSalaireByEmployeIdJPQL fait ");
	    	log.info("Sortie de getSalaireByEmployeIdJPQL sans erreurs ");
		} catch (Exception e) {
			log.error("Erreur dans getSalaireByEmployeIdJPQL " + e);
		}
		
		return salaire; 
	}

	public Double getSalaireMoyenByDepartementId(int departementId) {
		return employeRepository.getSalaireMoyenByDepartementId(departementId);
	}
	
	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		return timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}

	public List<Employe> getAllEmployes() {
				return (List<Employe>) employeRepository.findAll();
	}

}
