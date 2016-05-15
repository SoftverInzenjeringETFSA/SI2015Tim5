package ba.unsa.etf.si.TelefonskeNarudzbe;

public class JeloNotFound extends Exception {
	public JeloNotFound() {
		super();
	}

	public JeloNotFound(String poruka) {
		super(poruka);
	}

	public JeloNotFound(Throwable cause) {
		super(cause);
	}

	public JeloNotFound(String message, Throwable cause) {
		super(message, cause);
	}
}
