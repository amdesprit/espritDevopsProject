package tn.esprit.spring.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
        
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
                
import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.IEntrepriseService;
 


@RunWith(SpringRunner.class)
@SpringBootTest
public class EntrepriseServiceImplTest {
        @Autowired
        IEntrepriseService ES; 
           
           
        		@Test
        		public void ajouterEntreprise() throws ParseException {
        	
	                Entreprise entreprise=new Entreprise("testent","testraisonSocial");
	                Entreprise addedEntreprise = ES.ajouterEntreprise(entreprise);
	                assertEquals(entreprise.getName(),addedEntreprise.getName());
	               
        		}
        		
           		@Test
        		public void ajouterDepartement() throws ParseException {
        	
	                Departement departement=new Departement("testdep");
	                Departement addedDep = ES.ajouterDepartement(departement);
	                assertEquals(departement.getName(),addedDep.getName());
	               
        		}
            
           
                //ES.ajouterEntreprise(new Entreprise("test1","raisonSocial"));}
				
        		 @Test   
		         public void UpdateEntreprise() throws ParseException {
	
			         Entreprise entreprise=new Entreprise(3,"TestUpdate","Updatent");
			         Entreprise entrepriseUpdated=ES.UpdateEntreprise(entreprise);
			         assertEquals(entreprise.getName(),entrepriseUpdated.getName());
		         
		         }
           
                //ES.UpdateEntreprise(new Entreprise(12,"TestUpdate","Update"));}
           
                @Test
                public void getallEntreprises() throws ParseException {
                	
	                Entreprise entreprise=new Entreprise("testent","testraisonSocial");
	                Entreprise addedEntreprise = ES.ajouterEntreprise(entreprise);
                		
                    List<Entreprise> listentreprises=ES.getallEntreprises();
                    assertEquals(1,listentreprises.size());
                    
                }
                
        		 
                @Test
                
                public void getEntrepriseById() throws ParseException {
                		ES.getEntrepriseById(3);
                       }
                       //Entreprise entrepriseretrieved= ES.getEntrepriseById(14);
                       

              
              
               @Test
                public void affecterDepartementAEntreprise() throws ParseException {
	                Entreprise entreprise=new Entreprise("testent","testraisonSocial");
	                Entreprise addedEntreprise = ES.ajouterEntreprise(entreprise);
	                Departement departement=new Departement("testdep");
	                Departement addedDep = ES.ajouterDepartement(departement);
	                
            	   	ES.affecterDepartementAEntreprise(1,1);
               }
              
              
               @Test
                public void getAllDepartementsNamesByEntreprise() throws ParseException {
                       List<String> listeName=ES.getAllDepartementsNamesByEntreprise(3);
                       }
               
               
//             @Test
//             public void testdeleteAll() throws ParseException {
//                    ES.deleteAll();
//                    List<Entreprise> listentreprises=ES.getallEntreprises();
//                            assertEquals(0,listentreprises.size());}
//            
               @Test
             
                public void deleteEntrepriseById() throws ParseException {
                      ES.deleteEntrepriseById(3);
                      }
               
               
               
               
               
}
