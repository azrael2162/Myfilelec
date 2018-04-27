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

import controleur.Etudiant;
import controleur.Tableau;
import modele.Modele;

public class PanelSupprimer extends JPanel implements ActionListener{

	private JTable tableEtudiants;	
	private JPanel unPanel = new JPanel();
	private Tableau unTableau;
	
	public PanelSupprimer() {
		this.setBounds(130, 50, 370, 300);
		this.setBackground(Color.gray);
		this.setLayout(null);
		
		//construction de la JTable
		String entetes []= {"idEtudiant", "Nom","Prénom","Email", "Classe"};
		Object donnees [][] = this.getDonnees(); //matrice
		
		this.unTableau = new Tableau(donnees, entetes);
		
		this.tableEtudiants = new JTable(this.unTableau);
		//insersion de la Jtable dans JScroll
		JScrollPane unScroll = new JScrollPane(tableEtudiants);
		unScroll.setBounds(20,20,330,200);
		this.add(unScroll);
		
		//ajouter le click sur la table 
		this.tableEtudiants.addMouseListener(new MouseListener() {
			
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
				int ligne = tableEtudiants.getSelectedRow();
				int idEtudiant = Integer.parseInt(tableEtudiants.getValueAt(ligne, 0).toString());
				int retour = JOptionPane.showConfirmDialog(null, "Voulez vous supprimer ?","Confirmation", JOptionPane.YES_NO_OPTION);
				if(retour==0) {
					Etudiant unEtudiant = new Etudiant(idEtudiant,"","","","");
					Modele.delete(unEtudiant);
					JOptionPane.showMessageDialog(null, "Suppression réussie !");
					unTableau.delete(ligne);
				}
				
			}
		});
		
		
		this.setVisible(false);
	}
	private Object [][] getDonnees() {
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
