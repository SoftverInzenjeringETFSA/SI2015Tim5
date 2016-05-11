package ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels;

public class NarudzbaJeloVeza implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long narudzba;
	private Long jelo;
	
	public NarudzbaJeloVeza()
	{
		
	}

	public Long getNarudzba() {
		return narudzba;
	}

	public void setNarudzba(Long narudzba) {
		this.narudzba = narudzba;
	}

	public Long getJelo() {
		return jelo;
	}

	public void setJelo(Long jelo) {
		this.jelo = jelo;
	}

}
