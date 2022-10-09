package com.app.web.util.reports;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.app.web.entity.History;

public class HistoryExporterExcel {
	private XSSFWorkbook book;
	private XSSFSheet sheet;

	private List<History> history;

	public HistoryExporterExcel(List<History> history) {
		this.history = history;
		book = new XSSFWorkbook();
		sheet = book.createSheet("Historial");
	}

	private void writeTableHeader() {
		Row row = sheet.createRow(0);

		CellStyle style = book.createCellStyle();
		XSSFFont font = book.createFont();
		font.setBold(true);
		font.setFontHeight(16d);
		style.setFont(font);

		Cell cell = row.createCell(0);
		cell.setCellValue("ID");
		cell.setCellStyle(style);

		cell = row.createCell(1);
		cell.setCellValue("Placa");
		cell.setCellStyle(style);

		cell = row.createCell(2);
		cell.setCellValue("Hora de entrada");
		cell.setCellStyle(style);

		cell = row.createCell(3);
		cell.setCellValue("Hora de salida");
		cell.setCellStyle(style);

		cell = row.createCell(4);
		cell.setCellValue("Pago");
		cell.setCellStyle(style);
	}

	private void writeTableData() {
		int rowNum = 1;

		CellStyle style = book.createCellStyle();
		XSSFFont font = book.createFont();
		font.setFontHeight(14d);
		style.setFont(font);

		for (History registry : history) {
			Row row = sheet.createRow(rowNum++);

			Cell cell = row.createCell(0);
			cell.setCellValue(registry.getId());
			sheet.autoSizeColumn(0);
			cell.setCellStyle(style);

			cell = row.createCell(1);
			cell.setCellValue(registry.getPlate());
			sheet.autoSizeColumn(1);
			cell.setCellStyle(style);

			cell = row.createCell(2);
			cell.setCellValue(registry.getEntry().toString());
			sheet.autoSizeColumn(2);
			cell.setCellStyle(style);

			cell = row.createCell(3);
			cell.setCellValue(registry.getExit().toString());
			sheet.autoSizeColumn(3);
			cell.setCellStyle(style);

			cell = row.createCell(4);
			cell.setCellValue(registry.getPayment());
			sheet.autoSizeColumn(4);
			cell.setCellStyle(style);
		}
	}

	public void exportTable(HttpServletResponse response) throws IOException {
		writeTableHeader();
		writeTableData();

		ServletOutputStream outputStream = response.getOutputStream();
		book.write(outputStream);

		book.close();
		outputStream.close();
	}
}