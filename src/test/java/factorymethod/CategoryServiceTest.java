package factorymethod;

import entity.Category;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoryServiceTest {
    IService<Category> categoryService;

    @BeforeEach
    void setUp() {
        categoryService = ServiceFactory.getService("CategoryService");
        Category.reset();
        Category.categories.add(new Category("Teste"));
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
        Category category = new Category("Teste 2");
        int oldLength = Category.categories.size();
        categoryService.create(category);

        assertTrue(Category.categories.contains(category));
        assertEquals(oldLength + 1, Category.categories.size());
    }

    @Test
    void shouldThrowAnExceptionOnUpdate() {
        Category category = new Category("Teste 2");

        try {
            categoryService.update(category);
            fail();
        } catch (Exception e) {
            assertEquals("Could not find this category", e.getMessage());
        }
    }

    @Test
    void shouldUpdateCategory() {
        int index = 0;
        Category categoryToUpdate = Category.categories.get(index);
        String oldName = categoryToUpdate.getName();
        categoryToUpdate.setName("Teste 2");

        try {
            categoryService.update(categoryToUpdate);
            assertEquals(categoryToUpdate, Category.categories.get(index));
            assertNotEquals(oldName, Category.categories.get(index).getName());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void shouldThrowAnExceptionOnDelete() {
        Category category = new Category("Teste 2");

        try {
            categoryService.delete(category);
            fail();
        } catch (Exception e) {
            assertEquals("Could not find this category", e.getMessage());
        }
    }

    @Test
    void shouldDeleteCategory() {
        int oldLength = Category.categories.size();
        Category categoryToRemove = Category.categories.get(0);

        try {
            categoryService.delete(categoryToRemove);
            assertEquals(oldLength - 1, Category.categories.size());
        } catch (Exception e) {
            fail();
        }
    }
}
