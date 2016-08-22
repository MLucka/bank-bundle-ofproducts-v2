package products.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import products.daos.BundleDAO;
import products.entities.Bundle;
import products.entities.Product;

/**
 * 
 * @author Mindaugas Lucka
 * 
 */
@RestController
public class BundlesController {

	@Autowired
	private BundleDAO bundleDAO;

	
	@GetMapping("/bundleList")
	public ResponseEntity<String> getBundles() {
		HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity<String>( getHtmlTable(), responseHeaders, HttpStatus.FOUND);
	}
	
	@RequestMapping(value="/bundles",method=RequestMethod.GET,produces="application/json")
	public ResponseEntity<List<Bundle>> getBundleJson() {
		return new ResponseEntity<List<Bundle>>(bundleDAO.getList(), HttpStatus.OK);
	}
	

	@GetMapping("/bundle")
	public ResponseEntity<Bundle> getBundle(@RequestParam("id") Integer id) {
		Bundle bundle = bundleDAO.getItem(id);
		return new ResponseEntity<Bundle>(bundle, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/addproductobundle")
	public ResponseEntity<Bundle> updateBundle(@RequestParam("bundleId") Integer bundleId, @RequestParam("productId") Integer productId) {
		Bundle bundle = bundleDAO.update(bundleId, productId);
		return new ResponseEntity<Bundle>(bundle, HttpStatus.OK);
	}

	
	@GetMapping(value = "/removeproductfrombunlde")
	public ResponseEntity<Bundle> removeProductFromBundle(@RequestParam("bundleId") Integer bundleId, @RequestParam("productId") Integer productId) {
		Bundle bundle = bundleDAO.removeProduct(bundleId, productId);
		return new ResponseEntity<Bundle>(bundle, HttpStatus.OK);
	}
	
	
	/**
	 * 
	 * @return html table in string
	 */
	private String getHtmlTable() {
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonOfBundles = new String();
		String listOfBundles = "";
		
		try {
			jsonOfBundles += mapper.writeValueAsString(bundleDAO.getList());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		listOfBundles += jsonOfBundles;
		
		String tableOfBundles = "<table border='1'><tr><th>id</th><th>bundle</th><th>products included</th></tr>";
		
		for(Bundle bundle : bundleDAO.getList()) {
			tableOfBundles += "<tr>";
			tableOfBundles += "<td>" + bundle.getId() + "</td><td>" + bundle.getName() + "</td>";
			tableOfBundles += "<td>";
			int size = bundle.getProducts().size();
			int num = 0;
			if(size>0) {
			for(Product product : bundle.getProducts()) {
				tableOfBundles += product.getName();
				if(num!=size-1) {
					tableOfBundles += ", ";
				}
				num++;
			}
			}
			tableOfBundles += "</td></tr>";
		}
		
		tableOfBundles += "</table>";
		listOfBundles += tableOfBundles;
		
		return listOfBundles;
		
	}
	
}
