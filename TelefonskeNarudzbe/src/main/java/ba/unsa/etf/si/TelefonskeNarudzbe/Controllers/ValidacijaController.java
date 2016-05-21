package ba.unsa.etf.si.TelefonskeNarudzbe.Controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

public class ValidacijaController {
	final static Logger logger = Logger.getLogger(UnosIzmjenaPopustaController.class);
	private static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();
	    BigDecimal bd = BigDecimal.valueOf(value);
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
	public static boolean validirajPassword(String password){
		boolean dobar = true;
		if(password.length()<8) return false;
		dobar = password.matches(".*\\d+.*");
		if(!dobar) return false;
		dobar = password.matches(".*[A-Z].*");
		if(!dobar) return false;
		dobar = password.matches(".*[a-z].*");
		if(!dobar) return false;
		return dobar;
	}
	
	public Boolean validirajDatum(String datum){
		String[] parts = datum.split(Pattern.quote("."));
		String dan = parts[0];
		String mjesec = parts[1];
		String godina = parts[2];
		try {
			int dd = Integer.parseInt(dan);
			int mm = Integer.parseInt(mjesec);
			int yyyy = Integer.parseInt(godina);
			
			Date sada = new Date();
			DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
			String reportDate = df.format(sada);
			
			if(validirajDatume(datum, reportDate) == false) return false;
			
			if(mm == 1 || mm == 3 || mm == 05 || mm == 07 
					|| mm == 8 || mm == 10 || mm == 12)
			{
				if(dd < 1 || dd> 31) return false; 
			}
		
			if(mm == 4 || mm == 6 || mm == 9 || mm == 11)
			{
				if(dd < 1 || dd> 30) return false; 
			}	
			if(mm == 2)
			{
				if(dd < 1 || dd> 29) return false; 
				if(dd == 29)
				{
					if(yyyy % 4 == 0 && yyyy % 100 != 0) return true;
					if(yyyy % 4 == 0 && yyyy % 400 == 0) return true;
					else return false;
				}
			}
			return true;
		} catch (Exception e) {
			logger.info(e);
			return false;
		}
	}
	public boolean validirajDatume(String datumOdString, String datumDoString) {
		String[] parts1 = datumOdString.split(Pattern.quote("."));
		String dan1 = parts1[0];
		String mjesec1 = parts1[1];
		String godina1 = parts1[2];
		String[] parts2 = datumDoString.split(Pattern.quote("."));
		String dan2 = parts2[0];
		String mjesec2 = parts2[1];
		String godina2 = parts2[2];
		int dd1 = Integer.parseInt(dan1);
		int mm1 = Integer.parseInt(mjesec1);
		int yyyy1 = Integer.parseInt(godina1);
		int dd2 = Integer.parseInt(dan2);
		int mm2 = Integer.parseInt(mjesec2);
		int yyyy2 = Integer.parseInt(godina2);
		if(yyyy2 < yyyy1) return false;
		if(yyyy2 == yyyy1 && mm2 < mm1) return false;
		if(yyyy2 == yyyy1 && mm2 == mm1 && dd2 < dd1) return false;
		return true;
	}
}
