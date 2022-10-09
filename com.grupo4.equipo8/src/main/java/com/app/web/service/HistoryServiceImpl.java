package com.app.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.web.entity.History;
import com.app.web.repository.HistoryRepository;

@Service
public class HistoryServiceImpl implements HistoryService {
	@Autowired
	private HistoryRepository repository;

	@Override
	public List<History> listAllEntries() {
		return repository.findAll();
	}

	@Override
	public History getEntryById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public History getEntryByPlate(String plate) {
		return repository.findByPlate(plate);
	}

	@Override
	public void storeEntry(History entry) {
		repository.save(entry);
	}

	@Override
	public Double calculateTotal(List<History> history) {
		Double paymentTotal = 0d;
		if (!history.isEmpty()) {
			for (History registry : history) {
				paymentTotal += registry.getPayment();
			}
		}
		return paymentTotal;
	}
}
