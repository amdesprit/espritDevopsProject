package tn.esprit.spring.services;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repository.*;

import java.util.List;



@RunWith(SpringRunner.class)
@SpringBootTest
class EmployeServiceImplTest {
    @Autowired
    IEmployeService employeeService;
    @Autowired
    EntrepriseRepository enterpriseRepository;
    @Autowired
    DepartementRepository departementRepository;


    Entreprise entreprise;
    Contrat contract_1, contract_2, contract_3;
    Departement department_1;
    Employe employee_2= new Employe("Khaled", "Kallel", "Khaled_.kallel@ssiiconsulting.tn", true, Role.INGENIEUR);
    Employe employee_1= new Employe("Khaled", "ben", "updatedmail@mail.com", true, Role.INGENIEUR);
    Employe employee_3= new Employe("monji", "slim", "monji@ssiiconsulting.tn", true, Role.CHEF_DEPARTEMENT);

    @Test
    void getNombreEmployeJPQL() {
        int count = employeeService.getNombreEmployeJPQL();
        if(count == 0)
            employeeService.ajouterEmploye(employee_1);
        Assert.assertNotEquals(0, count);
    }

    @Test
    void getAllEmployeNamesJPQL() {
        List<String> actual_names = employeeService.getAllEmployeNamesJPQL();
        Assert.assertTrue(actual_names.size()>0);
    }

    @Test
    void getAllEmployeByEntreprise() {
        entreprise = new Entreprise("Accretio", "Accretio France");
        department_1 = new Departement( "Mobile", employeeService.getAllEmployes(), entreprise);
        entreprise.addDepartement(department_1);
        enterpriseRepository.save(entreprise);
        departementRepository.save(department_1);
        List<Employe> actual_employee_by_enterprise = employeeService.getAllEmployeByEntreprise(entreprise);
        Assert.assertNotNull(actual_employee_by_enterprise);
    }


}