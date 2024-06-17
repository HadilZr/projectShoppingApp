package com.shoppingapp.productservice;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingapp.productservice.dto.ProductRequest;
import com.shoppingapp.productservice.dto.ProductResponse;
import com.shoppingapp.productservice.repository.ProductRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

    /*@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ProductRepository productRepository;
	
    @DynamicPropertySource
    //provide the spring.data.mongodb.uri dynamically at the time of creating integration test
    //We're not using the local DB
	static void setProperties(DynamicPropertyRegistry registry) {
    	registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }
    
    //Integration Test
	@Test
	void shouldCreateProduct() throws Exception{
		ProductRequest productRequest = getProductRequest("Iphone 13","iphone 13", BigDecimal.valueOf(1200));
		String productRequestString = objectMapper.writeValueAsString(productRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productRequestString))
                .andExpect(status().isCreated());
        Assertions.assertEquals(1, productRepository.findAll().size());
	}

	private ProductRequest getProductRequest(String name, String description, BigDecimal price) {		
		return ProductRequest.builder()
				.name(name)
				.description(description)
				.price(price)
				.build();
	}

	@Test 
	void shouldGetAllProducts() throws Exception{
		ProductRequest productRequest1 = getProductRequest("Product 1", "Description 1", BigDecimal.valueOf(100));
	    ProductRequest productRequest2 = getProductRequest("Product 2", "Description 2", BigDecimal.valueOf(200));

	    mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(productRequest1)))
	            .andExpect(status().isCreated());

	    mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(productRequest2)))
	            .andExpect(status().isCreated());
		MvcResult products = mockMvc.perform(MockMvcRequestBuilders.get("/api/products")
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andReturn();
		
		TypeReference<List<ProductResponse>> listType = new TypeReference<List<ProductResponse>>() {};
	    List<ProductResponse> productResponseList = objectMapper.readValue(products.getResponse().getContentAsString(), listType);

        Assertions.assertEquals(2, productResponseList.size());
	}*/
}
