package factorymethod;

import entity.Category;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoryServiceTest {
    @BeforeAll
    void setUp() {
        Category.categories.add(new Category(1, "Teste"));
    }

    @Test
    void shouldReturnCategoryList() {
        IService categoryService = ServiceFactory.getService("CategoryService");

        ArrayList<Category> categories = categoryService.getAll();

        assertTrue(categories instanceof ArrayList);
        assertEquals(categories.stream().findFirst().get(), Category.categories.stream().findFirst().get());
    }

    @Test
    void shouldReturnSpecifiedCategory() {
        IService categoryService = ServiceFactory.getService("CategoryService");

        Category category = (Category) categoryService.getById(1);

        assertEquals(category, Category.categories.stream().findFirst().get());
    }
}
