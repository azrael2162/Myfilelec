package Vue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controleur.User;
import controleur.Commande;
import controleur.Tableau;
import modele.Modele;

public class PanelListercommande extends JPanel implements ActionListener{

	private JTable tablecommande;	
	private Tableau unTableau;
	
	public PanelListercommande() {
		this.setBounds(150, 50, 500, 300);
		this.setBackground(Color.gray);
		this.setLayout(null);
		
		//construction de la JTable
		String entetes []= {"com_num", "com_date","com_text","com_prest","fact_num", "uti_id"};
		Object [][]donnees = this.getDonnees();
		unTableau = new Tableau(donnees, entetes);
		this.tablecommande = new JTable(unTableau);
		//insersion de la Jtable dans JScroll
		JScrollPane unScroll = new JScrollPane(tablecommande);
		unScroll.setBounds(20,20,450,200);
		this.add(unScroll);
		
		
		this.setVisible(false);
	}
	private Object [][] getDonnees() {
		ArrayList<Commande> lesCmds = Modele.selectAllCom();
		Object donnees[][] = new Object [lesCmds.size()][6];
		int i =0;
		for (Commande uneCmde : lesCmds) {
			donnees[i][0] = uneCmde.getCom_num()+"";
			donnees[i][1] = uneCmde.getCom_date();
			donnees[i][2] = uneCmde.getCom_text();
			donnees[i][3] = uneCmde.getCom_prest();
			donnees[i][4] = uneCmde.getFact_num();
			donnees[i][5] = uneCmde.getUti_id();
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
