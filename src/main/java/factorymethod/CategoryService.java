package factorymethod;

import entity.Category;

import java.util.ArrayList;

public class CategoryService implements IService<Category> {
    public ArrayList<Category> getAll() {
        return Category.categories;
    }

    public Category getById(int id) {
        return Category.categories.stream().filter(category -> category.getId() == id).findFirst().orElse(null);
    }

    public void create(Category object) {
        object.setId(Category.categories.size() + 1);
        Category.categories.add(object);
    }

    public void update(Category object) throws Exception {
        Category foundCategory = this.getById(object.getId());

        if(foundCategory == null) {
            throw new Exception("Could not find this category");
        }

        int index = Category.categories.indexOf(foundCategory);

        Category.categories.set(index, object);
    }

    public void delete(Category object) throws Exception {
        Category foundCategory = this.getById(object.getId());

        if(foundCategory == null) {
            throw new Exception("Could not find this category");
        }

        Category.categories.remove(foundCategory);
    }
}
