package tenttijarjestelma;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude="vastaukset")
@Entity
public class Oppilas {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String etunimi;
	private String sukunimi;
	
	@OneToMany(mappedBy = "oppilas", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Vastaus> vastaukset;
	
	@ManyToOne
	private Opettaja ope;
	
	public Oppilas () {	
	}
	
	public Oppilas(String etunimi, String sukunimi, Opettaja ope) {
		this.etunimi = etunimi;
		this.sukunimi = sukunimi;
		
		this.ope = ope;
	}

}
