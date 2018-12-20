package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tulos")
@IdClass(TulosId.class)
public class Tulos {

	public Tulos() {
		
	}
	
	public Tulos(Oppilas oppilas, Tenttikysymys tenttikysymys, Tentti tentti, Boolean kokoKysymysOikein) {
		this.oppilas = oppilas;
		this.oppilas_id = oppilas.getId();
		this.tentti = tentti;
		this.tentti_id = tentti.getId();
		this.tenttikysymys = tenttikysymys;
		this.tenttikysymys_id = tenttikysymys.getId();
		this.kokoKysymysOikein = kokoKysymysOikein;
	}
	
	@Id
	@Column
	private Long oppilas_id;

	@Id
	@Column
	private Long tenttikysymys_id;

	@Id
	@Column
	private Long tentti_id;
	
	@ManyToOne
	@JoinColumn(name = "oppilas_id", updatable = false, insertable = false, referencedColumnName = "id")
	private Oppilas oppilas;

	@ManyToOne
	@JoinColumn(name = "tenttikysymys_id", updatable = false, insertable = false, referencedColumnName = "id")
	private Tenttikysymys tenttikysymys;

	@ManyToOne
	@JoinColumn(name = "tentti_id", updatable = false, insertable = false, referencedColumnName = "id")
	private Tentti tentti;
	
	@Column
	private Integer pisteet;
	
	private Boolean kokoKysymysOikein = null;

}