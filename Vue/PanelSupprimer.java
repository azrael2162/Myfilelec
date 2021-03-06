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
import controleur.Tableau;
import modele.Modele;

public class PanelSupprimer extends JPanel implements ActionListener{

	private JTable tableUser;	
	private JPanel unPanel = new JPanel();
	private Tableau unTableau;
	
	public PanelSupprimer() {
		this.setBounds(130, 50, 370, 300);
		this.setBackground(Color.gray);
		this.setLayout(null);
		
		//construction de la JTable
		String entetes []= {"iduser", "Nom","Prénom","email", "login","password","grp_id"};
		Object donnees [][] = this.getDonnees(); //matrice
		
		this.unTableau = new Tableau(donnees, entetes);
		
		this.tableUser = new JTable(this.unTableau);
		//insersion de la Jtable dans JScroll
		JScrollPane unScroll = new JScrollPane(tableUser);
		unScroll.setBounds(20,20,330,200);
		this.add(unScroll);
		
		//ajouter le click sur la table 
		this.tableUser.addMouseListener(new MouseListener() {
			
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
				int ligne = tableUser.getSelectedRow();
				int uti_id = Integer.parseInt(tableUser.getValueAt(ligne, 0).toString());
				int retour = JOptionPane.showConfirmDialog(null, "Voulez vous supprimer ?","Confirmation", JOptionPane.YES_NO_OPTION);
				if(retour==0) {
					User unUser = new User(uti_id,"","","","","", retour);
					Modele.delete(unUser);
					JOptionPane.showMessageDialog(null, "Suppression réussie !");
					unTableau.delete(ligne);
				}
				
			}
		});
		
		
		this.setVisible(false);
	}
	private Object [][] getDonnees() {
		ArrayList<User> lesUsers = Modele.selectAll();
		Object donnees[][] = new Object [lesUsers.size()][7];
		int i =0;
		for (User unUser : lesUsers) {
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
