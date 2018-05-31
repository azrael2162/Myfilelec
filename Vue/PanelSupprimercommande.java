package Vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controleur.User;
import controleur.Commande;
import controleur.Tableau;
import modele.Modele;

public class PanelSupprimercommande extends JPanel implements ActionListener{

	private JTable tablecommande;	
	private JPanel unPanel = new JPanel();
	private Tableau unTableau;
	
	public PanelSupprimercommande() {
		this.setBounds(150, 50, 450, 300);
		this.setBackground(Color.gray);
		this.setLayout(null);
		
		//construction de la JTable
		String entetes []= {"com_num", "com_date","com_text","com_prest","fact_num", "uti_id"};
		Object donnees [][] = this.getDonnees(); //matrice
		
		this.unTableau = new Tableau(donnees, entetes);
		
		this.tablecommande = new JTable(this.unTableau);
		//insersion de la Jtable dans JScroll
		JScrollPane unScroll = new JScrollPane(tablecommande);
		unScroll.setBounds(20,20,450,200);
		this.add(unScroll);
		
		//ajouter le click sur la table 
		this.tablecommande.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent m) {
				int ligne = tablecommande.getSelectedRow();
				int com_num = Integer.parseInt(tablecommande.getValueAt(ligne, 0).toString());
				int retour = JOptionPane.showConfirmDialog(null, "Voulez vous supprimer ?","Confirmation", JOptionPane.YES_NO_OPTION);
				if(retour==0) {
					Commande uneCommande = new Commande(com_num);
					Modele.deleteCommande(uneCommande);
					JOptionPane.showMessageDialog(null, "Suppression réussie !");
					unTableau.delete(ligne);
				}
				
			}
		});
		
		
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
