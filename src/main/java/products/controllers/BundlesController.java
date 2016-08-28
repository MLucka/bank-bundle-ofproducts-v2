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

import products.daos.BundleDAO;
import products.entities.Bundle;

/**
 * 
 * @author Mindaugas Lucka
 * 
 */
@Controller
public class BundlesController {

	@Autowired
	private BundleDAO bundleDAO;

	
	@RequestMapping(value="/bundles-json",method=RequestMethod.GET,produces="application/json")
	public ResponseEntity<List<Bundle>> getBundlesJson() {
		return new ResponseEntity<List<Bundle>>(bundleDAO.getList(), HttpStatus.OK);
	}
	
	@RequestMapping("/list-bundles")
	public String listBundles(Model model) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonOfBundles = new String();
		try {
			jsonOfBundles = mapper.writeValueAsString(bundleDAO.getList());
		} catch (JsonProcessingException e) {
			jsonOfBundles = e.getMessage();
		}
		model.addAttribute("listOfBundles", bundleDAO.getList());
		model.addAttribute("jsonOfBundles", jsonOfBundles);
		return "bundles";
	}
	
	@GetMapping("/bundle-json/{bundleId}")
	public ResponseEntity<Bundle> getBundleJson(@PathVariable("bundleId") Integer bundleId) {
		Bundle bundle = bundleDAO.getItem(bundleId);
		return new ResponseEntity<Bundle>(bundle, HttpStatus.OK);
	}
	
	
	@RequestMapping("/bundle/{bundleId}")
	public String getBundle(Model model, @PathVariable("bundleId") Integer bundleId) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonOfBundle = new String();
		try {
			jsonOfBundle = mapper.writeValueAsString(bundleDAO.getItem(bundleId));
		} catch (JsonProcessingException e) {
			jsonOfBundle = e.getMessage();
		}
		model.addAttribute("bundle", bundleDAO.getItem(bundleId));
		model.addAttribute("jsonOfBundle", jsonOfBundle);
		return "bundle";
	}
	
	
	@GetMapping(value = "/add-product-to-bundle-json")
	public ResponseEntity<Bundle> updateBundle(@RequestParam("bundleId") Integer bundleId, @RequestParam("productId") Integer productId) {
		Bundle bundle = bundleDAO.update(bundleId, productId);
		bundleDAO.setBundleProducts();
		return new ResponseEntity<Bundle>(bundle, HttpStatus.OK);
	}

	
	@GetMapping(value = "/remove-product-from-bundle")
	public ResponseEntity<Bundle> removeProductFromBundle(@RequestParam("bundleId") Integer bundleId, @RequestParam("productId") Integer productId) {
		Bundle bundle = bundleDAO.removeProduct(bundleId, productId);
		return new ResponseEntity<Bundle>(bundle, HttpStatus.OK);
	}
	

	
	
}
