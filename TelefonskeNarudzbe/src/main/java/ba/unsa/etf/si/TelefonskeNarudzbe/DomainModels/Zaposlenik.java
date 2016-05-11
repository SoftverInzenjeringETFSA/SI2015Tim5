package ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Zaposlenik implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	private String imePrezime;

	@Column(unique=true)

	private String username;
	private String password;
	private int radnoMjesto;
	private Date datumRodjenja;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Narudzba.class)
	private List<Narudzba> narudzbe;
	
	
	public Zaposlenik(Long id,String imePrezime,String username, String password, int radnoMjesto, Date datumRodjenja){
			//this.id= id;
			//private List<Jelo> listaJela;
			this.username=username;
			this.password=password;
			this.radnoMjesto=radnoMjesto;
			this.datumRodjenja= datumRodjenja;


		}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRadnoMjesto() {
		return radnoMjesto;
	}

	public void setRadnoMjesto(int radnoMjesto) {
		this.radnoMjesto = radnoMjesto;
	}

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	public Zaposlenik()
	{
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImePrezime() {
		return imePrezime;
	}
	public void setImePrezime(String imePrezime) {
		this.imePrezime = imePrezime;
	}
}
