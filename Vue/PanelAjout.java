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

import controleur.User;
import modele.Modele;

public class PanelAjout extends JPanel implements ActionListener {

		private JButton btEnregistrer = new JButton("Enregistrer");
		private JButton btAnnuler = new JButton("Annuler");
		private JTextField txtid = new JTextField("");
		private JTextField txtNom = new JTextField("");
		private JTextField txtPrenom = new JTextField("");
		private JTextField txtEmail = new JTextField("");
		private JTextField txtLogin = new JTextField("");
		private JTextField txtpassword = new JTextField("");
		private JTextField txtgrp_id = new JTextField("");

		public PanelAjout() {
			
			this.setBounds(130, 50, 370, 300);
			this.setBackground(Color.yellow);
			this.setLayout(new GridLayout(5,2));
			
			this.add(new JLabel("ID :"));
			this.add(this.txtid);
			this.add(new JLabel("Nom :"));
			this.add(this.txtNom);
			this.add(new JLabel("Prenom :"));
			this.add(this.txtPrenom);
			this.add(new JLabel("Email :"));
			this.add(this.txtEmail);
			this.add(new JLabel("Login :"));
			this.add(this.txtLogin);
			this.add(new JLabel("Password:"));
			this.add(this.txtpassword);
			this.add(new JLabel("Grp_id :"));
			this.add(this.txtgrp_id);
			
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
				
				User unUser = new User(Integer.parseInt(txtid.getText()),txtNom.getText(),txtPrenom.getText(),txtEmail.getText(), txtLogin.getText(),txtpassword.getText(),Integer.parseInt(txtgrp_id.getText()));
				if (unUser.verifSaisie()) {
					//insertion base de donnée
					Modele.insert(unUser);
					JOptionPane.showMessageDialog(this, "l'étudiant a été ajout avec succès","Ajout réussi", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "Erreur : Veuillez remplir les champs de saisie.","Erreur", JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
		}
			
		
			

		private void vider() {
			this.txtEmail.setText("");
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtid.setText("");
			this.txtNom.setText(""); 
			this.txtPrenom.setText("");
			this.txtEmail.setText("");
			this.txtLogin.setText("");
			this.txtpassword.setText("");
			this.txtgrp_id.setText("");

		}

}
