package metier;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

@Entity
//@Inheritance(strategy=InheritanceType.SINGLE_TABLE) // Permet l'héritage
//@DiscriminatorColumn(name="TYPE_FILM")
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS) // p99
@Inheritance(strategy=InheritanceType.JOINED) // classe mère + classe fille
public abstract class Film {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) // Adapte toi à la base de generation automatique que tu vas trouver
//	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="FILM_ID")
	private int idFilm;
	private String nomFilm;
	@ManyToMany(cascade=CascadeType.PERSIST)
	private Collection<Contact> contact;
	
	
	public Collection<Contact> getContact() {
		return contact;
	}
	public void setContact(Collection<Contact> contact) {
		this.contact = contact;
	}
	public int getIdFilm() {
		return idFilm;
	}
	public void setIdFilm(int idFilm) {
		this.idFilm = idFilm;
	}
	public String getNomFilm() {
		return nomFilm;
	}
	public void setNomFilm(String nomFilm) {
		this.nomFilm = nomFilm;
	}
	@Override
	public String toString() {
		return "Film [idFilm=" + idFilm + ", nomFilm=" + nomFilm + "]";
	}
	

}
