package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import controleur.Etudiant;

public class Modele {
	//REALISATION DES QUATRES FONCTIONS 
	public static ArrayList<Etudiant> selectAll() {
		ArrayList<Etudiant> lesEtudiants = new ArrayList<Etudiant>();
		Bdd uneBdd = new Bdd("localhost:3306", "etude", "root", "root");
		uneBdd.seConnecter();
		String requete = "select * from etudiant;";
		try{
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			while (unRes.next()) {
				int idEtudiant = unRes.getInt("idetudiant");
				String nom = unRes.getString("nom");
				String prenom = unRes.getString("prenom");
				String email = unRes.getString("email");
				String classe = unRes.getString("classe");
				Etudiant unEtudiant = new Etudiant(idEtudiant, nom, prenom, email, classe);
				lesEtudiants.add(unEtudiant);
			}
			unStat.close();
			unRes.close();
		}
		catch (SQLException exp) {
		System.out.println("Erreur de la requete" + requete);	
		}
		return lesEtudiants;
	}
	
	public static void insert (Etudiant unEtudiant) {
		String requete = "insert into etudiant values (null,'"+unEtudiant.getNom()+"','"+unEtudiant.getPrenom()+"','"+unEtudiant.getEmail()+"','"+unEtudiant.getClasse()+"');";
		Bdd uneBdd = new Bdd("localhost:8889", "ecoleBenahmed", "root", "root");
		uneBdd.seConnecter();
		try{
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
		} catch (SQLException exp) {
			System.out.println("Erreur requete : "+ requete);
		}
		uneBdd.seDeconnecter();
	}
	
	public static void delete (Etudiant unEtudiant) {
		String requete = "delete from etudiant where";
		String tab[] = new String [5];
		int i = 0;
		
		if (unEtudiant.getIdEtudiant() != 0) {
			tab[i++] = " idetudiant ="+unEtudiant.getIdEtudiant();
		}
		if (! unEtudiant.getNom().equals("")) {
			tab[i++] = " nom ='"+unEtudiant.getNom()+"'";
		}
		if (! unEtudiant.getPrenom().equals("")) {
			tab[i++] = " prenom ='"+unEtudiant.getPrenom()+"'";
		}
		if (! unEtudiant.getEmail().equals("")) {
			tab[i++] = " email ='"+unEtudiant.getEmail()+"'";
		}
		if (! unEtudiant.getClasse().equals("")) {
			tab[i++] = " classe ='"+unEtudiant.getClasse()+"'";
		}
		
		for (int j=0; j < i ; j++) {
			if (j == 0) {
				requete += tab[j];
			} else {
				requete += " and " + tab[j];
			}
		}
		Bdd uneBdd = new Bdd("localhost:8889", "ecoleBenahmed", "root", "root");
		uneBdd.seConnecter();
		try{
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
		} catch (SQLException exp) {
			System.out.println("Erreur requete :"+ requete);
		}
		uneBdd.seDeconnecter();
		}
	public static Etudiant rechercherID(Etudiant unEtudiant) {
		Etudiant resultat = null;
		
		String requete = "select * from etudiant where "
		+ "idetudiant = " + unEtudiant.getIdEtudiant()+";";
		Bdd uneBdd = new Bdd("localhost:3306", "etude", "root", "root");
		uneBdd.seConnecter();
		try{
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			if (unRes.next()) {
				int idEtudiant = unRes.getInt("idetudiant");
				String nom = unRes.getString("nom");
				String prenom = unRes.getString("prenom");
				String email = unRes.getString("email");
				String classe = unRes.getString("classe");
				resultat = new Etudiant(idEtudiant, nom, prenom, email, classe);
			}
			unStat.close();
			unRes.close();
		}
		catch (SQLException exp) {
		System.out.println("Erreur de la requete" + requete);	
		}
		return resultat;		
		}
	public static ArrayList<Etudiant> rechercher (String mot) {
		ArrayList<Etudiant> lesEtudiants = new ArrayList<Etudiant>();
		String requete = "select * from etudiant where "
		+ "nom like '%"+mot+"%' or "
		+ " prenom like '%"+mot+"%' or "
		+ " email like '%"+mot+"%' or "
		+ " classe like '%"+mot+"%' ; ";
		Bdd uneBdd = new Bdd("localhost", "etude", "root", "root");
		uneBdd.seConnecter();
		try{
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			while (unRes.next()) {
				int idEtudiant = unRes.getInt("idetudiant");
				String nom = unRes.getString("nom");
				String prenom = unRes.getString("prenom");
				String email = unRes.getString("email");
				String classe = unRes.getString("classe");
				Etudiant unEtudiant = new Etudiant(idEtudiant, nom, prenom, email, classe);
				lesEtudiants.add(unEtudiant);
			}
			unStat.close();
			unRes.close();
		}
		catch (SQLException exp) {
		System.out.println("Erreur de la requete" + requete);	
		}
		return lesEtudiants;
	}
	
	public static String [] verifConnexion (String login, String mdp) {
		String tab[] = new String [3];
		tab[0]= login;
		tab[1]= mdp;
		tab[2]= "";
		
		String requete="Select * from user where login ='"
				+login+"' AND mdp = '" +mdp+"' ;";
		Bdd uneBdd = new Bdd("localhost", "etude", "root", "root");
		uneBdd.seConnecter();
		try{
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			if (unRes.next()) {
				tab[2] = unRes.getString("droits");
			} 
		} catch (SQLException exp) {
			System.out.println("Erreur execution :" + requete);
		}
		uneBdd.seDeconnecter();
		return tab;
	}
}



















