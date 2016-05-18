package ba.unsa.etf.si.TelefonskeNarudzbe.Controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

public class ValidacijaController {
	final static Logger logger = Logger.getLogger(UnosIzmjenaPopustaController.class);
	private static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();
	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	public static String vratiDecimalan(Double s){
		try{
		   DecimalFormat format = new DecimalFormat("#.00");
			    DecimalFormatSymbols dfs = new DecimalFormatSymbols();
			    dfs.setDecimalSeparator('.');
			    format.setDecimalFormatSymbols(dfs);
			    return format.format(s);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "greska");
			logger.info(e);
			return null;
		}
	}
	public static Double zaokruziNa2(Double s){
		try{
			return round(s, 2);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "greska");
			logger.info(e);
			return null;
		}
	}
	public static boolean manjeOd500(String s) {
		if (s.length() > 500)
			return false;
		return true;
	}

	public static boolean manjeOd50(String s) {
		if (s.length() > 50)
			return false;
		return true;
	}

	public static boolean daLiJeBroj(String s) {
		for (char c : s.toCharArray()) {
			if (!Character.isDigit(c))
				return false;
		}
		return true;
	}

	public static boolean jeLiDuzeOd3Slova(String s) {
		if (s.length() > 3)
			return true;
		return false;
	}
}
