package controleur;

import javax.swing.table.AbstractTableModel;

public class Tableau extends AbstractTableModel{

	
	private String entetes[];
	private Object donnees[][]; //matrice 
	
	public Tableau (Object donnees[][], String entetes[]) {
		this.donnees = donnees;
		this.entetes = entetes;
	}
	
	
	
	@Override
	public int getRowCount() {
		return this.donnees.length;
	}

	@Override
	public int getColumnCount() {
		return this.entetes.length;
	}
	
	

	@Override
	public String getColumnName(int column) {
		 
		return this.entetes[column];
	}



	@Override
	public Object getValueAt(int ligne, int colonne) {
		try {
		return this.donnees[ligne][colonne];
		}
		catch (ArrayIndexOutOfBoundsException exp)
		{
			System.out.println(ligne +" - "+colonne);
			return null;
		}
	}
	
	public void add(Object ligne[]) {
		Object newDonnees[][] = new Object[this.donnees.length+1][this.entetes.length];
		//copie de donnees dans newDonnees
		for(int i = 0 ; i < this.donnees.length; i++) {
			newDonnees[i] = donnees[i];
		}
		newDonnees[this.donnees.length] = ligne;
		//ajout de la nouvelle ligne 
		this.donnees = newDonnees;
		//actualiser les données 
		this.fireTableDataChanged();
	}
	public void delete (int indexLigne) {
		Object newDonnees[][] = new Object[this.donnees.length-1][this.entetes.length];
		int j =0;
		for(int i = 0 ; i < this.donnees.length; i++) {
			if( i != indexLigne) {
				newDonnees[j] = this.donnees[i];
				j++;
			}
		}
		//remplacement de la table par la nouvelle 
		this.donnees = newDonnees;
		//actualiser les données 
		this.fireTableDataChanged();
	}
	public void update(Object ligne[], int index) {
		Object newDonnees[][] = new Object[this.donnees.length][this.entetes.length];
		for(int i = 0 ; i < this.donnees.length; i++) {
			if(i!=index) {
				newDonnees[i] = this.donnees[i];
			} else {
				newDonnees[i] = ligne;
				}
		}
		//remplacement de la table par la nouvelle
		this.donnees = newDonnees;
		//actualiser les données 
		this.fireTableDataChanged();
	}
	public void setDonnees(Object donnees[][]) {
		this.donnees = donnees;
		this.fireTableDataChanged();
	}
}




