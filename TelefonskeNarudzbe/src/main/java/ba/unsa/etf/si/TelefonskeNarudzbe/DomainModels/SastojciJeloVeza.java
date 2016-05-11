package ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels;

public class SastojciJeloVeza implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long sastojak;
	private Long jelo;
	private int kolicina;

	public SastojciJeloVeza() {
	}

	public Long getSastojak() {
		return sastojak;
	}

	public void setSastojak(Long sastojak) {
		this.sastojak = sastojak;
	}

	public Long getJelo() {
		return jelo;
	}

	public void setJelo(Long jelo) {
		this.jelo = jelo;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

}
