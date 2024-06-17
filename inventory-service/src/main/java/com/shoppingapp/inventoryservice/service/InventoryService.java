package com.shoppingapp.inventoryservice.service;

import com.shoppingapp.inventoryservice.dto.InventoryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingapp.inventoryservice.repository.InventoryRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class InventoryService {
	@Autowired
	private InventoryRepository inventoryRepository;

	@Transactional(readOnly = true)
	public List<InventoryResponse> isInStock(List<String> skuCode) {
		log.info("Checking inventory");
		return inventoryRepository.findBySkuCodeIn(skuCode).stream()
				.map(inventory ->
						InventoryResponse.builder()
								.skuCode(inventory.getSkuCode())
								.isInStock(inventory.getQuantity() > 0)
								.build()
				).toList();
	}
}
