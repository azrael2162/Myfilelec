package Vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controleur.Etudiant;
import modele.Modele;

public class PanelAjout extends JPanel implements ActionListener {

		private JButton btEnregistrer = new JButton("Enregistrer");
		private JButton btAnnuler = new JButton("Annuler");
		private JTextField txtNom = new JTextField("");
		private JTextField txtPrenom = new JTextField("");
		private JTextField txtEmail = new JTextField("");
		private JTextField txtClasse = new JTextField("");

		public PanelAjout() {
			
			this.setBounds(130, 50, 370, 300);
			this.setBackground(Color.yellow);
			this.setLayout(new GridLayout(5,2));
			
			this.add(new JLabel("Nom :"));
			this.add(this.txtNom);
			this.add(new JLabel("Prenom :"));
			this.add(this.txtPrenom);
			this.add(new JLabel("Email :"));
			this.add(this.txtEmail);
			this.add(new JLabel("Classe :"));
			this.add(this.txtClasse);
			
			this.add(this.btAnnuler);
			this.add(this.btEnregistrer);
			
			this.btAnnuler.addActionListener(this);
			this.btEnregistrer.addActionListener(this);
			
			this.setVisible(false);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "Annuler" :
				this.vider();
				break;
			case "Enregistrer" : 
				Etudiant unEtudiant = new Etudiant(txtNom.getText(),txtPrenom.getText(), txtEmail.getText(), txtClasse.getText());
				if (unEtudiant.verifSaisie()) {
					//insertion base de donnée
					Modele.insert(unEtudiant);
					JOptionPane.showMessageDialog(this, "l'étudiant a été ajout avec succès","Ajout réussi", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "Erreur : Veuillez remplir les champs de saisie.","Erreur", JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
		}
			
		
			

		private void vider() {
			this.txtClasse.setText("");
			this.txtEmail.setText("");
			this.txtNom.setText("");
			this.txtPrenom.setText("");
		}

}
