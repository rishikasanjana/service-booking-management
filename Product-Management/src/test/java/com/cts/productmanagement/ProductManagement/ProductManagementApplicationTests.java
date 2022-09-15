package com.cts.productmanagement.ProductManagement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.productmanagement.dao.ProductDao;
import com.cts.productmanagement.model.AppProduct;
import com.cts.productmanagement.service.ProductService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class ProductManagementApplicationTests {
	
	@Autowired
	ProductDao productDao;
	@Autowired
	ProductService productService;
	
	@Test
	@Order(1)
	public void testAddProduct() throws ParseException{
		AppProduct p = new AppProduct();
		//p.setId(1);
		p.setName("Pencil");
		p.setCost(10);
		p.setMake("Apsara");
		p.setModel("P101");
		p.setCreatedDate(LocalDate.now());
		productDao.save(p);
		assertNotNull(productDao.findById(1).get());
	}
	
	@Test
	@Order(2)
	public void testGetProducts() {
		List<AppProduct> products = (List<AppProduct>) productDao.findAll();
		assertThat(products).size().isGreaterThan(0);		
	}
	
	@Test
	@Order(3)
	public void testGetSingleProduct() {
		AppProduct appUser = productDao.findById(1).get();
		assertEquals("Pencil", appUser.getName());
	}
	
	@Test
	@Order(4)
	public void testDeleteProduct() {
		productDao.deleteById(1);
		assertThat(productDao.existsById(1)).isFalse();
	}
	

}
