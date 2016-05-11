package ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels;

import java.util.Date;

public class Sastojak implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String naziv;
	private String opis;
	private String mjernaJedinica;
	public Sastojak(Long id,String naziv,String opis, String mjernaJedinica){
		super();
			//this.id= id;
			//private List<Jelo> listaJela;
			this.naziv=naziv;
			this.opis=opis;
			this.mjernaJedinica=mjernaJedinica;

		}
	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getMjernaJedinica() {
		return mjernaJedinica;
	}

	public void setMjernaJedinica(String mjernaJedinica) {
		this.mjernaJedinica = mjernaJedinica;
	}

	public Sastojak() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
