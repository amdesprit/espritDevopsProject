package tn.esprit.spring.services;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repository.*;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
class EmployeServiceTest {
    @Autowired
    IEmployeService employeeService;
    @Autowired
    EntrepriseRepository enterpriseRepository;
    @Autowired
    DepartementRepository departementRepository;
    @Autowired
    ContratRepository contratRepository;


    Entreprise entreprise;
    Contrat contract_1, contract_2, contract_3;
    Departement department_1;
    Employe employee_2 = new Employe("Khaled", "Kallel", "Khaled_.kallel@ssiiconsulting.tn", true, Role.INGENIEUR);
    Employe employee_1 = new Employe("Khaled", "ben", "updatedmail@mail.com", true, Role.INGENIEUR);
    Employe employee_3 = new Employe("monji", "slim", "monji@ssiiconsulting.tn", true, Role.CHEF_DEPARTEMENT);

    @Test
    void getNombreEmployeJPQL() {
        int count = employeeService.getNombreEmployeJPQL();
        if (count == 0)
            employeeService.ajouterEmploye(employee_1);
        Assert.assertNotEquals(0, count);
    }

    @Test
    void getAllEmployeNamesJPQL() {
        List<String> actual_names = employeeService.getAllEmployeNamesJPQL();
        Assert.assertTrue(actual_names.size() > 0);
    }

    @Test
    void getAllEmployeByEntreprise() {
        entreprise = new Entreprise("Accretio", "Accretio France");
        department_1 = new Departement("Mobile", employeeService.getAllEmployes(), entreprise);
        entreprise.addDepartement(department_1);
        enterpriseRepository.save(entreprise);
        departementRepository.save(department_1);
        List<Employe> actual_employee_by_enterprise = employeeService.getAllEmployeByEntreprise(entreprise);
        Assert.assertNotNull(actual_employee_by_enterprise);
    }

    @Test
    void mettreAjourEmailByEmployeIdJPQL() {
        String newMail = "updated_mail@mail.com";
        employeeService.mettreAjourEmailByEmployeIdJPQL(newMail, employeeService.getAllEmployes().get(0).getId());
        String updatedMail = employeeService.getAllEmployes().get(0).getEmail();
        Assert.assertEquals("check updated mail ", newMail, updatedMail);
    }

    @Test
    void deleteAllContratJPQL() {
        employeeService.deleteAllContratJPQL();
        long count = contratRepository.count();
        Assert.assertEquals("deleteAllContractJPQL... ", 0, count);
    }

    @Test
    void getSalaireByEmployeIdJPQL() throws ParseException {
        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy/MM/dd");
        Date date = DateFor.parse("2021/11/25");
        contract_1 = new Contrat(111, date, "CIVP", 1200);
        contract_1.setEmploye(employeeService.getAllEmployes().get(0));
        contratRepository.save(contract_1);
        float salary = employeeService.getSalaireByEmployeIdJPQL(employeeService.getAllEmployes().get(0).getId());
        MatcherAssert.assertThat("getSalaireByEmployeIdJPQL... ", salary, Matchers.equalTo(contract_1.getSalaire()));
    }

    @Test
    void getAllEmployes() {
        List<Employe> employees = employeeService.getAllEmployes();
        Assert.assertNotNull(employees);
    }
    
    @Test
 	public void ajouterEmploye() throws ParseException {
 	
 		Employe em = new Employe("xavier", "Olivier", "xavier.Olivier@esprit.tn",true,Role.INGENIEUR);
 		int employeId = employeeService.ajouterEmploye(em); 
 		assertEquals("check ajout employé ",5,employeId);
 	}
 	@Test
 	public void ajouterContrat() throws ParseException {
 		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
 		Date d = dateFormat.parse("2020-10-30");
 		Contrat c = new Contrat(155,d, "CVP", 3200);
 		
 		int ContratRef = employeeService.ajouterContrat(c); 
 		assertEquals("check ajout contrat ",155,ContratRef);
 	}


}