package com.app.web.util.reports;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.app.web.entity.User;
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

public class UserExporterPDF {
	private List<User> userList;

	public UserExporterPDF(List<User> userList) {
		this.userList = userList;
	}

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();

		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);

		cell.setPhrase(new Phrase("ID", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Nombre", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Apellido", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Nombre de usuario", font));
		table.addCell(cell);
	}

	private void writeTableData(PdfPTable table) {
		for (User user : userList) {
			table.addCell(String.valueOf(user.getId()));
			table.addCell(String.valueOf(user.getFirstname()));
			table.addCell(String.valueOf(user.getLastname()));
			table.addCell(String.valueOf(user.getUsername()));
		}
	}

	public void exportTable(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setColor(Color.BLUE);
		font.setSize(18);

		Paragraph title = new Paragraph("Lista de usuarios registrados", font);
		title.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(title);

		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(100);
		table.setSpacingBefore(15);
		table.setWidths(new float[] { 1f, 3f, 3f, 3f });
		table.setWidthPercentage(110);

		writeTableHeader(table);
		writeTableData(table);

		document.add(table);
		document.close();
	}
}
