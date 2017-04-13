package metier;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Adresse {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) // Adapte toi à la base de generation automatique que tu vas trouver
	@Column(name="ADRESSE_ID")
	private int id; 
	private String numRue;
	private String ville;
	private String codePostal;
	@Transient
	private String commentaires;
	@OneToMany(mappedBy="adresse") // Deux chemin devient 1 chemin, relation maitre esclave 
	private Collection<Contact> contact; // Un contact à plusieurs adresse
	public Adresse() {
		super();
	}
	
	
	public Collection<Contact> getContact() {
		return contact;
	}
	public void setContact(Collection<Contact> contact) {
		this.contact = contact;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumRue() {
		return numRue;
	}
	public void setNumRue(String numRue) {
		this.numRue = numRue;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	@Override
	public String toString() {
		return "Adresse [id=" + id + ", numRue=" + numRue + ", ville=" + ville + ", codePostal=" + codePostal + "]";
	}

}
