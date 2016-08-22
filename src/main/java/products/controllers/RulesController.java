package products.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import products.daos.RuleDAO;
import products.entities.Bundle;
import products.entities.Product;
import products.entities.Question;

/**
 * 
 * @author Mindaugas Lucka
 * 
 */
@RestController
public class RulesController {

	
	@Autowired
	private RuleDAO ruleDAO;
	
	
	@RequestMapping(value="/questions",method=RequestMethod.GET,produces="application/json")
	public ResponseEntity<List<Question>> getProductJson() {
		return new ResponseEntity<List<Question>>(ruleDAO.getList(), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/productsbyquestions")
	public ResponseEntity<List<Product>> retrieveProductsByQuestions(@RequestParam("age") Integer age, @RequestParam("isStudent") Boolean isStudent, @RequestParam("income") Long income) {
		List<Product> products = ruleDAO.listOfProducts(age, isStudent, income);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@GetMapping(value = "/bundlesbyquestions")
	public ResponseEntity<List<Bundle>> retrieveBundlesByQuestions(@RequestParam("age") Integer age, @RequestParam("isStudent") Boolean isStudent, @RequestParam("income") Long income) {
		List<Bundle> bundles = ruleDAO.listOfBundles(age, isStudent, income);
		return new ResponseEntity<List<Bundle>>(bundles, HttpStatus.OK);
	}
	
}
