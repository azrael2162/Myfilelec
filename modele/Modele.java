package modele;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Formatter;

import controleur.Archive;
import controleur.Commande;
import controleur.User;



public class Modele {
	
	private static String encryptPassword(String password)
	{
	    String sha1 = "";
	    try
	    {
	        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	        crypt.reset();
	        crypt.update(password.getBytes("UTF-8"));
	        sha1 = byteToHex(crypt.digest());
	    }
	    catch(NoSuchAlgorithmException e)
	    {
	        e.printStackTrace();
	    }
	    catch(UnsupportedEncodingException e)
	    {
	        e.printStackTrace();
	    }
	    return sha1;
	}

	private static String byteToHex(final byte[] hash)
	{
	    Formatter formatter = new Formatter();
	    for (byte b : hash)
	    {
	        formatter.format("%02x", b);
	    }
	    String result = formatter.toString();
	    formatter.close();
	    return result;
	}
	
	
	//REALISATION DES QUATRES FONCTIONS 
	public static ArrayList<User> selectAll() {
		ArrayList<User> lesUser = new ArrayList<User>();
		Bdd uneBdd = new Bdd("localhost:3306", "bdd", "root", "root");
		uneBdd.seConnecter();
		String requete = "select * from  UTILISATEUR;";
		try{
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			while (unRes.next()) {
				int uti_id = unRes.getInt("uti_id");
				String uti_nom = unRes.getString("uti_nom");
				String uti_prenom = unRes.getString("uti_prenom");
				String uti_email = unRes.getString("uti_email");
				String login = unRes.getString("login");
				String passwordd = unRes.getString("passwordd");
				int grp_id = unRes.getInt("grp_id");

		User unUser = new User(uti_id, uti_nom, uti_prenom, uti_email,login,passwordd,grp_id);
				lesUser.add(unUser);
			}
			unStat.close();
			unRes.close();
		}
		catch (SQLException exp) {
		System.out.println("Erreur de la requete" + requete);	
		}
		return lesUser;
	}
	
	public static void insert (User unUser) {
		String requete = "insert into UTILISATEUR values (null,'"+unUser.getUti_nom()+"','"+unUser.getUti_prenom()+"','"+unUser.getUti_email()+"','"+unUser.getLogin()+"','"+unUser.getPasswordd()+"','"+unUser.getGrp_id()+"');";
		Bdd uneBdd = new Bdd("localhost:3306", "bdd", "root", "root");
		uneBdd.seConnecter();
		try{
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
		} catch (SQLException exp) {
			System.out.println("Erreur requete : "+ requete);
		}
		uneBdd.seDeconnecter();
	}
	
	public static void delete (User unUser) {
		String requete = "delete from UTILISATEUR where";
		String tab[] = new String [7];
		int i = 0;
		
		if (unUser.getUti_id() != 0) {
			tab[i++] = " uti_id ="+unUser.getUti_id();
		}
		if (! unUser.getUti_nom().equals("")) {
			tab[i++] = " uti_nom ='"+unUser.getUti_nom()+"'";
		}
		if (! unUser.getUti_prenom().equals("")) {
			tab[i++] = " uti_prenom ='"+unUser.getUti_prenom()+"'";
		}
		if (! unUser.getUti_email().equals("")) {
			tab[i++] = " uti_email ='"+unUser.getUti_email()+"'";
		}
		if (! unUser.getLogin().equals("")) {
			tab[i++] = " login ='"+unUser.getLogin()+"'";
		}
		if (! unUser.getPasswordd().equals("")) {
			tab[i++] = " passwordd ='"+unUser.getPasswordd()+"'";
		}
		if (unUser.getGrp_id() != 0) {
			tab[i++] = " grp_id ='"+unUser.getGrp_id();
		}
		
		for (int j=0; j < i ; j++) {
			if (j == 0) {
				requete += tab[j];
			} else {
				requete += " and " + tab[j];
			}
		}
		Bdd uneBdd = new Bdd("localhost:3006", "bdd", "root", "root");
		uneBdd.seConnecter();
		try{
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
		} catch (SQLException exp) {
			System.out.println("Erreur requete :"+ requete);
		}
		uneBdd.seDeconnecter();
		}
	
	public static User rechercherID(User unUser) {
		User resultat = null;
		
		String requete = "select * from UTILISATEUR where "
		+ "idUser = " + unUser.getUti_id()+";";
		Bdd uneBdd = new Bdd("localhost:3306", "bdd", "root", "root");
		uneBdd.seConnecter();
		try{
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			if (unRes.next()) {
				int uti_id = unRes.getInt("uti_id");
				String uti_nom = unRes.getString("uti_nom");
				String uti_prenom = unRes.getString("uti_prenom");
				String uti_email = unRes.getString("uti_email");
				String login = unRes.getString("login");
				String passwordd = unRes.getString("passwordd");
				int grp_id = unRes.getInt("grp_id");
				resultat = new User(uti_id, uti_nom, uti_prenom, uti_email,login,passwordd,grp_id);
			}
			unStat.close();
			unRes.close();
		}
		catch (SQLException exp) {
		System.out.println("Erreur de la requete" + requete);	
		}
		return resultat;		
		}
	public static ArrayList<User> rechercher (String mot) {
		ArrayList<User> lesUsers = new ArrayList<User>();
		String requete = "select * from UTILISATEUR where "
		+ "nom like '%"+mot+"%' or "
		+ " prenom like '%"+mot+"%' or "
		+ " email like '%"+mot+"%' or "	
		+ " classe like '%"+mot+"%' ; ";
		Bdd uneBdd = new Bdd("localhost", "bdd", "root", "root");
		uneBdd.seConnecter();
		try{
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			while (unRes.next()) {
				int uti_id = unRes.getInt("uti_id");
				String uti_nom = unRes.getString("uti_nom");
				String uti_prenom = unRes.getString("uti_prenom");
				String uti_email = unRes.getString("uti_email");
				String login = unRes.getString("login");
				String passwordd = unRes.getString("passwordd");
				int grp_id = unRes.getInt("grp_id");
				User unUser = new User(uti_id, uti_nom, uti_prenom, uti_email,login,passwordd,grp_id);
				lesUsers.add(unUser);
			}
			unStat.close();
			unRes.close();
		}
		catch (SQLException exp) {
		System.out.println("Erreur de la requete" + requete);	
		}
		return lesUsers;
	}
	
	public static String [] verifConnexion (String login, String mdp) {
		String tab[] = new String [3];
		tab[0]= login;
		tab[1]= mdp;
		tab[2]= "";
		
		//cryptage du mdp :
		mdp = Modele.encryptPassword(mdp);
		System.out.println(mdp);
		
		String requete="Select * from utilisateur where login ='"
				+login+"' AND passwordd = '" +mdp+"' ;";
		Bdd uneBdd = new Bdd("localhost:3306", "bdd", "root", "root");
		uneBdd.seConnecter();
		try{
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			if (unRes.next()) {
				tab[2] = unRes.getString("grp_id");
			} 
		} catch (SQLException exp) {
			System.out.println("Erreur execution :" + requete);
		}
		uneBdd.seDeconnecter();
		return tab;
	}
//----------------------------------------------------------------------------
	//fonction pour lister les commandes
	public static ArrayList<Commande> selectAllCom() {
		ArrayList<Commande> lesCommandes = new ArrayList<Commande>();
		Bdd uneBdd = new Bdd("localhost:3306", "bdd", "root", "root");
		uneBdd.seConnecter();
		String requete = "select * from  COMMANDE;";
		try{
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			while (unRes.next()) {
				int com_num = unRes.getInt("com_num");
				String com_date = unRes.getString("com_date");
				String com_text = unRes.getString("com_text");
				int com_prest = unRes.getInt("com_prest");
				int uti_id = unRes.getInt("uti_id");
				int fact_num = unRes.getInt("fact_num");

				Commande uneCommande = new Commande(com_num, com_date, com_text, com_prest,uti_id,fact_num);
				lesCommandes.add(uneCommande);
			}
			unStat.close();
			unRes.close();
		}
		catch (SQLException exp) {
		System.out.println("Erreur de la requete" + requete);	
		}
		return lesCommandes;
	}
	//fonction pour ajouter
	public static void insertCommande (Commande uneCommande) {
		String requete = "insert into Commande values (null,'"+uneCommande.getCom_date()+"','"+uneCommande.getCom_text()+"','"+uneCommande.getCom_prest()+"','"+uneCommande.getUti_id()+"','"+uneCommande.getFact_num()+"');";
		Bdd uneBdd = new Bdd("localhost:3306", "bdd", "root", "root");
		uneBdd.seConnecter();
		try{
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
		} catch (SQLException exp) {
			System.out.println("Erreur requete : "+ requete);
		}
		uneBdd.seDeconnecter();
	}
	
	//fonction pour supprimer commande  
	public static void deleteCommande (Commande uneCommande) {
		String requete = "delete from COMMANDE where com_num ="+uneCommande.getCom_num()+";";
		
		Bdd uneBdd = new Bdd("localhost:306", "bdd", "root", "root");
		uneBdd.seConnecter();
		try{
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
		} catch (SQLException exp) {
			System.out.println("Erreur requete :"+ requete);
		}
		uneBdd.seDeconnecter();
		}
	//-----------------------------------------------------------
	
	//fonction pour archiver
	
	//fonction pour lister les commandes
		public static ArrayList<Archive> selectAllarch() {
			ArrayList<Archive> lesArchives = new ArrayList<Archive>();
			Bdd uneBdd = new Bdd("localhost:3306", "bdd", "root", "root");
			uneBdd.seConnecter();
			String requete = "select * from  infoarch;";
			try{
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				ResultSet unRes = unStat.executeQuery(requete);
				while (unRes.next()) {
					String uti_nom = unRes.getString("uti_nom");
					String uti_prenom = unRes.getString("uti_prenom");
					String fact_reglement = unRes.getString("fact_reglement");
					int fact_prix = unRes.getInt("fact_prix");
					int  fact_quantite = unRes.getInt("fact_quantite");

					Archive uneArchive = new Archive(uti_nom, uti_prenom, fact_reglement, fact_prix,fact_quantite);
					lesArchives.add(uneArchive);
				}
				unStat.close();
				unRes.close();
			}
			catch (SQLException exp) {
			System.out.println("Erreur de la requete" + requete);	
			}
			return lesArchives;
		}
}



















