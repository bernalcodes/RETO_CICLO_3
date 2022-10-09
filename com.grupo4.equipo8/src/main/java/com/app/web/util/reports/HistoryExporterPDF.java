package com.app.web.util.reports;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.app.web.entity.History;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class HistoryExporterPDF {
	private List<History> history;

	public HistoryExporterPDF(List<History> history) {
		this.history = history;
	}

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();

		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);

		cell.setPhrase(new Phrase("ID", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Placa", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Hora de entrada", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Hora de salida", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Pago", font));
		table.addCell(cell);
	}

	private void writeTableData(PdfPTable table) {
		for (History registry : history) {
			table.addCell(String.valueOf(registry.getId()));
			table.addCell(String.valueOf(registry.getPlate()));
			table.addCell(String.valueOf(registry.getEntry().toString()));
			table.addCell(String.valueOf(registry.getExit().toString()));
			table.addCell(String.valueOf(registry.getPayment()));
		}
	}

	public void exportTable(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setColor(Color.BLUE);
		font.setSize(18);

		Paragraph title = new Paragraph("Historial del parqueadero", font);
		title.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(title);

		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100);
		table.setSpacingBefore(15);
		table.setWidths(new float[] { 2f, 2f, 2f, 2f, 2f });
		table.setWidthPercentage(110);

		writeTableHeader(table);
		writeTableData(table);

		document.add(table);
		document.close();
	}
}
