package tn.esprit.spring.entities;

public class EmployeModel {

	private int id;
	private String prenom;
	private String nom;
	private String email;
	private boolean isActif;
	private Role role;

	public EmployeModel() {
	}

	public EmployeModel(String prenom, String nom, String email, boolean isActif, Role role) {
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.isActif = isActif;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActif() {
		return isActif;
	}

	public void setActif(boolean actif) {
		isActif = actif;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
