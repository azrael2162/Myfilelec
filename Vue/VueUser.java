package Vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Main;

public class VueUser extends JFrame implements ActionListener{

		private JPanel panelMenu = new JPanel();
		private JButton btTab[] = new JButton[5];
		private String tab []= {"Ajouter","Lister","Rechercher","Supprimer","Quitter"};		
		private static PanelAjout unPanelAjout = new PanelAjout();
		private static PanelLister unPanelLister = new PanelLister();
		private static PanelRechercher unPanelRechercher = new PanelRechercher();
		private static PanelSupprimer unPanelSupprimer= new PanelSupprimer();
		
		public VueUser() {
			this.setTitle("Application  Commandes");
			this.setBounds(100,100,600,450);
			this.setLayout(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setResizable(false);
			//this.getContentPane().setBackground(Color.yellow);
			
			//construction du panel Menu
			
			this.panelMenu.setBounds(10, 10, 120, 100);
			// this.panelMenu.setBackground(Color.yellow);
			this.panelMenu.setLayout(new GridLayout(7,1));
			
			this.panelMenu.add(new JLabel());
			for (int i = 0; i<tab.length;i++) {
				this.btTab[i] = new JButton (this.tab[i]);
			 
				this.panelMenu.add(this.btTab[i]);
				 
			}
			this.panelMenu.add(new JLabel());
			
			this.add(this.panelMenu);
			
			for (int i = 0; i<this.btTab.length;i++) {
				this.btTab[i].addActionListener(this);
			}
			
			//ajout des panneaux
			this.add(unPanelAjout);
			this.add(unPanelLister);
			this.add(unPanelRechercher);
			this.add(unPanelSupprimer);
			
			
			this.setVisible(false);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "Quitter" : 	this.dispose();
								VueGenerale.rendreVisibleUser(false);
								break;
			case "Ajouter" : 	unPanelAjout.setVisible(true);
								unPanelLister.setVisible(false);
								unPanelRechercher.setVisible(false);
								unPanelSupprimer.setVisible(false);
								break;
			case "Lister" : 	 unPanelAjout.setVisible(false);
								unPanelLister.actualiser();
								unPanelLister.setVisible(true);
								unPanelRechercher.setVisible(false);
								unPanelSupprimer.setVisible(false);
								break;
			case "Rechercher": 	unPanelAjout.setVisible(false);
								unPanelLister.setVisible(false);
								unPanelRechercher.actualiser();
								unPanelRechercher.setVisible(true);
								unPanelSupprimer.setVisible(false);
								break;
			case "Supprimer": 	unPanelAjout.setVisible(false);
								unPanelLister.setVisible(false);
								unPanelSupprimer.actualiser();
								unPanelRechercher.setVisible(false);
								unPanelSupprimer.setVisible(true);
								break;
		}
	
}
}
