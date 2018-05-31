package controleur;

public class Commande {
	private int com_num,fact_num,id_livr, uti_id,com_prest;
	private String com_text, com_date;

	public Commande() {
		this.com_num = 0;
		this.com_date = "";
		this.com_text = "";
		this.com_prest= 0;
		this.uti_id = 0;
		this.fact_num = 0;
		
	}
	
	public Commande(int com_num) {
		this.com_num = com_num;
		this.com_date = "";
		this.com_text = "";
		this.com_prest= 0;
		this.uti_id = 0;
		this.fact_num = 0;
	}
	
	public Commande(int com_num, String com_date, String com_text,int com_prest, int uti_id, int fact_num) {
		this.com_num = com_num;
		this.com_date = com_date;
		this.com_text = com_text;
		this.com_prest = com_prest;
		this.uti_id = uti_id;
		this.fact_num = fact_num;

	}
	

	public int getCom_num() {
		return com_num;
	}

	public void setCom_num(int com_num) {
		this.com_num = com_num;
	}

	public int getFact_num() {
		return fact_num;
	}

	public void setFact_num(int fact_num) {
		this.fact_num = fact_num;
	}

	public int getUti_id() {
		return uti_id;
	}

	public void setUti_id(int uti_id) {
		this.uti_id = uti_id;
	}

	public String getCom_text() {
		return com_text;
	}

	public void setCom_text(String com_text) {
		this.com_text = com_text;
	}
	
	public int getCom_prest() {
		return com_prest;
	}

	public void setCom_prest(int com_prest) {
		this.com_prest = com_prest;
	}

	public String getCom_date() {
		return com_date;
	}

	public void setCom_date(String com_date) {
		this.com_date = com_date;
	}

	public boolean verifSaisie() {
		 
		return true;
	}
	
	

}
