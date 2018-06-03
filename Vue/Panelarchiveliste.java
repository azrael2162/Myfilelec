package Vue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controleur.User;
import controleur.Archive;
import controleur.Commande;
import controleur.Tableau;
import modele.Modele;

public class Panelarchiveliste extends JPanel implements ActionListener{

	private JTable tableArchvie;	
	private Tableau unTableau;
	
	public Panelarchiveliste() {
		this.setBounds(150, 50, 500, 300);
		this.setLayout(null);
		
		//construction de la JTable
		String entetes []= {"Nom", "Prenom","Date de facturation","Prix de la facture","quantité"};
		Object [][]donnees = this.getDonnees();
		unTableau = new Tableau(donnees, entetes);
		this.tableArchvie = new JTable(unTableau);
		//insersion de la Jtable dans JScroll
		JScrollPane unScroll = new JScrollPane(tableArchvie);
		unScroll.setBounds(20,20,450,200);
		this.add(unScroll);
		
		
		this.setVisible(false);
	}
	private Object [][] getDonnees() {
		ArrayList<Archive> lesArchs = Modele.selectAllarch();
		Object donnees[][] = new Object [lesArchs.size()][5];
		int i =0;
		for (Archive uneArch : lesArchs) {
			donnees[i][0] = uneArch.getUti_nom()+"";
			donnees[i][1] = uneArch.getUti_prenom();
			donnees[i][2] = uneArch.getFact_reglement();
			donnees[i][3] = uneArch.getFact_prix();
			donnees[i][4] = uneArch.getFact_quantite();
			i++;
		}
		return donnees;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	public void actualiser() {
		unTableau.setDonnees(this.getDonnees());
	}

}
