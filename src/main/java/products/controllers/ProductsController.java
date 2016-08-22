package products.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import products.daos.ProductDAO;
import products.entities.Product;

/**
 * 
 * @author Mindaugas Lucka
 * 
 */
@RestController
public class ProductsController {

	
	@Autowired
	private ProductDAO productDAO;
	
	
	@RequestMapping(value="/productList",method=RequestMethod.GET,produces="application/json")
	public ResponseEntity<String> getProducts() {
		HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity<String>( getHtmlTable(), responseHeaders, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/products",method=RequestMethod.GET,produces="application/json")
	public ResponseEntity<List<Product>> getProductJson() {
		return new ResponseEntity<List<Product>>(productDAO.getList(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/product")
	public ResponseEntity<Product> getBundle(@RequestParam(value="id") Integer id) {
		Product product = productDAO.getItem(id);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/addproduct")
	public ResponseEntity<Product> createProduct(@RequestParam("name") String name) {
		Product product = productDAO.add(name);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	
	@GetMapping(value = "/removeproduct/{id}")
	public ResponseEntity<String> removeProduct(@PathVariable("id") Integer id) {
		Product product = productDAO.remove(id);
		if (product == null) {
			return new ResponseEntity<String>("There no product found by id " + id + "", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("The product " + product.getName() + " removed from the list by id " + id + "", HttpStatus.FOUND);
	}
	
	/**
	 * 
	 * @return html table in string
	 */
	private String getHtmlTable() {
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonOfProducts = new String();
		String listOfProducts = "";
		
		try {
			jsonOfProducts += mapper.writeValueAsString(productDAO.getList());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		listOfProducts += jsonOfProducts;
		String tableOfProducts = "<table border='1'><tr><th>id</th><th>product</th></tr>";
		
		for(Product product : productDAO.getList()) {
			tableOfProducts += "<tr><td>" + product.getId() + "</td><td>" + product.getName() + "</td></tr>";
		}
		
		tableOfProducts += "</table>";
		listOfProducts += tableOfProducts;
		
		return listOfProducts;
		
	}
	
	
}
