package products;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import products.entities.Bundle;
import products.entities.Product;
import products.entities.Question;
import products.entities.Rule;

/**
 * 
 * @author Mindaugas Lucka
 * 
 */
public class JsonHelper {

	
	public JsonHelper() {
		
	}
	
	public JsonObject retrieveJsonObject(String jsonFile) throws IOException  {
		InputStream fis = new FileInputStream(jsonFile);
        
	      //create JsonReader object
	      JsonReader jsonReader = Json.createReader(fis);
	      //get JsonObject from JsonReader
	      JsonObject jsonObject = jsonReader.readObject();  
	    
	    jsonReader.close();
	    fis.close();
	    
	    return jsonObject;
	}
	
	
	/**
	 * retrieves the list of products
	 * 
	 * @return list of product objects
	 */
	public List<Product> getListOfProductsFromJson(JsonObject jsonObject) throws IOException {
		List<Product> products = new ArrayList<Product>();
		
		if(jsonObject!=null) {
			JsonArray productsJsonArray = jsonObject.getJsonArray("products"); 
			if(productsJsonArray!=null) {
				for (JsonObject productJsonObj : productsJsonArray.getValuesAs(JsonObject.class)) {
					  Integer id = Integer.parseInt(productJsonObj.getString("id"));
					  String name = productJsonObj.getString("name");
					  Product product = new Product(id, name);
					  List<Rule> rules = getListOfRulesFromJson(productJsonObj.getJsonArray("rules"));
					  product.setRules(rules);
					  products.add(product);
				  }
			}
		}
		
		return products;
	}
	
	
	/**
	 * retrieves the list of bundles
	 * 
	 * @return list of bundle objects
	 */
	public List<Bundle> getListOfBundlesFromJson(JsonObject jsonObject) throws IOException {
		List<Bundle> bundles = new ArrayList<Bundle>();
		
		if(jsonObject!=null) {
			JsonArray bundlesJsonArray = jsonObject.getJsonArray("bundles"); 
			if(bundlesJsonArray!=null) {
				for (JsonObject bundleJsonObj : bundlesJsonArray.getValuesAs(JsonObject.class)) {
					  Integer bundleId = Integer.parseInt(bundleJsonObj.getString("id"));
					  String name = bundleJsonObj.getString("name");
					  List<Integer> productIds = getListOfIdsFromJson(bundleJsonObj.getJsonArray("productIds"));
					  List<Rule> rules = getListOfRulesFromJson(bundleJsonObj.getJsonArray("rules"));
					  Bundle bundle = new Bundle(bundleId, name);
					  bundle.setProductIds(productIds);
					  bundle.setRules(rules);
					  bundles.add(bundle);
				  }
			}
		}
		
		return bundles;
	}
	
	
	
	/**
	 * retrieves the list of ids
	 * 
	 * @return list of ids
	 */
	private List<Integer> getListOfIdsFromJson(JsonArray idsJsonArray) {
		List<Integer> ids = new ArrayList<Integer>();
		
		if(idsJsonArray!=null) {
			  for (JsonObject jsonObj : idsJsonArray.getValuesAs(JsonObject.class)) {
				  Integer id = Integer.parseInt(jsonObj.getString("id"));
				  ids.add(id);
			  }
		  }
		
		return ids;
	}
	
	/**
	 * retrieves the list of rules
	 * 
	 * @return list of rules
	 */
	private List<Rule> getListOfRulesFromJson(JsonArray rulesJsonArray) {
		List<Rule> rules = new ArrayList<Rule>();
		
		if(rulesJsonArray!=null) {
			  for (JsonObject jsonObj : rulesJsonArray.getValuesAs(JsonObject.class)) {
				  String type = jsonObj.getString("type");
				  String min = jsonObj.getString("min");
				  String max = jsonObj.getString("max");
				  Rule rule = new Rule(type, min, max);
				  rules.add(rule);
			  }
		  }
		
		return rules;
	}
	
	
	
	/**
	 * retrieves the list of questions
	 * 
	 * @return list of question objects
	 */
	public List<Question> getListOfQuestionsFromJson(JsonObject jsonObject) throws IOException {
		List<Question> questions = new ArrayList<Question>();
		
		if(jsonObject!=null) {
			JsonArray questionsJsonArray = jsonObject.getJsonArray("questions"); 
			if(questionsJsonArray!=null) {
				for (JsonObject result : questionsJsonArray.getValuesAs(JsonObject.class)) {
					  String type = result.getString("type");
					  List<List<String>> answers = getListOfAnswersFromJson(result.getJsonArray("answers"));
					  Question question = new Question(type);
					  question.setAnswers(answers);
					  questions.add(question);
				  }
			}
		}
		
		return questions;
	}
	
	
	/**
	 * retrieves the list of answers
	 * 
	 * @return list of answers
	 */
	private List<List<String>> getListOfAnswersFromJson(JsonArray idsJsonArray) {
		List<List<String>> answers = new ArrayList<List<String>>();
		
		if(idsJsonArray!=null) {
			  for (JsonObject jsonAnsObj : idsJsonArray.getValuesAs(JsonObject.class)) {
					  String min = jsonAnsObj.getString("min");
					  String max = jsonAnsObj.getString("max");
					  List<String> answer = new ArrayList<String>(); 
					  answer.add(min);
					  answer.add(max);
					  answers.add(answer);
			  }
		  }
		
		return answers;
	}
	
}
