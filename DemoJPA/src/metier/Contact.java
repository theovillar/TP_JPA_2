package metier;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Contact {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) // Adapte toi à la base de generation automatique que tu vas trouver
	@Column(name="CONTACT_ID")
	private int id;
	private String nom;
	private String prenom;
	private String email;
	@ManyToOne(cascade=CascadeType.PERSIST) //Permet de faire les opérations en cascade car sinon en même temps ca marche pas à cause des foreign key qui impose un ordre chronologique
	private Adresse adresse; // Un contact à une adresse
	@ManyToMany(mappedBy="contact")  //Celui qui à le mapped by est celuiqui à l'esclave ????????????
	private Collection<Film> film;
	
	public int getId() {
		return id;
	}
	public Collection<Film> getFilm() {
		return film;
	}
	public void setFilm(Collection<Film> film) {
		this.film = film;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Contact(int id, String nom, String prenom, String email) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}
	
	
	public Contact() {
		super();
	}
	@Override
	public String toString() {
		return "Contact [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + "]";
	}
}
