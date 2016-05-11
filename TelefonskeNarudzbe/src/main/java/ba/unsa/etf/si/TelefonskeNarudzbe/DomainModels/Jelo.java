package ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels;

public class Jelo implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String naziv;
	private Double cijena;
	//private List<Sastojak> sastojci;
	public Jelo(Long id, String naziv, Double cijena) {
		super();
		this.id= id;
		//private List<Jelo> listaJela;
		this.naziv= naziv;
		this.cijena= cijena;
	}
	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Double getCijena() {
		return cijena;
	}

	public void setCijena(Double cijena) {
		this.cijena = cijena;
	}

	/*public List<Sastojak> getSastojci() {
		return sastojci;
	}

	public void setSastojci(List<Sastojak> sastojci) {
		this.sastojci = sastojci;
	}
*/
	public Jelo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
