package tn.esprit.spring.services;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
class EmployeServiceImplTest {
    @Autowired
    IEmployeService employeService;
    private Employe employee_1, employee_2, employee_3;

    @Test
    void getNombreEmployeJPQL() {
        employee_1 = new Employe(1, "Khaled", "Kallel", "Khaled.kallel@ssiiconsulting.tn", true, Role.INGENIEUR);
        employeService.ajouterEmploye(employee_1);
        int count = employeService.getNombreEmployeJPQL();
        assertEquals(1, count);
    }


}