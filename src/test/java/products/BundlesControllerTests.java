package products;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

/**
 * 
 * @author Mindaugas Lucka
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
public class BundlesControllerTests {

	@Autowired
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	
	@Test
	public void getBundle() throws Exception {
		this.mockMvc.perform(get("/bundle").param("id","1")).andDo(print())
		.andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Junior Saver"));
	}
	
	
	@Test
	public void addProductToBundle() throws Exception {
		this.mockMvc.perform(get("/addproductobundle").param("bundleId","2").param("productId", "1"))
		.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Student"));
	}
	
	
	@Test
	public void removeProductFromBundle() throws Exception {
		this.mockMvc.perform(get("/removeproductfrombunlde").param("bundleId","3").param("productId", "1"))
		.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Classic"));
	}
	
}
