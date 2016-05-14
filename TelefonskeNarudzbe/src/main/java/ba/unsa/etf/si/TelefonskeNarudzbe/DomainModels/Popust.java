package ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels;

// default package
// Generated 12-May-2016 01:37:08 by Hibernate Tools 5.1.0.Alpha1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Jelo generated by hbm2java
 */
@Entity
@Table(name = "popust", catalog = "si2015tim5")
public class Popust implements java.io.Serializable {

	private Integer id;
	private Double od;
	private Double doo;
	private Double iznos;
	

	public Popust() {
	}

	
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "od", nullable = false, precision = 22, scale = 0)
	public Double getOd() {
		return od;
	}


	public void setOd(Double od) {
		this.od = od;
	}

	@Column(name = "do", nullable = false, precision = 22, scale = 0)
	public Double getDoo() {
		return doo;
	}


	public void setDoo(Double doo) {
		this.doo = doo;
	}

	@Column(name = "iznos", nullable = false, precision = 22, scale = 0)
	public Double getIznos() {
		return iznos;
	}


	public void setIznos(Double iznos) {
		this.iznos = iznos;
	}

}