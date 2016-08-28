package products.daos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.json.JsonObject;

import org.springframework.stereotype.Component;

import products.JsonHelper;
import products.entities.Answer;
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
	public static final String QUESITONS_JSON_FILE = "src/main/resources/json/questions.json";
	private List<Question> questions = new ArrayList<Question>();
	private JsonHelper jsonHelper = new JsonHelper();
	private ProductDAO productDao = new ProductDAO();
	private BundleDAO bundleDao = new BundleDAO();
	private List<Product> selectedProducts = new ArrayList<Product>();
	private List<Bundle> selectedBundles = new ArrayList<Bundle>();
	private Answer answer = new Answer();
	
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

	public List<Product> listOfProducts(Answer answer) {
		List<Product> products = productDao.getList();
		selectedProducts = new ArrayList<Product>();
		this.answer = answer;
		for(Product product : products) {
			if(checkAgainstRules(product.getRules(), answer.getAgeAsInteger(), answer.getIsStudentAsBoolean(), answer.getIncomeAsLong())) {
				selectedProducts.add(product);
			}
		}
		return selectedProducts;
	}
	
	public List<Bundle> listOfBundles(Answer answer) {
		List<Bundle> bundles = bundleDao.getList();
		selectedBundles = new ArrayList<Bundle>();
		this.answer = answer;
		for(Bundle bundle : bundles) {
			if(checkAgainstRules(bundle.getRules(), answer.getAgeAsInteger(), answer.getIsStudentAsBoolean(), answer.getIncomeAsLong())) {
				if(selectedBundles.size()>0 && 
						selectedBundles.get(selectedBundles.size()-1).getPriority()<bundle.getPriority()) {
					selectedBundles.set(selectedBundles.size()-1, bundle);
				} else {
					selectedBundles.add(bundle);
				}
				
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

	public List<Product> getSelectedProducts() {
		return selectedProducts;
	}
	
	public List<Bundle> getSelectedBundles() {
		return selectedBundles;
	}
	
	public Answer getAnswer() {
		return this.answer;
	}

	public List<Bundle> addProductToSelectedBundle(Integer bundleId, Integer productId) {
		
		for(Bundle bundle : selectedBundles) {
			if(bundle.getId()==bundleId) {
				bundle = bundleDao.update(bundle, productId);
				if(bundle!=null) {
					bundle.getProductIds().add(productId);
				}
				break;
			}
		}
		
		return selectedBundles;
	}

	public List<Bundle> removeProductFromSelectedBundle(Integer bundleId, Integer productId) {
		
		for(Bundle bundle : selectedBundles) {
			if(bundle.getId()==bundleId) {
				bundle.getProductIds().remove(productId);
				bundleDao.setBundleProducts(bundle);
			}
		}

		return selectedBundles;
	}
	
}
