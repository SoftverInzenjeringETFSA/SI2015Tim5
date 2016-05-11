package ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "jelo")

public class Jelo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name="Id")
	private Long Id;
	@Column(unique=true)
	private String naziv;
	private Double cijena;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = SastojciJeloVeza.class)
	private List<SastojciJeloVeza> sastojciJeloVeza;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = NarudzbaJeloVeza.class)
	private List<NarudzbaJeloVeza> narudzbaJeloVeza;

	public Jelo(Long id, String naziv, Double cijena) {
		super();
		this.Id = id;
		
		this.naziv = naziv;
		this.cijena = cijena;
	}

	public List<SastojciJeloVeza> getSastojciJeloVeza() {
		return sastojciJeloVeza;
	}

	public void setSastojciJeloVeza(List<SastojciJeloVeza> sastojciJeloVeza) {
		this.sastojciJeloVeza = sastojciJeloVeza;
	}

	public List<NarudzbaJeloVeza> getNarudzbaJeloVeza() {
		return narudzbaJeloVeza;
	}

	public void setNarudzbaJeloVeza(List<NarudzbaJeloVeza> narudzbaJeloVeza) {
		this.narudzbaJeloVeza = narudzbaJeloVeza;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Double getCijena() {
		return cijena;
	}

	public void setCijena(Double cijena) {
		this.cijena = cijena;
	}

	public Jelo() {
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		this.Id = id;
	}
}
