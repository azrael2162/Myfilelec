package Vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controleur.Etudiant;
import controleur.Tableau;
import modele.Modele;

public class PanelLister extends JPanel implements ActionListener{

	private JTable tableEtudiants;
	private Tableau unTableau;
	
	public PanelLister() {
		this.setBounds(130, 50, 370, 300);
		this.setBackground(Color.gray);
		this.setLayout(null);
		
		//construction de la JTable
		String entetes []= {"idEtudiant", "Nom","Prénom","Email", "Classe"};
		Object [][]donnees = this.getDonnees();
		unTableau = new Tableau(donnees, entetes);
		this.tableEtudiants = new JTable(unTableau);
		//insersion de la Jtable dans JScroll
		JScrollPane unScroll = new JScrollPane(tableEtudiants);
		unScroll.setBounds(20,20,330,200);
		this.add(unScroll);
		
		
		this.setVisible(false);
	}
	public Object [][] getDonnees() {
		ArrayList<Etudiant> lesEtudiants = Modele.selectAll();
	
		Object donnees[][] = new Object [lesEtudiants.size()][5];
		int i =0;
		for (Etudiant unEtudiant : lesEtudiants) {
			donnees[i][0] = unEtudiant.getIdEtudiant()+"";
			donnees[i][1] = unEtudiant.getNom();
			donnees[i][2] = unEtudiant.getPrenom();
			donnees[i][3] = unEtudiant.getEmail();
			donnees[i][4] = unEtudiant.getClass();
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
