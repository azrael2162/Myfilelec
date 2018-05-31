package controleur;

public class User {
	private int uti_id,grp_id;
	private String uti_nom, uti_prenom, uti_email, login,passwordd;
	
	public User() {
		this.uti_id = 0;
		this.uti_nom = "";
		this.uti_prenom = "";
		this.uti_email = "";
		this.login = "";
		this.passwordd = "";
		this.grp_id=0;
		

	}
	
	public User (int uti_id, String uti_nom, String uti_prenom, String uti_email, String login,String passwordd,int grp_id) {
		this.uti_id = uti_id;
		this.uti_nom = uti_nom;
		this.uti_prenom = uti_prenom;
		this.uti_email = uti_email;
		this.login = login;
		this.passwordd = passwordd;
		this.grp_id= grp_id;

	}
	
	
	public User (String uti_nom, String uti_prenom, String uti_email, String login,String passwordd,int grp_id) {
		this.uti_nom = uti_nom;
		this.uti_prenom = uti_prenom;
		this.uti_email = uti_email;
		this.login = login;
		this.passwordd = passwordd;
		this.grp_id=grp_id;
	}
	public boolean verifSaisie() {
		if(this.uti_nom.equals("") || this.uti_prenom.equals("") || this.uti_email.equals("")){
			return false;
		}
		return true;
	}

	public int getUti_id() {
		return uti_id;
	}

	public void setUti_id(int uti_id) {
		this.uti_id = uti_id;
	}

	public String getUti_nom() {
		return uti_nom;
	}

	public void setUti_nom(String uti_nom) {
		this.uti_nom = uti_nom;
	}

	public String getUti_prenom() {
		return uti_prenom;
	}

	public void setUti_prenom(String uti_prenom) {
		this.uti_prenom = uti_prenom;
	}

	public String getUti_email() {
		return uti_email;
	}

	public void setUti_email(String uti_email) {
		this.uti_email = uti_email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPasswordd() {
		return passwordd;
	}

	public void setPasswordd(String passwordd) {
		this.passwordd = passwordd;
	}

	public int getGrp_id() {
		return grp_id;
	}

	public void setGrp_id(int grp_id) {
		this.grp_id = grp_id;
	}	
}
