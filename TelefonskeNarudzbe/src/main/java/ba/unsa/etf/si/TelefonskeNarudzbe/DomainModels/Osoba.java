package ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels;

public abstract class Osoba implements java.io.Serializable
{

	private static final long serialVersionUID = 1L;
	private String imePrezime;

	public String getImePrezime() {
		return imePrezime;
	}

	public void setImePrezime(String imePrezime) {
		this.imePrezime = imePrezime;
	}
	public Osoba()
	{
		
	}

}
