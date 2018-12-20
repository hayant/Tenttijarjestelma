package entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class Vastausvaihtoehto {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "tenttikysymys_id")
	private Tenttikysymys tenttikysymys;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "vastausvaihtoehto", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Vastaus> vastaukset;
	
	private String vaihtoehto;
	private Boolean oikeaVastaus;
	
}
