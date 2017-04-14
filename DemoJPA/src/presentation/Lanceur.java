package presentation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import metier.Adresse;
import metier.Contact;
import metier.Film;
import metier.LongMetrage;
import metier.TeleFilm;

public class Lanceur {

	public static void main(String[] args) {
		// 1: ouverture de l'unité de persistence
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("demojpa-pu"); // "demojpa-pu" name de ma persistence dans persistence.xml
																						
		
		
		EntityManager em = emf.createEntityManager();

		// 2: ouverture de la transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		// 3: Création d'un objet metier
		Adresse s = new Adresse();
		s.setVille("LYON");
		s.setNumRue("160 rue Massena");
		s.setCodePostal("69006");

		// Film f = new Film();
		// f.setNomFilm("TOTO et TITI sur un bateau");

		Contact c = new Contact();
		c.setNom("ZORO");
		c.setPrenom("BOBY");
		c.setEmail("theo.villar.fr@gmail.com");
		c.setAdresse(s);
		// c.setAdresse(f);

		LongMetrage lm = new LongMetrage();
		lm.setNomFilm("James Bond 007");
		lm.setCinema("Gaumont");

		TeleFilm tf = new TeleFilm();
		tf.setChaine("TF1");
		tf.setNomFilm("Joséphine...");
		
		TeleFilm tf2 = new TeleFilm();
		tf2.setChaine("TF2");
		tf2.setNomFilm("TITI TOTO.");

		Collection<Film> films = new ArrayList<Film>();
		films.add(tf);
		films.add(tf2);
		films.add(lm);
		c.setFilm(films);

		// 4: Persistence de l'objet métier
		em.persist(tf2);//Pour qu'il prenne l'id 1
		em.persist(c);
		em.persist(lm);
		em.persist(tf);
	

		System.out.println(c);
		System.out.println(s);
		// 5: validation de la transaction
		tx.commit();

		// Récupérer Film d'ID 1
		Film f = em.find(Film.class, 1);
		System.out.println(f);
		f.setNomFilm("film modifié");
		tx.begin(); // Commence la transaction (une transaction qui modifit la BD)
					
		em.merge(f);
		tx.commit(); // On ferme la transaction

		// Suppression Film d'ID1
		tx.begin();
		em.remove(f);
		tx.commit(); 
		
		//selection de films en BD
		
		List<Film> listeFilm = em.createQuery("SELECT f FROM Film f").getResultList();
		for(Film fl : listeFilm)
		{
			System.out.println(fl);
		}
		
		//Recherche les films par nom de film
		Query q = em.createQuery("SELECT f FROM Film f where f.nomFilm = :leNom");
		q.setParameter("leNom", "James Bond 007");
		List<Film> listeFilm2 = q.getResultList();
		
		for(Film fl2 : listeFilm2)
		{
			System.out.println(fl2);
		}
		
		
		// Parfois il faut redemarrer la base de données
		Query q1 = em.createQuery("SELECT f FROM Film f where f.nomFilm LIKE:lettreFilm");
		q1.setParameter("lettreFilm", "%j%");
		List<Film> listeFilm3 = q1.getResultList();
		
		for(Film fl3 : listeFilm3)
		{
			System.out.println(fl3);
		}
		
		//exemple de getsingleresult
		Film f5= (Film) em.createQuery("SELECT f FROM Film f where f.idFilm = 2").getSingleResult();
		System.out.println(f5);
		
		//Exemple de jointure entre contact et adresse
		//Query q3 = em.createQuery("SELECT c FROM Contact c left join c.adresse");
		Query q3 = em.createQuery("SELECT c FROM Contact c ");
		List<Contact> listeC = q3.getResultList();
		for(Contact ct : listeC)
		{
			System.out.println(ct);
		}
		//appel d'une requête nommée
		System.out.println("Est ce que ca marche ? ");
		Query q4=em.createNamedQuery("Contact.findAll");
		List<Contact> lst = q4.getResultList();
		for(Contact ct : lst)
		{
			System.out.println(ct);
		}
		
		System.out.println("Est ce que ca marche 2 ? ");
		Query q5=em.createNamedQuery("Adresse1.findAll");
		List<Adresse>lst1 = q5.getResultList();
		for(Adresse ct1 : lst1)
		{
			System.out.println(ct1);
		}
		
		System.out.println("Est ce que ca marche 3 ? ");
		Query q6=em.createNamedQuery("Adresse2.findAll");
		q6.setParameter("ladresse", "%m%");
		List<Adresse> lst2 = q6.getResultList();
		for(Adresse ct2 : lst2)
		{
			System.out.println(ct2);
		}
		// 6: Fermeture de l'unité de persistence
		em.close();
		emf.close();
	}
}
