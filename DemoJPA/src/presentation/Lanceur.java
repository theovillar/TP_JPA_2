package presentation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import metier.Adresse;
import metier.Contact;
import metier.LongMetrage;
import metier.TeleFilm;

public class Lanceur {

	public static void main(String[] args) {
		// 1: ouverture de l'unité de persistence
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("demojpa-pu"); //"demojpa-pu" name de ma persistence dans persistence.xml
		EntityManager em = emf.createEntityManager();
		
		// 2: ouverture de la transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		// 3: Création d'un objet metier 
		Adresse s = new Adresse();
		s.setVille("LYON");
		s.setNumRue("160 rue Massena");
		s.setCodePostal("69006");
		
	//	Film f = new Film(); 
//		f.setNomFilm("TOTO et TITI sur un bateau");
		
		Contact c = new Contact(); 
		c.setNom("ZORO");
		c.setPrenom("BOBY");
		c.setEmail("theo.villar.fr@gmail.com");
		c.setAdresse(s);
	//	c.setAdresse(f);
		
		LongMetrage lm = new LongMetrage();
		lm.setNomFilm("film1");
		lm.setCinema("Gaumont");
		
		TeleFilm tf = new TeleFilm();
		tf.setChaine("TF1");
		tf.setNomFilm("Joséphine...");
		

		// 4: Persistence de l'objet métier 
		em.persist(c);
		em.persist(lm);
		em.persist(tf);
		
		
		// 5: validation de la transaction
		tx.commit();
		
		// 6: Fermeture de l'unité de persistence
		em.close();
		emf.close();

	}

}
