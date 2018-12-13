package tenttijarjestelma;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Henkilo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String etunimi;
	private String sukunimi;
	
}
