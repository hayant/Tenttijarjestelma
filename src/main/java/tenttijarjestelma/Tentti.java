package tenttijarjestelma;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude="tenttikysymykset")
@Entity
public class Tentti {

	protected Tentti() {}

    public Tentti(String nimi) {
        this.nimi = nimi;
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @ManyToMany(mappedBy = "tentit", cascade = CascadeType.ALL)
    private List<Tenttikysymys> tenttikysymykset = new ArrayList<Tenttikysymys>();
    
    private String nimi;

}