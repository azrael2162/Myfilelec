package controleur;

public class Etudiant {
	private int idEtudiant;
	private String nom, prenom, email, classe;
	
	public Etudiant() {
		this.idEtudiant = 0;
		this.nom = "";
		this.prenom = "";
		this.email = "";
		this.classe = "";
	}
	
	public Etudiant (int idEtudiant, String nom, String prenom, String email, String classe) {
		this.idEtudiant = idEtudiant;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.classe = classe;
	}
	
	
	public Etudiant (String nom, String prenom, String email, String classe) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.classe = classe;
	}
	public boolean verifSaisie() {
		if(this.nom.equals("") || this.prenom.equals("") || this.email.equals("")){
			return false;
		}
		return true;
	}
	
	//getters & setters
	public int getIdEtudiant() {
		return idEtudiant;
	}

	public void setIdEtudiant(int idEtudiant) {
		this.idEtudiant = idEtudiant;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}
	
	
}
