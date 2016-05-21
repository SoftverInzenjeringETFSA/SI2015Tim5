package ba.unsa.etf.si.TelefonskeNarudzbe.Controllers;

import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.*;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import javax.swing.JFileChooser;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Util.HibernateUtil;

public class RacunPDF {

	private Transaction t;
	private Session session = HibernateUtil.getSessionFactory().openSession();
	final static Logger logger = Logger.getLogger(RacunPDF.class);

	public void createPDF(Narudzba narudzba) {

		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int option = chooser.showSaveDialog(null);

		if (option == JFileChooser.APPROVE_OPTION) {
			DateFormat df = new SimpleDateFormat("ddMMyy-HHmmss");
			Date dateobj = new Date();

			String new_file_path = chooser.getSelectedFile().getAbsolutePath().toString() + "\\Racun" + narudzba.getId()
					+ ".pdf";

			Document document = new Document();
			try {

				Font boldFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new_file_path));
				document.open();

				PdfPTable table = new PdfPTable(3);
				table.setWidthPercentage(100);
				table.setSpacingBefore(10f);
				table.setSpacingAfter(10f);

				String[] top = { "Tim Petica",
						"Dostavljac :" + narudzba.getZaposlenikByZaposlenikOsobaIdDostavljac().getUsername(),
						narudzba.getVrijemePrijema().toString() };
				for (String s : top) {
					PdfPCell cell = new PdfPCell(new Paragraph(s, boldFont));
					cell.setPadding(10);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(cell);
				}
				document.add(table);

				PdfPTable headerTable = new PdfPTable(3);
				headerTable.setWidthPercentage(100);
				headerTable.setSpacingBefore(10f);
				headerTable.setSpacingAfter(10f);

				String[] header = { "Jelo  ", "Kolicina   ", "Cijena  " };

				for (int i = 0; i < header.length; i++) {
					PdfPCell cell = new PdfPCell(new Paragraph(header[i], boldFont));
					cell.setPadding(10);
					headerTable.addCell(cell);
				}

				document.add(headerTable);
				List<NarudzbaJeloVeza> veza = new ArrayList<NarudzbaJeloVeza>(narudzba.getNarudzbajelovezas());
				double cijena = 0;
				PdfPTable table_lot = new PdfPTable(3);
				table_lot.setWidthPercentage(100);
				table_lot.setSpacingBefore(10f);
				table_lot.setSpacingAfter(10f);
				for (NarudzbaJeloVeza v : veza) {

					cijena += v.getJelo().getCijena() * v.getKolicina();
					String[] kolone = { v.getJelo().getNaziv(), Integer.toString(v.getKolicina()),
							Double.toString(v.getJelo().getCijena()) };

					for (String celija : kolone) {
						PdfPCell cell = new PdfPCell(new Paragraph(celija));
						cell.setPadding(16);
						cell.setBorder(Rectangle.NO_BORDER);
						table_lot.addCell(cell);
					}

				}
				document.add(table_lot);

				PdfPTable ukupno = new PdfPTable(3);
				ukupno.setWidthPercentage(100);
				ukupno.setSpacingBefore(10f);
				ukupno.setSpacingAfter(10f);

				String[] kolone = { " ", " Ukupno:", Double.toString(cijena) };

				for (String celija : kolone) {
					PdfPCell cell = new PdfPCell(new Paragraph(celija));
					cell.setPadding(16);
					cell.setBorder(Rectangle.NO_BORDER);
					ukupno.addCell(cell);
				}
				document.add(ukupno);

				PdfPTable kupac = new PdfPTable(3);
				kupac.setWidthPercentage(100);
				kupac.setSpacingBefore(10f);
				kupac.setSpacingAfter(10f);
				String[] kolone1 = { " ", " Adresa: ", narudzba.getKupac().getAdresa() };

				for (String celija : kolone1) {
					PdfPCell cell = new PdfPCell(new Paragraph(celija));
					cell.setPadding(16);
					cell.setBorder(Rectangle.NO_BORDER);
					kupac.addCell(cell);
				}
				String[] kolone2 = { " ", " Broj telefona: ", narudzba.getKupac().getBrojTelefona().toString() };

				for (String celija : kolone2) {
					PdfPCell cell = new PdfPCell(new Paragraph(celija));
					cell.setPadding(16);
					cell.setBorder(Rectangle.NO_BORDER);
					kupac.addCell(cell);
				}
				String[] kolone3 = { " ", " Dodatne informacije: ", narudzba.getKupac().getInfo() };

				for (String celija : kolone3) {
					PdfPCell cell = new PdfPCell(new Paragraph(celija));
					cell.setPadding(16);
					cell.setBorder(Rectangle.NO_BORDER);
					kupac.addCell(cell);
				}
				document.add(kupac);

				document.close();
				writer.close();
			} catch (DocumentException e) {
				logger.info(e);
				System.out.println(e.getMessage());
			} catch (FileNotFoundException e) {
				logger.info(e);
				System.out.println(e.getMessage());
			}

			if (Desktop.isDesktopSupported()) {
				try {
					File myFile = new File(new_file_path);
					Desktop.getDesktop().open(myFile);
				} catch (IOException ex) {
					logger.info(ex);
				}
			}
		}

	}
}