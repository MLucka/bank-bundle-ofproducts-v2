package products.daos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.json.JsonObject;

import org.springframework.stereotype.Component;

import products.JsonHelper;
import products.entities.Bundle;
import products.entities.Product;

/**
 * 
 * @author Mindaugas Lucka
 * 
 */
@Component
public class BundleDAO {

	public static final String BUNDLES_JSON_FILE = "src/main/resources/json/bundles.json";
	
	private List<Bundle> bundles = new ArrayList<Bundle>();
	private JsonHelper jsonHelper = new JsonHelper();
	private ProductDAO productDao = new ProductDAO();
	
	public BundleDAO() {
		try {
			JsonObject jsonObj = jsonHelper.retrieveJsonObject(BUNDLES_JSON_FILE);
			bundles = jsonHelper.getListOfBundlesFromJson(jsonObj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	/**
	 * Returns list of bundles from JSON.
	 * 
	 * @return list of bundles
	 */
	public List<Bundle> getList() {
		setBundleProducts();
		return bundles;
	}
	
	
	/**
	 * 
	 * @param bundleId
	 * @param productId
	 * @return bundle
	 */
	public Bundle update(Integer bundleId, Integer productId) {
		Bundle bundle = getItem(bundleId);
		if(bundle!=null && isValidToAddProduct(bundle.getProductIds(), productId)) {
			bundle.getProductIds().add(productId);
			setBundleProducts();
			return bundle;
		}
		return null;
	}
	
	
	/**
	 * 
	 * @param bundleId
	 * @param productId
	 * @return bundle
	 */
	public Bundle update(Bundle bundle, Integer productId) {
		if(bundle!=null && isValidToAddProduct(bundle.getProductIds(), productId)) {
			bundle.getProductIds().add(productId);
			setBundleProducts(bundle);
			return bundle;
		}
		return null;
	}
	
	/**
	 * 
	 * @param bundleId
	 * @param productId
	 * @return bundle
	 */
	public Bundle removeProduct(Integer bundleId, Integer productId) {
		Bundle bundle = getItem(bundleId);
		if(bundle!=null && isValidToRemoveProduct(bundle.getProductIds(), productId)) {
			bundle.getProductIds().remove(productId);
			return bundle;
		}
		return null;
	}
	
	
	/**
	 * Return bundle object for given id. 
	 * If bundle is not found for id, returns null.
	 * 
	 * @param id
	 *            bundle id
	 * @return bundle object for given id
	 */
	public Bundle getItem(Integer id) {
		
			if(bundles!=null && bundles.size()>0) {
				setBundleProducts();
				for (Bundle bundle : bundles) {
					if (bundle.getId()==id) {
						return bundle;
					}
				}
			}

		return null;
	}

	/**
	 * 
	 */
	public List<Bundle> setBundleProducts() {
		
		for(Bundle bundle : bundles) {
			List<Product> products = new ArrayList<Product>();
			for(Integer productId : bundle.getProductIds()) {
				products.add(productDao.getItem(productId));
			}
			bundle.setProducts(products);
		}
		
		return bundles;
		
	}
	
	
	/**
	 * 
	 */
	public Bundle setBundleProducts(Bundle bundle) {

		List<Product> products = new ArrayList<Product>();
		for(Integer productId : bundle.getProductIds()) {
			products.add(productDao.getItem(productId));
		}
		bundle.setProducts(products);

		
		return bundle;
		
	}
	
	
	/**
	 * 
	 * @param list product Ids in a bundle
	 * @param productId
	 * @return boolean
	 */
	private boolean isValidToAddProduct(List<Integer> bundleProIds, Integer productId) {
		Product product = productDao.getProductById(productId);
		if(product!=null) {
			for(Integer id : bundleProIds) {
				if(id==productId) return false;
			}
			return true;
		}
		
		return false;
	}

	
	/**
	 * 
	 * @param list product Ids in a bundle
	 * @param productId
	 * @return boolean
	 */
	private boolean isValidToRemoveProduct(List<Integer> bundleProIds, Integer productId) {
		Product product = productDao.getProductById(productId);
		if(product!=null) {
			for(Integer id : bundleProIds) {
				if(id==productId) return true;
			}
			return false;
		}
		
		return false;
	}
	
}
