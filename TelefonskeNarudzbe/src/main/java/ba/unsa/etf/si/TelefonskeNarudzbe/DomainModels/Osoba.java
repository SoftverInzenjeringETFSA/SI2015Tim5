package ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels;

public class Osoba implements java.io.Serializable
{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String imePrezime;
	public Osoba(Long id, String imePrezime){
		super();
			this.id=id;
			this.imePrezime= imePrezime;


		}
	public String getImePrezime() {
		return imePrezime;
	}

	public void setImePrezime(String imePrezime) {
		this.imePrezime = imePrezime;
	}
	public Osoba()
	{
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
