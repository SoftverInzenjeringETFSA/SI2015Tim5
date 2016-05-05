package ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels;

import java.util.List;

public class Jelo {
private String naziv;
private Double cijena;
private List<Sastojak> sastojci;

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

public List<Sastojak> getSastojci() {
	return sastojci;
}

public void setSastojci(List<Sastojak> sastojci) {
	this.sastojci = sastojci;
}

}
