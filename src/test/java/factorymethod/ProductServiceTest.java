package factorymethod;

import entity.Category;
import entity.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductServiceTest {
    IService<Product> productService;

    @BeforeAll
    void setUp() {
        productService = ServiceFactory.getService("ProductService");
        Product.products.add(new Product(1, "Teste", 10d));
    }

    @Test
    void shouldReturnProductList() {
        ArrayList<Product> products = productService.getAll();

        assertTrue(products instanceof ArrayList<Product>);
    }

    @Test
    void shouldReturnSpecifiedProduct() {
        Product product = productService.getById(1);

        assertEquals(product, Product.products.stream().findFirst().get());
    }

    @Test
    void shouldReturnNull() {
        Product product = productService.getById(999);

        assertEquals(null, product);
    }
}
