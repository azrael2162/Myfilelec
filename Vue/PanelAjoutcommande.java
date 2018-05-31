package Vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controleur.Commande;
import controleur.User;
import modele.Modele;

public class PanelAjoutcommande extends JPanel implements ActionListener {

		private JButton btEnregistrer = new JButton("Enregistrer");
		private JButton btAnnuler = new JButton("Annuler");
		private JTextField com_num = new JTextField("");
		private JTextField com_date = new JTextField("");
		private JTextField com_text = new JTextField("");
		private JTextField com_prest = new JTextField("");
		private JComboBox<String> uti_id = new JComboBox<String> ();
		private JTextField fact_num = new JTextField("");
		private JTextField id_livr = new JTextField("");

		public PanelAjoutcommande() {
			
			this.setBounds(130, 50, 370, 300);
			this.setBackground(Color.yellow);
			this.setLayout(new GridLayout(5,2));
			
			this.add(new JLabel("com_num :"));
			this.add(this.com_num);
			this.add(new JLabel("DATE :"));
			this.add(this.com_date);
			this.add(new JLabel("com_text :"));
			this.add(this.com_text);
			this.add(new JLabel("com_prest :"));
			this.add(this.com_prest);
			this.add(new JLabel("uti_id :"));
			this.add(this.uti_id);
			this.add(new JLabel("fact_num:"));
			this.add(this.fact_num);
			this.add(new JLabel("id_livr :"));
			this.add(this.id_livr);
			
			this.add(this.btAnnuler);
			this.add(this.btEnregistrer);
			remplirCBX();
			this.btAnnuler.addActionListener(this);
			this.btEnregistrer.addActionListener(this);
			
			this.setVisible(false);
		}

		private void remplirCBX() {
			ArrayList<User> lesUsers = Modele.selectAll();
			for (User unUser : lesUsers)
			{
				uti_id.addItem(unUser.getUti_id()+"");
			}
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "Annuler" :
				this.vider();
				break;
			case "Enregistrer" :
				int id = Integer.parseInt(uti_id.getSelectedItem().toString());
				Commande uneCommande = new Commande(Integer.parseInt(com_num.getText()),com_date.getText(),com_text.getText(),Integer.parseInt(com_prest.getText()),id, Integer.parseInt(fact_num.getText()));
				if (uneCommande.verifSaisie()) {
					//insertion base de donnée
					Modele.insertCommande(uneCommande);
					JOptionPane.showMessageDialog(this, "l'étudiant a été ajout avec succès","Ajout réussi", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "Erreur : Veuillez remplir les champs de saisie.","Erreur", JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
		}
			
		
			

		private void vider() {
			this.com_date.setText("");
			this.com_num.setText("");
			this.com_prest.setText("");
			this.com_text.setText("");
			this.id_livr.setText(""); 
			//this.uti_id.setText("");
			this.fact_num.setText("");
			

		}

}
