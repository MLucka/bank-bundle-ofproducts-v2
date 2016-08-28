package products.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import products.daos.RuleDAO;
import products.entities.Answer;
import products.entities.Bundle;
import products.entities.Product;
import products.entities.Question;

/**
 * 
 * @author Mindaugas Lucka
 * 
 */
@Controller
public class RulesController {

	
	@Autowired
	private RuleDAO ruleDAO;
	
	
	@RequestMapping(value="/questions",method=RequestMethod.GET,produces="application/json")
	public ResponseEntity<List<Question>> getProductJson() {
		return new ResponseEntity<List<Question>>(ruleDAO.getList(), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/products-by-questions")
	public ResponseEntity<List<Product>> retrieveProductsByQuestions(@RequestParam("age") Integer age, @RequestParam("isStudent") Boolean isStudent, @RequestParam("income") Long income) {
		Answer answer = new Answer(String.valueOf(age), String.valueOf(isStudent), String.valueOf(income), String.valueOf(true));
		List<Product> products = ruleDAO.listOfProducts(answer);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@GetMapping(value = "/bundles-by-questions")
	public ResponseEntity<List<Bundle>> retrieveBundlesByQuestions(@RequestParam("age") Integer age, @RequestParam("isStudent") Boolean isStudent, @RequestParam("income") Long income) {
		Answer answer = new Answer(String.valueOf(age), String.valueOf(isStudent), String.valueOf(income), String.valueOf(true));
		List<Bundle> bundles = ruleDAO.listOfBundles(answer);
		return new ResponseEntity<List<Bundle>>(bundles, HttpStatus.OK);
	}

	
	@RequestMapping(value="/choose-a-bundle",method=RequestMethod.GET)
	public String recommendBundle(Model model) {
		List<Bundle> choosenBundles = ruleDAO.getSelectedBundles(); 
		List<Product> availableProducts = ruleDAO.getSelectedProducts();
		Answer answer = ruleDAO.getAnswer();
		answer.setAnswered(false);
		
		model.addAttribute("choosenBundles", choosenBundles);
		model.addAttribute("availableProducts", availableProducts);
		model.addAttribute("selectedBundleId", 0);
		model.addAttribute("answer", answer);
		
		return "choosenbundle";
	}
	
	
	 @PostMapping("/answers-to-questions")
	 public String greetingSubmit(Model model, @ModelAttribute Answer answer) {
		 List<Bundle> choosenBundles = ruleDAO.getSelectedBundles(); 
		 List<Product> availableProducts = ruleDAO.getSelectedProducts();
		 
		 if(answer.getIsAllAnswersValid()) {
			choosenBundles = ruleDAO.listOfBundles(answer);
			availableProducts = ruleDAO.listOfProducts(answer);
		 }

		 model.addAttribute("choosenBundles", choosenBundles);
		 model.addAttribute("availableProducts", availableProducts);
		 model.addAttribute("selectedBundleId", 0);
		 model.addAttribute("answer", answer);
		 
		 return "choosenbundle";
	 }

	 @GetMapping(value = "/select-a-bundle/{bundleId}")
	 public String selectABundle(Model model, @PathVariable("bundleId") Integer bundleId) {
		List<Bundle> choosenBundles = ruleDAO.getSelectedBundles(); 
		List<Product> availableProducts = ruleDAO.getSelectedProducts();
		Answer answer = ruleDAO.getAnswer();
		
		model.addAttribute("choosenBundles", choosenBundles);
		model.addAttribute("availableProducts", availableProducts);
		model.addAttribute("selectedBundleId", bundleId);
		model.addAttribute("answer", answer);	
		 
		 return "choosenbundle";
	 }
	 
	@GetMapping(value = "/add-product-to-bundle/{bundleId}/{productId}")
	public String addProductToSelectedBundle(Model model, @PathVariable("bundleId") Integer bundleId, @PathVariable("productId") Integer productId) {
		List<Bundle> choosenBundles = ruleDAO.addProductToSelectedBundle(bundleId, productId);
		List<Product> availableProducts = ruleDAO.getSelectedProducts();
		Answer answer = ruleDAO.getAnswer();
		
		model.addAttribute("choosenBundles", choosenBundles);
		model.addAttribute("availableProducts", availableProducts);
		model.addAttribute("selectedBundleId", bundleId);
		model.addAttribute("answer", answer);
		
		return "choosenbundle";
	}
	
	
	@GetMapping(value = "/remove-product-from-bundle/{bundleId}/{productId}")
	public String removeProductToSelectedBundle(Model model, @PathVariable("bundleId") Integer bundleId, @PathVariable("productId") Integer productId) {
		List<Bundle> choosenBundles = ruleDAO.removeProductFromSelectedBundle(bundleId, productId);
		List<Product> availableProducts = ruleDAO.getSelectedProducts();
		Answer answer = ruleDAO.getAnswer();
		
		model.addAttribute("choosenBundles", choosenBundles);
		model.addAttribute("availableProducts", availableProducts);
		model.addAttribute("selectedBundleId", bundleId);
		model.addAttribute("answer", answer);
		
		return "choosenbundle";
	}
	
	
	
}
