package ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels;

import java.util.Date;

public class Kupac extends Osoba implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String adresa;
	private String brojTelefona;
	public Kupac(Long id,String imePrezime, String adresa, String brojTelefona){
	super(id,imePrezime);
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
}
