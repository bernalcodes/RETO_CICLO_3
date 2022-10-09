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

import com.app.web.entity.User;

public class UserExporterExcel {
	private XSSFWorkbook book;
	private XSSFSheet sheet;

	private List<User> userList;

	public UserExporterExcel(List<User> userList) {
		this.userList = userList;
		book = new XSSFWorkbook();
		sheet = book.createSheet("Usuarios");
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
		cell.setCellValue("Nombre");
		cell.setCellStyle(style);

		cell = row.createCell(2);
		cell.setCellValue("Apellido");
		cell.setCellStyle(style);

		cell = row.createCell(3);
		cell.setCellValue("Nombre de usuario");
		cell.setCellStyle(style);
	}

	private void writeTableData() {
		int rowNum = 1;

		CellStyle style = book.createCellStyle();
		XSSFFont font = book.createFont();
		font.setFontHeight(14d);
		style.setFont(font);

		for (User user : userList) {
			Row row = sheet.createRow(rowNum++);

			Cell cell = row.createCell(0);
			cell.setCellValue(user.getId());
			sheet.autoSizeColumn(0);
			cell.setCellStyle(style);

			cell = row.createCell(1);
			cell.setCellValue(user.getFirstname());
			sheet.autoSizeColumn(1);
			cell.setCellStyle(style);

			cell = row.createCell(2);
			cell.setCellValue(user.getLastname());
			sheet.autoSizeColumn(2);
			cell.setCellStyle(style);

			cell = row.createCell(3);
			cell.setCellValue(user.getUsername());
			sheet.autoSizeColumn(3);
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
