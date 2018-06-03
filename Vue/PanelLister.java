package Vue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controleur.User;
import controleur.Tableau;
import modele.Modele;

public class PanelLister extends JPanel implements ActionListener{

	private JTable tableUser;
	private Tableau unTableau;
	
	public PanelLister() {
		this.setBounds(130, 50, 370, 300);
		this.setLayout(null);
		
		//construction de la JTable
		String entetes []= {"iduser", "Nom","Prénom","Email", "login", "password", "grp_id"};
		Object [][]donnees = this.getDonnees();
		unTableau = new Tableau(donnees, entetes);
		this.tableUser = new JTable(unTableau);
		//insersion de la Jtable dans JScroll
		JScrollPane unScroll = new JScrollPane(tableUser);
		unScroll.setBounds(20,20,330,200);
		this.add(unScroll);
		
		
		this.setVisible(false);
	}
	public Object [][] getDonnees() {
		ArrayList<User> lesUser = Modele.selectAll();
	
		Object donnees[][] = new Object [lesUser.size()][7];
		int i =0;
		for (User unUser : lesUser) {
			donnees[i][0] = unUser.getUti_id()+"";
			donnees[i][1] = unUser.getUti_nom();
			donnees[i][2] = unUser.getUti_prenom();
			donnees[i][3] = unUser.getUti_email();
			donnees[i][4] = unUser.getLogin();
			donnees[i][5] = unUser.getPasswordd();
			donnees[i][6] = unUser.getGrp_id();

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
