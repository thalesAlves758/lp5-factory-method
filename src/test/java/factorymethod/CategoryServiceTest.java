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
        IService<Category> categoryService = ServiceFactory.getService("CategoryService");

        ArrayList<Category> categories = categoryService.getAll();

        assertTrue(categories instanceof ArrayList<Category>);
    }

    @Test
    void shouldReturnSpecifiedCategory() {
        IService<Category> categoryService = ServiceFactory.getService("CategoryService");

        Category category = categoryService.getById(1);

        assertEquals(category, Category.categories.stream().findFirst().get());
    }

    @Test
    void shouldReturnNull() {
        IService<Category> categoryService = ServiceFactory.getService("CategoryService");

        Category category = categoryService.getById(999);

        assertEquals(null, category);
    }

    @Test
    void shouldCreateCategory() {
        IService<Category> categoryService = ServiceFactory.getService("CategoryService");

        Category category = new Category(null, "Teste 2");
        int oldLength = Category.categories.size();
        categoryService.create(category);

        assertTrue(Category.categories.contains(category));
        assertEquals(oldLength + 1, Category.categories.size());
    }
}
