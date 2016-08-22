package products.daos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.json.JsonObject;
import org.springframework.stereotype.Component;

import products.JsonHelper;
import products.entities.Product;

/**
 * 
 * @author Mindaugas Lucka
 * 
 */
@Component
public class ProductDAO {
	
	public static final String PRODUCTS_JSON_FILE = "products.json";
	private List<Product> products = new ArrayList<Product>();
	private JsonHelper jsonHelper = new JsonHelper();
	
	public ProductDAO() {
		try {
			JsonObject jsonObj = jsonHelper.retrieveJsonObject(PRODUCTS_JSON_FILE);
			products = jsonHelper.getListOfProductsFromJson(jsonObj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns list of products from JSON.
	 * 
	 * @return list of products
	 */
	public List<Product> getList() {
		return products;
	}
	
	
	/**
	 * Return product object for given id. 
	 * If product is not found for id, returns null.
	 * 
	 * @param id
	 *            product id
	 * @return product object for given id
	 */
	public Product getItem(Integer id) {
			if(products!=null && products.size()>0) {
				for (Product product : products) {
					if (product.getId()==id) {
						return product;
					}
				}
			}
		
		return null;
	}

	
	/**
	 * Create new product and insert in the list
	 * 
	 * @param product name
	 *            
	 * @return product object
	 */
	public Product add(String name) {
		Product product = new Product(getMaxProductId()+1, name);
		products.add(product);
		return product;
	}
	
	/**
	 * Remove product
	 * 
	 * @param product id
	 *            
	 * @return product object
	 */
	public Product remove(Integer id) {
		Product product = getProductById(id);
		products.remove(product);
		return product;
	}
	
	/**
	 * 
	 * @return max id value
	 */
	public Integer getMaxProductId() {
		Integer maxId = 1;
		
		for(Product p : products) {
			if(p.getId()>maxId) maxId = p.getId();
		}
		
		return maxId;
	}
	
	/**
	 * 
	 * @param id
	 * @return product
	 */
	public Product getProductById(Integer id) {
		for(Product p : products) {
			if(p.getId()==id) {
				return p;
			}
		}
		return null;
	}
	
}
