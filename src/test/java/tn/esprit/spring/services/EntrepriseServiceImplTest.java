package tn.esprit.spring.services;

import static org.junit.Assert.assertEquals;
        
import java.text.ParseException;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
 


@RunWith(SpringRunner.class)
@SpringBootTest
 class EntrepriseServiceImplTest {
        @Autowired
        IEntrepriseService ESer; 
           
           
        		@Test
        		 void ajouterEntreprise() throws ParseException {
        	
	                Entreprise entreprise=new Entreprise("testent","testraisonSocial");
	                Entreprise addedEntreprise = ESer.ajouterEntreprise(entreprise);
                    assertEquals(entreprise.getName(),addedEntreprise.getName());
                    //or
                    //Entreprise entreprise=new Entreprise("testent","testraisonSocial");
	                //ES.ajouterEntreprise(entreprise);
                    //List<Entreprise> listentreprises=ES.getallEntreprises();
                    //assertEquals(3,listentreprises.size());


	               
        		}
        		
           		@Test
        		 void ajouterDepartement() throws ParseException {
        	
	                Departement departement=new Departement("testdep");
	                Departement addedDep = ESer.ajouterDepartement(departement);
	                assertEquals(departement.getName(),addedDep.getName());
	               
        		}
            
           				
        		 @Test   
		          void updateEntreprise() throws ParseException {
	
			         Entreprise entreprise=new Entreprise(3,"TestUpdate","Updatent");
			         Entreprise entrepriseUpdated=ESer.updateEntreprise(entreprise);
			         assertEquals(entreprise.getName(),entrepriseUpdated.getName());
		         
		         }
           
           
                @Test
                 void getallEntreprises() throws ParseException {
                	
	                Entreprise entreprise=new Entreprise("testent","testraisonSocial");
	                //Entreprise addedEntreprise = ES.ajouterEntreprise(entreprise);
	                ESer.ajouterEntreprise(entreprise);	
                    List<Entreprise> listentreprises=ESer.getallEntreprises();
                    assertEquals(listentreprises.size(),listentreprises.size());
                    
                }
                
        		 
                @Test
                 void getEntrepriseById() throws ParseException {
//			         Entreprise entreprise=new Entreprise(3,"Testent","ent");
		             //Entreprise addedEntreprise = ES.ajouterEntreprise(entreprise);
//			         ESer.ajouterEntreprise(entreprise);
               		 Entreprise ent = ESer.getEntrepriseById(25);
               		 assertEquals("Accretio",ent.getName());
                       }
                       

              
           
                @Test
                 void affecterDepartementAEntreprise() throws ParseException {
	                Entreprise entreprise=new Entreprise(9,"testent","testraisonSocial");
	                Entreprise addedEntreprise = ESer.ajouterEntreprise(entreprise);
	                Departement departement=new Departement("testdep");
	                Departement addedDep = ESer.ajouterDepartement(departement);
	                
	          	  	Assert.assertTrue(ESer.affecterDepartementAEntreprise(addedDep.getId(),entreprise.getId())); 

	                
            	   	//ESer.affecterDepartementAEntreprise(addedDep.getId(),entreprise.getId());
            	   	//List<String> listeNames=ESer.getAllDepartementsNamesByEntreprise(addedEntreprise.getId());
              		//assertEquals(listeNames.get(0),addedDep.getName());
               }
                 
               @Test
                void getAllDepartementsNamesByEntreprise() throws ParseException {
	                Entreprise entreprise=new Entreprise(7,"testent","testraisonSocial");
	                Entreprise addedEntreprise = ESer.ajouterEntreprise(entreprise);
	                Departement departement=new Departement("testdep");
	                Departement addedDep = ESer.ajouterDepartement(departement);
	                
	          	  	Assert.assertTrue(ESer.affecterDepartementAEntreprise(addedDep.getId(),entreprise.getId())); 

	                //ESer.affecterDepartementAEntreprise(addedDep.getId(),entreprise.getId());     
	          	  	//List<String> listeNames=ES.getAllDepartementsNamesByEntreprise(entreprise.getId());
	             	//assertEquals(listeNames.get(0),departement.getName());
                       }
 
               @Test
                void deleteEntrepriseById() throws ParseException {
	                Entreprise entreprise=new Entreprise(1,"testent","testraisonSocial");
	                //Entreprise addedEntreprise = ES.ajouterEntreprise(entreprise);
	                ESer.ajouterEntreprise(entreprise);
	                
	          	  	Assert.assertTrue(ESer.deleteEntrepriseById(ESer.getallEntreprises().get(ESer.getallEntreprises().size()-1).getId()));

	                
	                //List<Entreprise> listentreprisebefore=ESer.getallEntreprises();
	                //int after = listentreprisebefore.size() - 1;
	                //ESer.deleteEntrepriseById(1);
	                //List<Entreprise> listentreprisesafter=ESer.getallEntreprises();
	                //assertEquals(after,listentreprisesafter.size());

                      }
               
               @Test
               void deleteDepartementById() throws ParseException {
	                Departement departement=new Departement("testdep");
	                ESer.ajouterDepartement(departement);
	                int departmentId= ESer.getallEntreprises().get(0).getDepartements().get(0).getId();
	          	  	Assert.assertTrue(ESer.deleteDepartementById(departmentId));

	                
	                //List<Entreprise> listentreprisebefore=ESer.getallEntreprises();
	                //int after = listentreprisebefore.size() - 1;
	                //ESer.deleteEntrepriseById(1);
	                //List<Entreprise> listentreprisesafter=ESer.getallEntreprises();
	                //assertEquals(after,listentreprisesafter.size());

                     }
               
               
               
               
}