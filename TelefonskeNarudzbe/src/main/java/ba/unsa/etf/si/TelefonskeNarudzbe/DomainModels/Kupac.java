package ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;



@Entity
public class Kupac implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name="id", unique=true, nullable =false)
	private Long id;
	private String adresa;
	private String brojTelefona;
	private String info;
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Narudzba.class)
	private List<Narudzba> narudzbe;
	
	public Kupac(Long id, String adresa, String brojTelefona){
	
		this.id= id;
		//private List<Jelo> listaJela;
		this.adresa= adresa;
		this.brojTelefona= brojTelefona;
		}
	
	public List<Narudzba> getNarudzbe() {
		return narudzbe;
	}
	public void setNarudzbe(List<Narudzba> narudzbe) {
		this.narudzbe = narudzbe;
	}
	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}
	public Kupac()
	{
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
