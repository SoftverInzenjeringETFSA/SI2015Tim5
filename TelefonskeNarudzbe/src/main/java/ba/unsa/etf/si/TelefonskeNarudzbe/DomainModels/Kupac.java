package ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels;

public class Kupac extends Osoba implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String adresa;
	private String brojTelefona;

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
