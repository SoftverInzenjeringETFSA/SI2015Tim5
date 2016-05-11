
package ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Narudzba implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name="id", unique=true, nullable =false)
	private Long id;
	
	//private List<Jelo> listaJela;
	private Long statusNarudzbe;
	private double cijena;
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="KupacOsobaId", insertable = false, updatable = false)
	private Kupac narucioc;
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ZaposlenikOsobaId_Primalac", insertable = false, updatable = false)
	private Zaposlenik primaoc;
	private Date vrijemePrijema;
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ZaposlenikOsobaId_Kuhar", insertable = false, updatable = false)
	private Zaposlenik kuhar;
	private Date vrijemePocetkaPripreme;
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ZaposlenikOsobaId_Dostavljac", insertable = false, updatable = false)
	private Zaposlenik dostavljac;
	private Date vrijemePreuzimanja;
	private Date vrijemeDostave;
	private String opis; 
	private int status;
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Kupac getNarucioc() {
		return narucioc;
	}

	public Zaposlenik getPrimaoc() {
		return primaoc;
	}

	public Zaposlenik getKuhar() {
		return kuhar;
	}

	public Zaposlenik getDostavljac() {
		return dostavljac;
	}
	private Double novacaDostavljeno;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = NarudzbaJeloVeza.class)
	private List<NarudzbaJeloVeza> narudzbaJeloVeza;

	public Narudzba(Long id, Long statusNarudzbe, double cijena, Kupac narucioc, Zaposlenik primaoc, Date vrijemePrijema,
			Zaposlenik kuhar, Date vrijemePocetkaPripreme, Zaposlenik dostavljac, Date vrijemePreuzimanja, Date vrijemeDostave) {
		super();
		this.id= id;
		//private List<Jelo> listaJela;
		this.statusNarudzbe= statusNarudzbe;
		this.cijena= cijena;
		this.narucioc= narucioc;
		this.primaoc=primaoc;
		this.vrijemePrijema=vrijemePrijema;
		this.kuhar=kuhar;
		this.vrijemePocetkaPripreme= vrijemePocetkaPripreme;
		this.dostavljac= dostavljac;
		this.vrijemePreuzimanja= vrijemePreuzimanja;
		this.vrijemeDostave= vrijemeDostave;

	}
	
	public void setNarucioc(Kupac narucioc) {
		this.narucioc = narucioc;
	}

	public void setPrimaoc(Zaposlenik primaoc) {
		this.primaoc = primaoc;
	}

	public void setKuhar(Zaposlenik kuhar) {
		this.kuhar = kuhar;
	}

	public void setDostavljac(Zaposlenik dostavljac) {
		this.dostavljac = dostavljac;
	}

	public Long getStatusNarudzbe() {
		return statusNarudzbe;
	}
	public List<NarudzbaJeloVeza> getNarudzbaJeloVeza() {
		return narudzbaJeloVeza;
	}
	public void setNarudzbaJeloVeza(List<NarudzbaJeloVeza> narudzbaJeloVeza) {
		this.narudzbaJeloVeza = narudzbaJeloVeza;
	}
	public void setStatusNarudzbe(Long statusNarudzbe) {
		this.statusNarudzbe = statusNarudzbe;
	}

	public double getCijena() {
		return cijena;
	}

	public void setCijena(double cijena) {
		this.cijena = cijena;
	}

	

	public Date getVrijemePrijema() {
		return vrijemePrijema;
	}

	public void setVrijemePrijema(Date vrijemePrijema) {
		this.vrijemePrijema = vrijemePrijema;
	}

	
	public Date getVrijemePocetkaPripreme() {
		return vrijemePocetkaPripreme;
	}

	public void setVrijemePocetkaPripreme(Date vrijemePocetkaPripreme) {
		this.vrijemePocetkaPripreme = vrijemePocetkaPripreme;
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
	public Narudzba()
	{
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public Double getNovacaDostavljeno() {
		return novacaDostavljeno;
	}
	public void setNovacaDostavljeno(Double novacaDostavljeno) {
		this.novacaDostavljeno = novacaDostavljeno;
	}
}
