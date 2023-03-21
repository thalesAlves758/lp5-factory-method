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

    @Test
    void shouldCreateProduct() {
        Product product = new Product(null, "Teste 2", 10d);
        int oldLength = Product.products.size();
        productService.create(product);

        assertTrue(Product.products.contains(product));
        assertEquals(oldLength + 1, Product.products.size());
    }

    @Test
    void shouldThrowAnExceptionOnUpdate() {
        Product product = new Product(999, "Teste 2", 10d);

        try {
            productService.update(product);
            fail();
        } catch (Exception e) {
            assertEquals("Could not find this product", e.getMessage());
        }
    }

    @Test
    void shouldUpdateProduct() {
        int index = 0;
        Product productToUpdate = Product.products.get(index);
        String oldName = productToUpdate.getName();
        productToUpdate.setName("Teste 2");

        try {
            productService.update(productToUpdate);
            assertEquals(productToUpdate, Product.products.get(index));
            assertNotEquals(oldName, Product.products.get(index).getName());
        } catch (Exception e) {
            fail();
        }
    }
}
