package entity;

import java.io.Serializable;

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
@Table(name="tenttisuoritus")
@IdClass(TenttisuoritusId.class)
public class Tenttisuoritus implements Serializable {
	
	@Id
	Long oppilas_id;
	
	@Id
	Long tentti_id;
	
	@Column(name = "pisteet")
	private int pisteet; 
	
	@ManyToOne
	@JoinColumn(name="oppilas_id",updatable = false, insertable = false, referencedColumnName = "id")
	private Oppilas oppilas;

	@ManyToOne
	@JoinColumn(name="tentti_id", updatable = false, insertable = false, referencedColumnName = "id")
	private Tentti tentti;

}