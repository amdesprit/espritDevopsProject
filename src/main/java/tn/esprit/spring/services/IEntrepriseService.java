package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;

public interface IEntrepriseService {
	
	public Entreprise ajouterEntreprise(Entreprise entreprise);
	public Departement ajouterDepartement(Departement dep);
	boolean affecterDepartementAEntreprise(int depId, int entrepriseId);
	List<String> getAllDepartementsNamesByEntreprise(int entrepriseId);
	public boolean deleteEntrepriseById(int entrepriseId);
	public boolean deleteDepartementById(int depId);
	public Entreprise getEntrepriseById(int entrepriseId);
	public Entreprise updateEntreprise(Entreprise entreprise);
	public List<Entreprise> getallEntreprises();
}
