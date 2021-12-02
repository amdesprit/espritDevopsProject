package tn.esprit.spring.services;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repository.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeServiceImplTest {

    IEmployeService employeeService;
    ContratRepository contractRepository;
    MissionRepository missionRepository;
    DepartementRepository departementRepository;
    EntrepriseRepository entrepriseRepository;

    @Autowired
    public EmployeServiceImplTest(EmployeServiceImpl employeeService, ContratRepository contractRepository, MissionRepository missionRepository,DepartementRepository departementRepository,EntrepriseRepository entrepriseRepository) {
        this.employeeService = employeeService;
        this.contractRepository = contractRepository;
        this.missionRepository = missionRepository;
        this.departementRepository = departementRepository;
        this.entrepriseRepository = entrepriseRepository;
    }

    private Employe employee_1, employee_2, employee_3;
    private List<Employe> employeesList= new ArrayList<>();
    private Entreprise entreprise;
    private Contrat contract_1, contract_2, contract_3;
    private Departement department_1;

    /*  DONIA TEST  */
    @Test
    public void getNombreEmployeJPQL() {
        employee_1 = new Employe(1, "Khaled", "Kallel", "Khaled.kallel@ssiiconsulting.tn", true, Role.INGENIEUR);
        employee_2 = new Employe(2, "Khaled", "ben", "updatedmail@mail.com", true, Role.INGENIEUR);
        employee_3 = new Employe(3, "monji", "slim", "monji@ssiiconsulting.tn", true, Role.CHEF_DEPARTEMENT);
//        employeesList.add(employee_1);
//        employeesList.add(employee_2);
//        employeesList.add(employee_3);
        employeeService.ajouterEmploye(employee_1);
        employeeService.ajouterEmploye(employee_2);
        employeeService.ajouterEmploye(employee_3);

        int count = employeeService.getNombreEmployeJPQL();
        Assert.assertEquals(".. getNombreOfEmployeeJPQL count : ", employeesList.size(), count);
    }

    @Test
    public void getAllEmployeNamesJPQL() {
        List<String> actual_names = employeeService.getAllEmployeNamesJPQL();
        List<String> static_names = Arrays.asList(employee_1.getNom(), employee_2.getNom(), employee_3.getNom());
        MatcherAssert.assertThat(actual_names, Matchers.equalToObject(static_names));
    }

//    @Test
//    public void getAllEmployeByEntreprise() {
//        entreprise = new Entreprise(1, "Accretio", "Accretio France");
//        department_1 = new Departement(28, "Mobile", employeeService.getAllEmployes(), entreprise);
//        entreprise.addDepartement(department_1);
//        entrepriseRepository.save(entreprise);
//        departementRepository.save(department_1);
//        List<Employe> actual_employee_by_enterprise = employeeService.getAllEmployeByEntreprise(entreprise);
//        Assert.assertNotNull(actual_employee_by_enterprise);
//    }
//
//    @Test
//    public void mettreAjourEmailByEmployeIdJPQL() {
//        employeeService.mettreAjourEmailByEmployeIdJPQL("updatedmail@mail.com", 2);
//        Employe employee = null;
//        for (Employe e : employeeService.getAllEmployes()) {
//                if(e.getId() == 2)
//                {
//                    employee = e;break;
//                }
//        }
//        assert employee != null;
//        Assert.assertEquals("check updated mail ","updatedmail@mail.com",employee.getEmail());
//    }
//
//    @Test
//    public void deleteAllContratJPQL() {
//        employeeService.deleteAllContratJPQL();
//        long count = contractRepository.count();
//        Assert.assertEquals("deleteAllContractJPQL... ",0,count);
//    }
//
//    @Test
//    public void getSalaireByEmployeIdJPQL() {
//        double salary = employeeService.getSalaireByEmployeIdJPQL(2);
//        MatcherAssert.assertThat("getSalaireByEmployeIdJPQL... ",salary,Matchers.equalTo(2500.0));
//    }
//
//    @Test
//    public void getTimesheetsByMissionAndDate() {
//
//    }
//
//    @Test
//    public void getAllEmployes() {
//        List<String> employees = employeeService.getAllEmployes().stream().map(employe -> employe.getEmail()).collect(Collectors.toList());
//        List<String> static_email = Arrays.asList(employee_1.getEmail(), employee_2.getEmail(), employee_3.getEmail());
//        MatcherAssert.assertThat(static_email, Matchers.equalToObject(employees));
//    }
//
//    @Test
//    public void getSalaireMoyenByDepartementId() {
//
//    }


}
