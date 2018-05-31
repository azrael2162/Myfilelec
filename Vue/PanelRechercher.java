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

import controleur.User;
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
			int uti_id = Integer.parseInt(tab[0]);
			int grp_id = Integer.parseInt(tab[1]);
			User unUser = new User(uti_id, "","","","","",grp_id);
			unUser = Modele.rechercherID(unUser);
			if(unUser != null) {
				chaine = "ID User : " + unUser.getUti_id() + "\n" +
					"Nom : " + unUser.getUti_nom() + "\n" +
					"Prenom : " + unUser.getUti_prenom() + "\n" +
					"Email : " + unUser.getUti_email() + "\n" +
					"Login : " + unUser.getLogin() + "\n" +
					"Password : " + unUser.getPasswordd() + "\n" +
					"grP_id : " + unUser.getGrp_id() + "\n";
				this.txtResultat.setText(chaine);
			} else {
				this.txtResultat.setText("Aucun User trouvé");
			}
			
		}
	}
	
	public void remplirCBX() {
		ArrayList<User> lesEtudiants = Modele.selectAll();
		this.cbxIdEtudiant.removeAllItems();
		for(User unUser : lesEtudiants){
			String chaine = unUser.getUti_id() + " - " + unUser.getUti_nom() + " - " + unUser.getUti_prenom()+ "-" + unUser.getUti_email()+ "-" + unUser.getLogin()+ "-" + unUser.getPasswordd()+ "-" + unUser.getGrp_id();                               
			this.cbxIdEtudiant.addItem(chaine);
		}
	}
	public void actualiser() {
		this.remplirCBX();
	}
}
