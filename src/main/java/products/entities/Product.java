package products.entities;

import java.util.List;

/**
 * 
 * @author Mindaugas Lucka
 * 
 */
public class Product {

	private final Integer id;
	private final String name;
	private List<Rule> rules;
	
	public Product(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public List<Rule> getRules() {
		return rules;
	}

	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}

	
}
