package products.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import products.daos.ProductDAO;
import products.entities.Product;

/**
 * 
 * @author Mindaugas Lucka
 * 
 */
@Controller
public class ProductsController {

	
	@Autowired
	private ProductDAO productDAO;
	

	@RequestMapping(value="/products-json",method=RequestMethod.GET,produces="application/json")
	public ResponseEntity<List<Product>> getProductsJson() {
		return new ResponseEntity<List<Product>>(productDAO.getList(), HttpStatus.OK);
	}
	
	
	@RequestMapping("/list-products")
	public String listProducts(Model model) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonOfProducts = new String();
		try {
			jsonOfProducts = mapper.writeValueAsString(productDAO.getList());
		} catch (JsonProcessingException e) {
			jsonOfProducts = e.getMessage();
		}
		model.addAttribute("listOfProducts", productDAO.getList());
		model.addAttribute("jsonOfProducts", jsonOfProducts);
		return "products";
	}
	
	
	@GetMapping(value = "/product-json/{productId}")
	public ResponseEntity<Product> getProductJson(@PathVariable(value="productId") Integer productId) {
		Product product = productDAO.getItem(productId);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	
	@RequestMapping("/product/{productId}")
	public String getProduct(Model model, @PathVariable("productId") Integer productId) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonOfProduct = new String();
		try {
			jsonOfProduct = mapper.writeValueAsString(productDAO.getItem(productId));
		} catch (JsonProcessingException e) {
			jsonOfProduct = e.getMessage();
		}
		model.addAttribute("product", productDAO.getItem(productId));
		model.addAttribute("jsonOfProduct", jsonOfProduct);
		return "product";
	}
	
	
	@RequestMapping(value = "/add-product/{name}", method = RequestMethod.GET)
	public ResponseEntity<Product> createProduct(@PathVariable(value="name") String name) {
		Product product = productDAO.add(name);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/remove-product/{productId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeProduct(@PathVariable("productId") Integer productId) {
		Product product = productDAO.remove(productId);
		if (product == null) {
			return new ResponseEntity<String>("There no product found by id " + productId + "", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("The product " + product.getName() + " removed from the list by id " + productId + "", HttpStatus.FOUND);
	}
	
	@RequestMapping("/productInHtml")
    public String greeting(@RequestParam(value="productId", required=true, defaultValue="1") Integer productId, Model model) {
		Product product = productDAO.getItem(productId);
		model.addAttribute("name", product.getName());
        return "productInHtml";
    }
	

	
	
}
