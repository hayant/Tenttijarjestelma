package tenttijarjestelma;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString(exclude = "vastausvaihtoehdot")
public class Tenttikysymys {

    public Tenttikysymys() {}

    public Tenttikysymys(String kysymys) {
        this.kysymys = kysymys;
    }
    
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "tenttikysymys_tentti", joinColumns = @JoinColumn(name = "tenttikysymys_id"), 
		inverseJoinColumns = @JoinColumn(name = "tentti_id"))
	private List<Tentti> tentit;
	
	@OneToMany(mappedBy = "tenttikysymys", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Vastausvaihtoehto> vastausvaihtoehdot = new ArrayList<>();
	
	private String kysymys;
	
}
