package tn.esprit.spring.services;

import static org.junit.Assert.assertEquals;
        
import java.text.ParseException;
import java.util.List;
                
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
        IEntrepriseService ES; 
           
           
        		@Test
        		void ajouterEntreprise() throws ParseException {
        	
	                Entreprise entreprise=new Entreprise("testent","testraisonSocial");
	                Entreprise addedEntreprise = ES.ajouterEntreprise(entreprise);
                    List<Entreprise> listentreprises=ES.getallEntreprises();
                    assertEquals(3,listentreprises.size());

	               
        		}
        		
           		@Test
        		void ajouterDepartement() throws ParseException {
        	
	                Departement departement=new Departement("testdep");
	                Departement addedDep = ES.ajouterDepartement(departement);
	                assertEquals(departement.getName(),addedDep.getName());
	               
        		}
            
           
                //ES.ajouterEntreprise(new Entreprise("test1","raisonSocial"));}
				
        		 @Test   
		         void updateEntreprise() throws ParseException {
	
			         Entreprise entreprise=new Entreprise(3,"TestUpdate","Updatent");
			         Entreprise entrepriseUpdated=ES.updateEntreprise(entreprise);
			         assertEquals(entreprise.getName(),entrepriseUpdated.getName());
		         
		         }
           
                //ES.UpdateEntreprise(new Entreprise(12,"TestUpdate","Update"));}
           
                @Test
                void getallEntreprises() throws ParseException {
                	
	                Entreprise entreprise=new Entreprise("testent","testraisonSocial");
	                Entreprise addedEntreprise = ES.ajouterEntreprise(entreprise);
                		
                    List<Entreprise> listentreprises=ES.getallEntreprises();
                    assertEquals(1,listentreprises.size());
                    
                }
                
        		 
                @Test
                
                void getEntrepriseById() throws ParseException {
			         Entreprise entreprise=new Entreprise(3,"Testent","ent");
		             Entreprise addedEntreprise = ES.ajouterEntreprise(entreprise);

               		 Entreprise ent = ES.getEntrepriseById(entreprise.getId());
               		 assertEquals(entreprise.getName(),ent.getName());
                       }
                       

              
       /*       
                @Test
                public void affecterDepartementAEntreprise() throws ParseException {
	                Entreprise entreprise=new Entreprise(9,"testent","testraisonSocial");
	                Entreprise addedEntreprise = ES.ajouterEntreprise(entreprise);
	                Departement departement=new Departement("testdep");
	                Departement addedDep = ES.ajouterDepartement(departement);
	                
            	   	ES.affecterDepartementAEntreprise(addedDep.getId(),entreprise.getId());
            	   	List<String> listeNames=ES.getAllDepartementsNamesByEntreprise(addedEntreprise.getId());
              		assertEquals(listeNames.get(0),addedDep.getName());

               }
              
              
               @Test
                public void getAllDepartementsNamesByEntreprise() throws ParseException {
	                Entreprise entreprise=new Entreprise(7,"testent","testraisonSocial");
	                Entreprise addedEntreprise = ES.ajouterEntreprise(entreprise);
	                Departement departement=new Departement("testdep");
	                Departement addedDep = ES.ajouterDepartement(departement);
	                ES.affecterDepartementAEntreprise(addedDep.getId(),entreprise.getId());
		                
	           	   	List<String> listeNames=ES.getAllDepartementsNamesByEntreprise(entreprise.getId());
	             	assertEquals(listeNames.get(0),departement.getName());
                       }
         */      
               
//             @Test
//             public void testdeleteAll() throws ParseException {
//                    ES.deleteAll();
//                    List<Entreprise> listentreprises=ES.getallEntreprises();
//                            assertEquals(0,listentreprises.size());}
//            
               @Test
             
                void deleteEntrepriseById() throws ParseException {
	                Entreprise entreprise=new Entreprise(1,"testent","testraisonSocial");
	                Entreprise addedEntreprise = ES.ajouterEntreprise(entreprise);
               		
	                ES.deleteEntrepriseById(1);
	                List<Entreprise> listentreprises=ES.getallEntreprises();
	
	                assertEquals(1,listentreprises.size());

                      }
               
               
               
               
               
}
