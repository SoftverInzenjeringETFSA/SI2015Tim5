package ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels;

import java.util.Date;

public class Zaposlenik extends Osoba{
	private String username;
	private String password;
	private RadnoMjesto radnoMjesto;
	private Date datumRodjenja;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RadnoMjesto getRadnoMjesto() {
		return radnoMjesto;
	}

	public void setRadnoMjesto(RadnoMjesto radnoMjesto) {
		this.radnoMjesto = radnoMjesto;
	}

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

}
