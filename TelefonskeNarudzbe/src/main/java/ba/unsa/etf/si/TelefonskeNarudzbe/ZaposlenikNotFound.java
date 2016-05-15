package ba.unsa.etf.si.TelefonskeNarudzbe;

public class ZaposlenikNotFound extends Exception {

	public ZaposlenikNotFound() {
		super();
	}

	public ZaposlenikNotFound(String poruka) {
		super(poruka);
	}

	public ZaposlenikNotFound(Throwable cause) {
		super(cause);
	}

	public ZaposlenikNotFound(String message, Throwable cause) {
		super(message, cause);
	}

}
