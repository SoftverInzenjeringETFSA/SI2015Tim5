package ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import java.util.List;

import javax.persistence.*;



@Entity

public class SastojciJeloVeza implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="SastojakId", insertable = false, updatable = false)
	private Sastojak sastojak;
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="JeloId", insertable = false, updatable = false)
	private Jelo jelo;
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Sastojak getSastojak() {
		return sastojak;
	}



	public void setSastojak(Sastojak sastojak) {
		this.sastojak = sastojak;
	}



	public Jelo getJelo() {
		return jelo;
	}



	public void setJelo(Jelo jelo) {
		this.jelo = jelo;
	}

	private int kolicina;

	public SastojciJeloVeza() {
	}



	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

}
