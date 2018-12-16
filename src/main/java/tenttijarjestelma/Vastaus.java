package tenttijarjestelma;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@IdClass(VastausId.class)
public class Vastaus implements Serializable {
	
	private static final long serialVersionUID = -2968688507150222018L;

	@Id
	private Long oppilas_id;
	
	@Id
	private Long vastausvaihtoehto_id;

	@Id
	private Long tentti_id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "oppilas_id", updatable = false, insertable = false, referencedColumnName = "id")
	private Oppilas oppilas;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vastausvaihtoehto_id", updatable = false, insertable = false, referencedColumnName = "id")
	private Vastausvaihtoehto vastausvaihtoehto;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tentti_id", updatable = false, insertable = false, referencedColumnName = "id")
	private Tentti tentti;
	
	private Boolean oppilaanVastaus = null;
	
	private Boolean menikoOikein = null;
	
}
