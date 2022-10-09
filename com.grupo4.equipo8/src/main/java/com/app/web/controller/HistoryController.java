package com.app.web.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.web.entity.History;
import com.app.web.service.HistoryServiceImpl;
import com.app.web.util.reports.HistoryExporterExcel;
import com.app.web.util.reports.HistoryExporterPDF;
import com.lowagie.text.DocumentException;

@Controller
public class HistoryController {
	@Autowired
	private HistoryServiceImpl service;

	@CrossOrigin
	@GetMapping("/history")
	public String listHistory(Model model) {
		model.addAttribute("entries", service.listAllEntries());
		model.addAttribute("total", service.calculateTotal(service.listAllEntries()));
		return "history_list";
	}

	@GetMapping("/history/exportpdf")
	public void exportHistoryPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");

		DateFormat dateFormatter = new SimpleDateFormat("yyy-MM-dd_HH:mm:ss");
		String currentDate = dateFormatter.format(new Date());

		String header = "Content-Disposition";
		String value = "attachment; filename=History_" + currentDate + ".pdf";

		response.setHeader(header, value);

		List<History> users = service.listAllEntries();

		HistoryExporterPDF exporter = new HistoryExporterPDF(users);
		exporter.exportTable(response);
	}

	@GetMapping("/history/exportxlsx")
	public void exportHistoryExcel(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/octect-stream");

		DateFormat dateFormatter = new SimpleDateFormat("yyy-MM-dd_HH:mm:ss");
		String currentDate = dateFormatter.format(new Date());

		String header = "Content-Disposition";
		String value = "attachment; filename=History_" + currentDate + ".xlsx";

		response.setHeader(header, value);

		List<History> users = service.listAllEntries();

		HistoryExporterExcel exporter = new HistoryExporterExcel(users);
		exporter.exportTable(response);
	}
}
