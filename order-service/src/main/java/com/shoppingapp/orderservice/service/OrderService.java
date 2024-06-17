package com.shoppingapp.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.shoppingapp.orderservice.dto.InventoryResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingapp.orderservice.dto.OrderLineItemsDto;
import com.shoppingapp.orderservice.dto.OrderRequest;
import com.shoppingapp.orderservice.model.Order;
import com.shoppingapp.orderservice.model.OrderLineItems;
import com.shoppingapp.orderservice.repository.OrderRepository;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class OrderService {
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired 
	private OrderRepository orderRepository;
	@Autowired
	private WebClient.Builder webClientBuilder;

	public String placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		
		List<OrderLineItemsDto> orderLineItemsDto = orderRequest.getOrderLineItemsDtoList();
		List<OrderLineItems> orderLineItems = Arrays.asList(modelMapper.map(orderLineItemsDto, OrderLineItems[].class));

		order.setOrderLineItemsList(orderLineItems);

		List<String> skuCodes = orderLineItems.stream()
				.map(OrderLineItems::getSkuCode)
				.toList();

		//Call inventory service, and place order if the product is in stock
		InventoryResponse[] inventoryResponsesArray = webClientBuilder.build().get()
				.uri("http://inventory-service/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
				.retrieve()
				.bodyToMono(InventoryResponse[].class)
				.block();
		Boolean allProductsInStock = Arrays.stream(inventoryResponsesArray).allMatch(InventoryResponse::getIsInStock);
		if(allProductsInStock) {
			orderRepository.save(order);
			return "Order Placed successfully";
		} else {
			throw new IllegalArgumentException("Product is not in stock, please try again later");
		}
	}
}
