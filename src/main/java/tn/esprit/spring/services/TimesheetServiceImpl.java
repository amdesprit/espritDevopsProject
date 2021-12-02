package tn.esprit.spring.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.controller.RestControlEntreprise;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.entities.TimesheetPK;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class TimesheetServiceImpl implements ITimesheetService {

	private static final Logger log = Logger.getLogger(RestControlEntreprise.class);

	@Autowired
	MissionRepository missionRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;
	@Autowired
	EmployeRepository employeRepository;
	
	public int ajouterMission(Mission mission) {

		log.info("Dans ajouterMission() : ");
		log.debug("Ajout de la mission " + mission);

		try {
			missionRepository.save(mission);

			log.debug("Ajout mission fait !!!");
			log.info("Sortie de ajouterMission sans erreurs");

		} catch (Exception e) {
			log.error("Erreure dans ajouterMission() : " + e);
		}

		return mission.getId();
	}
    
	public void affecterMissionADepartement(int missionId, int depId) {

		log.info("Dans affecterMissionADepartement() : ");
		log.debug("Affectation du mission " + missionId + " a departement " + depId);

		Mission mission = missionRepository.findById(missionId).get();
		Departement dep = deptRepoistory.findById(depId).get();

		try {
			mission.setDepartement(dep);
			missionRepository.save(mission);

			log.debug("Affectation terminée !!!");
			log.info("Sortie de affecterMissionADepartement sans erreurs");
		} catch (Exception e) {
			log.error("Dans affecterMissionADepartement() : "+ e);
		}

	}

	public boolean ajouterTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin) {
		log.info("Dans ajouterTimesheet() : ");
		log.debug("Ajouter Timesheet ");
		boolean isCreated = false;
		TimesheetPK timesheetPK = new TimesheetPK();
		timesheetPK.setDateDebut(dateDebut);
		timesheetPK.setDateFin(dateFin);
		timesheetPK.setIdEmploye(employeId);
		timesheetPK.setIdMission(missionId);
		Timesheet timesheet = new Timesheet();
		timesheet.setTimesheetPK(timesheetPK);
		timesheet.setValide(false); //par defaut non valide
		try {
			timesheetRepository.save(timesheet);
			isCreated = true;
			log.debug("Ajout Timesheet fait !!!");
			log.info("Sortie de ajouterTimesheet sans erreurs");
		} catch (Exception e) {
			log.error("Dans ajouterTimesheet() : "+ e);
		}
		return isCreated;
	}

	
	public void validerTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin, int validateurId) {

		log.info("Dans validerTimesheet() : ");
		log.debug("Valider une timesheet ");

		boolean chefDeLaMission = false;

		try {
			Employe validateur = employeRepository.findById(validateurId).get();
			Mission mission = missionRepository.findById(missionId).get();
			//verifier s'il est un chef de departement (interet des enum)
			if(!validateur.getRole().equals(Role.CHEF_DEPARTEMENT)){
				log.info("l'employe doit etre chef de departement pour valider une feuille de temps !");
				return;
			}
			//verifier s'il est le chef de departement de la mission en question
			for(Departement dep : validateur.getDepartements()){
				if(dep.getId() == mission.getDepartement().getId()){
					chefDeLaMission = true;
					break;
				}
			}
			if(!chefDeLaMission){
				log.info("l'employe doit etre chef de departement de la mission en question");
				return;
			}
			TimesheetPK timesheetPK = new TimesheetPK(missionId, employeId, dateDebut, dateFin);
			Timesheet timesheet =timesheetRepository.findBytimesheetPK(timesheetPK);
			timesheet.setValide(true);
			//Comment Lire une date de la base de données
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			log.info("dateDebut : " + dateFormat.format(timesheet.getTimesheetPK().getDateDebut()));
		}  catch (Exception e) {
			log.error("Dans ajouterTimesheet() : "+ e);
		}
		
	}

	
	public List<Mission> findAllMissionByEmployeJPQL(int employeId) {

		log.info("Dans findAllMissionByEmployeJPQL() : ");
		log.debug("Afficher les missions de  " + employeId);

		List<Mission> MissionByEmp = null;

		try {
			MissionByEmp = timesheetRepository.findAllMissionByEmployeJPQL(employeId);
			log.debug("Affichage terminée");
			log.info("Sortie de findAllMissionByEmployeJPQL() sans erreurs");
		} catch (Exception e) {
			log.error("Erreur dans findAllMissionByEmployeJPQL() : " + e);
		}
		return MissionByEmp;

	}

	
	public List<Employe> getAllEmployeByMission(int missionId) {

		log.info("Dans getAllEmployeByMission() : ");
		log.debug("Afficher les employés de  " + missionId);

		List<Employe> EmpByMission = null;

		try {
			EmpByMission = timesheetRepository.getAllEmployeByMission(missionId);
			log.debug("Affichage terminée");
			log.info("Sortie de getAllEmployeByMission() sans erreurs");
		} catch (Exception e) {
			log.error("Erreur dans getAllEmployeByMission() : " + e);
		}
		return EmpByMission;
	}

}
