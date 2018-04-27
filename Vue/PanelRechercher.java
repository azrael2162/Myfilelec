package Vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controleur.Etudiant;
import modele.Modele;

public class PanelRechercher extends JPanel implements ActionListener{

	
	JComboBox cbxIdEtudiant = new JComboBox();
	private JButton btOk = new JButton("Ok");
	private JTextArea txtResultat = new JTextArea();
	public PanelRechercher() {
		this.setBounds(130,50,370,300);
		this.setBackground(Color.magenta);
		this.setLayout(new GridLayout(5,2));
		
		this.cbxIdEtudiant.setBounds(100, 20, 200, 20);
		this.add(this.cbxIdEtudiant);
		this.btOk.setBounds(320,50,50,20);
		this.add(this.btOk);
		this.txtResultat.setBounds(50,100,340,250);
		this.add(this.txtResultat);
		
		this.remplirCBX();
		this.txtResultat.setEditable(false);
		
		this.btOk.addActionListener(this);
		
		this.setVisible(false);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btOk) {
			String chaine = this.cbxIdEtudiant.getSelectedItem().toString();
			String tab[] = chaine.split(" - ");
			int idEtudiant = Integer.parseInt(tab[0]);
			Etudiant unEtudiant = new Etudiant(idEtudiant, tab[1],tab[2],"","");
			unEtudiant = Modele.rechercherID(unEtudiant);
			if(unEtudiant != null) {
				chaine = "ID Etudiant : " + unEtudiant.getIdEtudiant() + "\n" +
					"Nom : " + unEtudiant.getNom() + "\n" +
					"Prenom : " + unEtudiant.getPrenom() + "\n" +
					"Email : " + unEtudiant.getEmail() + "\n" +
					"Classe : " + unEtudiant.getClasse() + "\n";
				this.txtResultat.setText(chaine);
			} else {
				this.txtResultat.setText("Aucun Etudian trouvé");
			}
			
		}
	}
	
	public void remplirCBX() {
		ArrayList<Etudiant> lesEtudiants = Modele.selectAll();
		this.cbxIdEtudiant.removeAllItems();
		for(Etudiant unEtudiant : lesEtudiants){
			String chaine = unEtudiant.getIdEtudiant() + " - " + unEtudiant.getNom() + " - " + unEtudiant.getPrenom() ;
			this.cbxIdEtudiant.addItem(chaine);
		}
	}
	public void actualiser() {
		this.remplirCBX();
	}
}
