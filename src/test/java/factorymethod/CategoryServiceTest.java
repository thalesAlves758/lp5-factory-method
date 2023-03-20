package factorymethod;

import entity.Category;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoryServiceTest {
    IService<Category> categoryService;

    @BeforeAll
    void setUp() {
        categoryService = ServiceFactory.getService("CategoryService");
        Category.categories.add(new Category(1, "Teste"));
    }

    @Test
    void shouldReturnCategoryList() {
        ArrayList<Category> categories = categoryService.getAll();

        assertTrue(categories instanceof ArrayList<Category>);
    }

    @Test
    void shouldReturnSpecifiedCategory() {
        Category category = categoryService.getById(1);

        assertEquals(category, Category.categories.stream().findFirst().get());
    }

    @Test
    void shouldReturnNull() {
        Category category = categoryService.getById(999);

        assertEquals(null, category);
    }

    @Test
    void shouldCreateCategory() {
        Category category = new Category(null, "Teste 2");
        int oldLength = Category.categories.size();
        categoryService.create(category);

        assertTrue(Category.categories.contains(category));
        assertEquals(oldLength + 1, Category.categories.size());
    }

    @Test
    void shouldThrowAnExceptionOnUpdate() {
        Category category = new Category(999, "Teste 2");

        try {
            categoryService.update(category);
            fail();
        } catch (Exception e) {
            assertEquals("Could not find this category", e.getMessage());
        }
    }
}
