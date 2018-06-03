package controleur;
/*+---------+------------+----------------+-----------+
| uti_nom | uti_prenom | fact_reglement | fact_prix |
+---------+------------+----------------+-----------+
| jean    | vincent    | 2018-05-01     |       350 |
+---------+------------+----------------+-----------+
*/
public class Archive {
	private String uti_nom,uti_prenom,fact_reglement;
	private int fact_prix,fact_quantite;
	
	public Archive() {
		this.uti_nom = "";
		this.uti_prenom = "";
		this.fact_reglement ="";
		this.fact_prix = 0;
		this.fact_quantite=0;
	}
	public Archive(String uti_nom,String uti_prenom,String fact_reglement,int fact_prix,int fact_quantite) {
		this.uti_nom = uti_nom;
		this.uti_prenom = uti_prenom;
		this.fact_reglement =fact_reglement;
		this.fact_prix = fact_prix;
		this.fact_quantite = fact_quantite;
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
	public String getFact_reglement() {
		return fact_reglement;
	}
	public void setFact_reglement(String fact_reglement) {
		this.fact_reglement = fact_reglement;
	}
	public int getFact_prix() {
		return fact_prix;
	}
	public void setFact_prix(int fact_prix) {
		this.fact_prix = fact_prix;
	}
	public int getFact_quantite() {
		return fact_quantite;
	}
	public void setFact_quantite(int fact_quantite) {
		this.fact_quantite = fact_quantite;
	}
	
	

}
