package products.daos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.json.JsonObject;

import org.springframework.stereotype.Component;

import products.JsonHelper;
import products.entities.Bundle;
import products.entities.Product;
import products.entities.Question;
import products.entities.Rule;

/**
 * 
 * @author Mindaugas Lucka
 * 
 */
@Component
public class RuleDAO {
	public static final String QUESITONS_JSON_FILE = "questions.json";
	private List<Question> questions = new ArrayList<Question>();
	private JsonHelper jsonHelper = new JsonHelper();
	private ProductDAO productDao = new ProductDAO();
	private BundleDAO bundleDao = new BundleDAO();
	
	public RuleDAO() {
		try {
			JsonObject jsonObj = jsonHelper.retrieveJsonObject(QUESITONS_JSON_FILE);
			questions = jsonHelper.getListOfQuestionsFromJson(jsonObj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns list of questions from JSON.
	 * 
	 * @return list of questions
	 */
	public List<Question> getList() {
		return questions;
	}

	public List<Product> listOfProducts(Integer age, Boolean isStudent, Long income) {
		List<Product> products = productDao.getList();
		List<Product> selectedProducts = new ArrayList<Product>();
		for(Product product : products) {
			if(checkAgainstRules(product.getRules(), age, isStudent, income)) {
				selectedProducts.add(product);
			}
		}
		return selectedProducts;
	}
	
	public List<Bundle> listOfBundles(Integer age, Boolean isStudent, Long income) {
		List<Bundle> bundles = bundleDao.getList();
		List<Bundle> selectedBundles = new ArrayList<Bundle>();
		for(Bundle bundle : bundles) {
			if(checkAgainstRules(bundle.getRules(), age, isStudent, income)) {
				selectedBundles.add(bundle);
			}
		}
		return selectedBundles;
	}
	
	private boolean checkAgainstRules(List<Rule> rules, Integer age, Boolean isStudent, Long income) {
		for(Rule rule : rules) {
			String min = rule.getMin();
			String max = rule.getMax();
			switch(rule.getType()) {
				case AGE:
					Integer minAge = (min!=null && min.length()>0) ? Integer.valueOf(min) : null; 
					Integer maxAge = (max!=null && max.length()>0) ? Integer.valueOf(max) : null;
					if((minAge!=null && age!=null && minAge>age) || 
							(maxAge!=null && age!=null && maxAge<age)) 
						return false;
				break;
				case INCOME:
					Long minIncome = (min!=null && min.length()>0) ? Long.valueOf(min) : null;
					Long maxIncome = (max!=null && max.length()>0) ? Long.valueOf(max) : null;
					if((minIncome!=null && income!=null && minIncome>income) || 
							(maxIncome!=null && income!=null && maxIncome<income)) 
						return false;
				break;
				case STUDENT:
					Boolean isFalse = (min!=null && min.length()>0) ? false : null;
					Boolean isTrue = (max!=null && max.length()>0) ? true : null;
					if((isStudent!=null && isFalse!=null && isFalse!=isStudent) || 
							(isStudent!=null && isTrue!=null && isTrue!=isStudent))
						return false;
				break;	
			default:
				break;
					
			}
		}
		
		return true;
	}


	
}
