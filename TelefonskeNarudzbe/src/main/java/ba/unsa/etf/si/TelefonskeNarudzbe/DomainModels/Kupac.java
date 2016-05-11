package ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels;

import java.util.Date;

public class Kupac implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String imePrezime;
	private String adresa;
	private String brojTelefona;
	public Kupac(Long id,String imePrezime, String adresa, String brojTelefona){
	
		//this.id= id;
		//private List<Jelo> listaJela;
		this.adresa= adresa;
		this.brojTelefona= brojTelefona;


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
	public String getImePrezime() {
		return imePrezime;
	}
	public void setImePrezime(String imePrezime) {
		this.imePrezime = imePrezime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
