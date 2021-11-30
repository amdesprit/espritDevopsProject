package tn.esprit.spring.services;

import org.apache.log4j.Logger;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repository.ContratRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeServiceImplTest {
    private static final Logger log = Logger.getLogger(EmployeServiceImplTest.class);

    @Autowired
    EmployeServiceImpl employeeService;

    @Autowired
    ContratRepository contractRepository;

    private Employe employee_1, employee_2, employee_3;
    private List<Employe> employeesList;
    private Entreprise entreprise;
    private Contrat contract_1, contract_2, contract_3;
    private Departement department_1;

    @Before
    public void init() throws ParseException {
        employeesList = new ArrayList<>();
        employee_1 = new Employe(1, "Khaled", "Kallel", "Khaled.kallel@ssiiconsulting.tn", true, Role.INGENIEUR);
        employee_2 = new Employe(2, "Khaled", "ben", "Khaled.ben@ssiiconsulting.tn", true, Role.INGENIEUR);
        employee_3 = new Employe(3, "monji", "slim", "monji@ssiiconsulting.tn", true, Role.CHEF_DEPARTEMENT);
        employeesList.add(employee_1);
        employeesList.add(employee_2);
        employeesList.add(employee_3);

        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy/MM/dd");
        Date date = DateFor.parse("2021/11/25");
        contract_1 = new Contrat(111, date, "CIVP", 1200);
        contract_2 = new Contrat(112, date, "CDI", 2500);
        contract_3 = new Contrat(113, date, "CDI", 2000);

        contract_1.setEmploye(employeeService.getAllEmployes().get(0));
        contract_2.setEmploye(employeeService.getAllEmployes().get(1));
        contract_3.setEmploye(employeeService.getAllEmployes().get(2));

        employeeService.ajouterContrat(contract_1);
        employeeService.ajouterContrat(contract_2);
        employeeService.ajouterContrat(contract_3);
//        employee_1.setContrat(contract_1);
//        employee_2.setContrat(contract_2);
//        employee_3.setContrat(contract_3);

        entreprise = new Entreprise(25, "Accretio", "Accretio France");

        department_1 = new Departement(28, "Mobile", employeesList, entreprise);
        entreprise.addDepartement(department_1);
    }

    /*  DONIA TEST  */
    @Test
    public void getNombreEmployeJPQL() {
        int count = employeeService.getNombreEmployeJPQL();
        Assert.assertEquals(".. getNombreOfEmployeeJPQL count : ", 3, count);
    }

    @Test
    public void getAllEmployeNamesJPQL() {
        List<String> actual_names = employeeService.getAllEmployeNamesJPQL();
        List<String> static_names = Arrays.asList(employee_1.getNom(), employee_2.getNom(), employee_3.getNom());
        MatcherAssert.assertThat(actual_names, Matchers.equalToObject(static_names));
    }

    @Test
    public void getAllEmployeByEntreprise() {
        List<Employe> actual_employee_by_enterprise = employeeService.getAllEmployeByEntreprise(entreprise);
        Assert.assertNotNull(actual_employee_by_enterprise);
    }

    @Test
    public void mettreAjourEmailByEmployeIdJPQL() {
        employeeService.mettreAjourEmailByEmployeIdJPQL("updatedmail@mail.com", 2);
        Employe employee = null;
        for (Employe e : employeeService.getAllEmployes()) {
                if(e.getId() == 2)
                {
                    employee = e;break;
                }
        }
        Assert.assertEquals("check updated mail ","updatedmail@mail.com",employee.getEmail());
    }

    @Test
    public void deleteAllContratJPQL() {
//        contract_1.setEmploye(employeeService.getAllEmployes().get(0));
//        contract_2.setEmploye(employeeService.getAllEmployes().get(1));
//        employeeService.ajouterContrat(contract_1);
//        employeeService.ajouterContrat(contract_2);
        employeeService.deleteAllContratJPQL();
        long count = contractRepository.count();
        Assert.assertEquals("deleteAllContractJPQL... ",0,count);
    }

    @Test
    public void getSalaireByEmployeIdJPQL() {
//        contract_1.setEmploye(employeeService.getAllEmployes().get(0));
//        contract_2.setEmploye(employeeService.getAllEmployes().get(1));
//        contract_3.setEmploye(employeeService.getAllEmployes().get(2));
//
//        employeeService.ajouterContrat(contract_1);
//        employeeService.ajouterContrat(contract_2);
//        employeeService.ajouterContrat(contract_3);

        double salary = employeeService.getSalaireByEmployeIdJPQL(2);
        MatcherAssert.assertThat("getSalaireByEmployeIdJPQL... ",salary,Matchers.equalTo(2500.0));
    }

    @Test
    public void getTimesheetsByMissionAndDate() {

    }

    @Test
    public void getAllEmployes() {

    }

    @Test
    public void getSalaireMoyenByDepartementId() {

    }


}
