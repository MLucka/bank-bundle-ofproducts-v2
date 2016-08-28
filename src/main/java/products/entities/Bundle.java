package products.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author Mindaugas Lucka
 * 
 */
public class Bundle {
	private final Integer id;
	private final String name;
	private List<Integer> productIds;
	private List<Product> products;
	private List<Rule> rules;
	private Integer priority;
	
	public Bundle(Integer id, String name) {
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

	@JsonIgnore
	public List<Product> getProducts() {
		return products;
	}
	
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	
	public List<Integer> getProductIds() {
		return productIds;
	}

	public void setProductIds(List<Integer> productIds) {
		this.productIds = productIds;
	}

	public List<Rule> getRules() {
		return rules;
	}

	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
}
