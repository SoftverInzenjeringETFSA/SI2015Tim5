package ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels;

// default package
// Generated 12-May-2016 01:37:08 by Hibernate Tools 5.1.0.Alpha1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * SastojciJeloVeza generated by hbm2java
 */
@Entity
@Table(name = "sastojci_jelo_veza", catalog = "tim5")
public class SastojciJeloVeza implements java.io.Serializable {

	private Integer id;
	private Jelo jelo;
	private Sastojak sastojak;
	private Double kolicina;

	public SastojciJeloVeza() {
	}

	public SastojciJeloVeza(Jelo jelo, Sastojak sastojak, Double kolicina) {
		this.jelo = jelo;
		this.sastojak = sastojak;
		this.kolicina = kolicina;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "JeloId", nullable = false)
	public Jelo getJelo() {
		return this.jelo;
	}

	public void setJelo(Jelo jelo) {
		this.jelo = jelo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SastojakId", nullable = false)
	public Sastojak getSastojak() {
		return this.sastojak;
	}

	public void setSastojak(Sastojak sastojak) {
		this.sastojak = sastojak;
	}

	@Column(name = "kolicina", nullable = false)
	public double getKolicina() {
		return this.kolicina;
	}

	public void setKolicina(Double double1) {
		this.kolicina = double1;
	}

}
