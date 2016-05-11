package ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels;

import java.util.Date;

public class Zaposlenik implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String imePrezime;
	private String username;
	private String password;
	private Long radnoMjesto;
	private Date datumRodjenja;
	public Zaposlenik(Long id,String imePrezime,String username, String password, Long radnoMjesto, Date datumRodjenja){
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

	public Long getRadnoMjesto() {
		return radnoMjesto;
	}

	public void setRadnoMjesto(Long radnoMjesto) {
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
