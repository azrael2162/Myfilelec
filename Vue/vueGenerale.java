package Vue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

 

import controleur.Main;

public class VueGenerale extends JFrame implements ActionListener
{

	private JButton btUsers =  new  JButton("Gestion des utilisateurs") ;
	private JButton btCommandes =  new  JButton("Gestion des Commandes") ;
	private JButton btQuitter =  new  JButton("Quitter") ;
	
	private static VueUser uneVueUser = new VueUser() ;
	private static VueCommandes uneVueCommandes = new VueCommandes() ;
	
	public static void rendreVisibleUser (boolean action)
	{
		uneVueUser.setVisible(action);
	}
	
	public static void rendreVisibleCommandes (boolean action)
	{
		uneVueCommandes.setVisible(action);
	}
	
	public VueGenerale()
	{
		this.setTitle("Application  Commandes");
		this.setBounds(100,100,600,450);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		this.getContentPane().setBackground(Color.CYAN);
		
		this.btUsers.setBounds(100, 100, 200, 50);
		this.add(this.btUsers);
		
		this.btCommandes.setBounds(350, 100, 200, 50);
		this.add(this.btCommandes);
		
		this.btQuitter.setBounds(200, 200, 200, 50);
		this.add(this.btQuitter);
		
		this.btQuitter.addActionListener(this);
		this.btCommandes.addActionListener(this);
		this.btUsers.addActionListener(this);
		
		
		this.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btUsers)
		{
			uneVueUser.setVisible(true);
			
		}else if(e.getSource() == this.btCommandes)
		{
			uneVueCommandes.setVisible(true);
			
		}else if(e.getSource() == this.btQuitter)
		{
				this.setVisible(false);
				Main.rendreVisible(true);
		}
		
	}
}
