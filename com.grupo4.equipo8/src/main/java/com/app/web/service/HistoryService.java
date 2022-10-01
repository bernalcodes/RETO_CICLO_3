package com.app.web.service;

import java.util.List;

import com.app.web.entity.History;

public interface HistoryService {
	public List<History> listAllEntries();

	public History getEntryById(Long id);

	public History getEntryByPlate(String plate);

	public void storeEntry(History entry);
}
