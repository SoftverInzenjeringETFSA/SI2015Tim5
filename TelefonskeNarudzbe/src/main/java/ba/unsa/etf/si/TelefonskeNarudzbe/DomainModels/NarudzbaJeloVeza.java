

package ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class NarudzbaJeloVeza implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="NarudzbaId", insertable = false, updatable = false)
	private Narudzba narudzba;
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="JeloId", insertable = false, updatable = false)
	private Jelo jelo;

	private int kolicina;

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public NarudzbaJeloVeza(){
		
	}
    
	public NarudzbaJeloVeza(Long id, Narudzba narudzba, Jelo jelo)
	{
		this.id=id;
		this.narudzba=narudzba;
		this.jelo=jelo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Narudzba getNarudzba() {
		return narudzba;
	}

	public void setNarudzba(Narudzba narudzba) {
		this.narudzba = narudzba;
	}

	public Jelo getJelo() {
		return jelo;
	}

	public void setJelo(Jelo jelo) {
		this.jelo = jelo;
	}

	

	
}
