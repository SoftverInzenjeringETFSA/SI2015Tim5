package ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels;

import java.util.Date;
import java.util.List;

public class Narudzba {
	private int idnarudzbe;
	private List<Jelo> listaJela;
	private Status statusNarudzbe;
	private double cijena;
	private Kupac narucioc;
	private Zaposlenik primaoc;
	private Date vrijemePrijema;
	private Zaposlenik kuhar;
	private Date vrijemePocetkaPripreme;
	private Date vrijemeZavrsetkaPripreme;
	private Zaposlenik dostavljac;
	private Date vrijemePreuzimanja;
	private Date vrijemeDostave;

	public int getIdnarudzbe() {
		return idnarudzbe;
	}

	public void setIdnarudzbe(int idnarudzbe) {
		this.idnarudzbe = idnarudzbe;
	}

	public List<Jelo> getListaJela() {
		return listaJela;
	}

	public void setListaJela(List<Jelo> listaJela) {
		this.listaJela = listaJela;
	}

	public Status getStatusNarudzbe() {
		return statusNarudzbe;
	}

	public void setStatusNarudzbe(Status statusNarudzbe) {
		this.statusNarudzbe = statusNarudzbe;
	}

	public double getCijena() {
		return cijena;
	}

	public void setCijena(double cijena) {
		this.cijena = cijena;
	}

	public Kupac getNarucioc() {
		return narucioc;
	}

	public void setNarucioc(Kupac narucioc) {
		this.narucioc = narucioc;
	}

	public Zaposlenik getPrimaoc() {
		return primaoc;
	}

	public void setPrimaoc(Zaposlenik primaoc) {
		this.primaoc = primaoc;
	}

	public Date getVrijemePrijema() {
		return vrijemePrijema;
	}

	public void setVrijemePrijema(Date vrijemePrijema) {
		this.vrijemePrijema = vrijemePrijema;
	}

	public Zaposlenik getKuhar() {
		return kuhar;
	}

	public void setKuhar(Zaposlenik kuhar) {
		this.kuhar = kuhar;
	}

	public Date getVrijemePocetkaPripreme() {
		return vrijemePocetkaPripreme;
	}

	public void setVrijemePocetkaPripreme(Date vrijemePocetkaPripreme) {
		this.vrijemePocetkaPripreme = vrijemePocetkaPripreme;
	}

	public Date getVrijemeZavrsetkaPripreme() {
		return vrijemeZavrsetkaPripreme;
	}

	public void setVrijemeZavrsetkaPripreme(Date vrijemeZavrsetkaPripreme) {
		this.vrijemeZavrsetkaPripreme = vrijemeZavrsetkaPripreme;
	}

	public Zaposlenik getDostavljac() {
		return dostavljac;
	}

	public void setDostavljac(Zaposlenik dostavljac) {
		this.dostavljac = dostavljac;
	}

	public Date getVrijemePreuzimanja() {
		return vrijemePreuzimanja;
	}

	public void setVrijemePreuzimanja(Date vrijemePreuzimanja) {
		this.vrijemePreuzimanja = vrijemePreuzimanja;
	}

	public Date getVrijemeDostave() {
		return vrijemeDostave;
	}

	public void setVrijemeDostave(Date vrijemeDostave) {
		this.vrijemeDostave = vrijemeDostave;
	}

}
