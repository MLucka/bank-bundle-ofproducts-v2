package products.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import products.Application;
import products.daos.ProductDAO;
import products.entities.Product;

/**
 * 
 * @author Mindaugas Lucka
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
public class ProductsControllerTests {
	
	private MockMvc mockMvc;

	private List<Product> products;
	
	
	@Autowired
	private WebApplicationContext ctx;
	
	@Autowired
	private ProductDAO productDAO;

	@Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
        this.products = productDAO.getList();
	}
	
	@Test
	public void getProduct() throws Exception {
		this.mockMvc.perform(get("/product-json/" + this.products.get(0).getId())).andDo(print())
		.andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Current Account"));
	}
	
	@Test
	public void addProduct() throws Exception {
		this.mockMvc.perform(get("/add-product/Gold Debit Card"))
		.andDo(print())
		.andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Gold Debit Card"));
	}
	
	
}
