package tn.esprit.spring.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repository.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeServiceImpl implements IEmployeService {

    private static final Logger log = Logger.getLogger(IEmployeService.class);

    EmployeRepository employeRepository;
    DepartementRepository deptRepoistory;
    ContratRepository contratRepoistory;
    TimesheetRepository timesheetRepository;

    @Autowired
    public EmployeServiceImpl(EmployeRepository employeRepository, DepartementRepository deptRepoistory, ContratRepository contratRepoistory, TimesheetRepository timesheetRepository) {
        this.employeRepository = employeRepository;
        this.deptRepoistory = deptRepoistory;
        this.contratRepoistory = contratRepoistory;
        this.timesheetRepository = timesheetRepository;
    }

    /* INES */
    public int ajouterEmploye(Employe employe) {
        employeRepository.save(employe);
        return employe.getId();
    }

    public void mettreAjourEmailByEmployeId(String email, int employeId) {
        Employe employe = employeRepository.findById(employeId).get();
        employe.setEmail(email);
        employeRepository.save(employe);
    }

    public void affecterEmployeADepartement(int employeId, int depId) {
        Departement depManagedEntity = deptRepoistory.findById(depId).get();
        Employe employeManagedEntity = employeRepository.findById(employeId).get();

        if (depManagedEntity.getEmployes() == null) {
            List<Employe> employes = new ArrayList<>();
            employes.add(employeManagedEntity);
            depManagedEntity.setEmployes(employes);
        } else {

            depManagedEntity.getEmployes().add(employeManagedEntity);

        }

    }

    @Transactional
    public void desaffecterEmployeDuDepartement(int employeId, int depId) {
        Departement dep = deptRepoistory.findById(depId).get();

        int employeNb = dep.getEmployes().size();
        for (int index = 0; index < employeNb; index++) {
            if (dep.getEmployes().get(index).getId() == employeId) {
                dep.getEmployes().remove(index);
            }
        }
    }

    public int ajouterContrat(Contrat contrat) {
        contratRepoistory.save(contrat);
        return contrat.getReference();
    }

    public void affecterContratAEmploye(int contratId, int employeId) {
        Contrat contratManagedEntity = contratRepoistory.findById(contratId).get();
        Employe employeManagedEntity = employeRepository.findById(employeId).get();

        contratManagedEntity.setEmploye(employeManagedEntity);
        contratRepoistory.save(contratManagedEntity);

    }

    public String getEmployePrenomById(int employeId) {
        Employe employeManagedEntity = employeRepository.findById(employeId).get();
        return employeManagedEntity.getPrenom();
    }

    public void deleteEmployeById(int employeId) {
        Employe employe = employeRepository.findById(employeId).get();

        //Desaffecter l'employe de tous les departements
        //c'est le bout master qui permet de mettre a jour
        //la table d'association
        for (Departement dep : employe.getDepartements()) {
            dep.getEmployes().remove(employe);
        }

        employeRepository.delete(employe);
    }

    public void deleteContratById(int contratId) {
        Contrat contratManagedEntity = contratRepoistory.findById(contratId).get();
        contratRepoistory.delete(contratManagedEntity);

    }



    /* DONIA */
    public int getNombreEmployeJPQL() {
        log.info("** start  getNumberEmployeeJPQL : ");
        int count = 0;
        try {
            count = employeRepository.countemp();
            log.debug(" -- the numbers of employee is :"+count);
            log.info("** end  getNumberEmployeeJPQL without error ");
        }catch (Exception e ){
            log.info("** end  getNumberEmployeeJPQL with error : "+e);
        }
        return count;
    }

    public List<String> getAllEmployeNamesJPQL() {
        log.info("** start  getAllEmployeeNamesJPQL : ");
        List<String> names = null;
        try {
            names = employeRepository.employeNames();
            log.debug(" -- N° 1 : Names of employee : "+names);
            log.info("** end  getAllEmployeeNamesJPQL without error ");
        }catch(Exception e ){
            log.info("** end  getAllEmployeeNamesJPQL with error : "+e);
        }
        return names;
    }

    public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
        log.info("** start  getAllEmployeeByEnterprise : ");
        List<Employe> employees = null;
        try {
            employees = employeRepository.getAllEmployeByEntreprisec(entreprise);
            log.debug(" -- N° 1 : the number of employees of the enterprise :" + entreprise.getName() +" .. is :" + employees.size() );
            List <String> emp_names = employees.stream().map(Employe::getNom).collect(Collectors.toList());
            log.debug(" -- N° 2 : their names are :" + emp_names );
            log.info("** end  getAllEmployeeByEnterprise without error");
        }catch(Exception e )
        {
            log.info("** end  getAllEmployeeByEnterprise with error : "+e);
        }
        return employees;
    }

    public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
        log.info("** start  UpdateEmailByEmployeeIdJPQL : ");
        try {
            log.debug(" -- N° 1 : the employee :" + employeId +" will have a new mail :" + email );
            employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);
            log.debug(" -- N° 2 : the employee :" + employeId +" updated successfully " );
            log.info("** end  UpdateEmailByEmployeeIdJPQL without error ");
        }catch (Exception e ){
            log.info("** end  UpdateEmailByEmployeeIdJPQL with error : "+e);
        }

    }

    public void deleteAllContratJPQL() {
        log.info("** start  deleteAllContactJPQL : ");
        try {
            employeRepository.deleteAllContratJPQL();
            log.info("** end  deleteAllContactJPQL without error");
        }catch(Exception e ){
            log.error("** end  deleteAllContactJPQL with error "+e);
        }
    }

    public float getSalaireByEmployeIdJPQL(int employeId) {

        log.info("** start  getSalaryByEmployeeIdJPQL : ");
        log.debug(" -- N° 1 : the employee :"+employeId  );
        float salaire = 0;
        try {
            salaire = employeRepository.getSalaireByEmployeIdJPQL(employeId);
            log.debug(" -- N° 2 : his salary is :"+salaire  );
            log.info("** end  getSalaryByEmployeeIdJPQL  without error ");
        } catch (Exception e) {
            log.error("** end  getSalaryByEmployeeIdJPQL  with error "+e);
        }

        return salaire;
    }

    public Double getSalaireMoyenByDepartementId(int departementId) {
        log.info("** start  getAverageSalaryByDepartmentId : ");
        log.debug(" -- N° 1 : the department ID :"+departementId );
        double average_salary=0;
        try {
            average_salary = employeRepository.getSalaireMoyenByDepartementId(departementId);
            log.debug(" -- N° 2 : average salary :"+average_salary );
            log.info("** end  getAverageSalaryByDepartmentId  without error ");
        }catch (Exception e )
        {
            log.info("** end  getAverageSalaryByDepartmentId  with error : "+e);
        }
        return average_salary;
    }

    public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
                                                         Date dateFin) {
        log.info("** start  getTimeSheetsByMissionAndDate : ");
        List<Timesheet> timesheetList = null;
        try {
            log.debug(" -- N° 1 : the information of timeSheet are :\nemployee : " +employe.getId()+"\nmission : "+mission.getName()+"\ndate : "+dateDebut+" -> "+dateFin );
            timesheetList = timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
            log.debug(" -- N° 2 : time sheet found successfully .. count  : " +timesheetList.size() );
            log.info("** end  getTimeSheetsByMissionAndDate without error ");
        }catch (Exception e ){
            log.info("** end  getTimeSheetsByMissionAndDate with error : "+e);
        }
        return timesheetList;
    }
    public List<Employe> getAllEmployes() {
        log.info("** start  getAllEmployees : ");
        List<Employe> employeeList = null;
        try {
            employeeList = (List<Employe>) employeRepository.findAll();
            log.debug(" -- N° 1 : the employee list count :" +employeeList.size() );
            log.info("** end  getAllEmployees without error ");
        }catch(Exception e ){
            log.info("** end  getAllEmployees with error  : "+e);
        }
        return employeeList ;
    }

}
