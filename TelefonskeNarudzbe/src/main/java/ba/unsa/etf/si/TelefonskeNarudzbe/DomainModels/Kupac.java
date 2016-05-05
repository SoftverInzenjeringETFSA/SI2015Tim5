package ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels;

public class Kupac extends Osoba {
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

}
