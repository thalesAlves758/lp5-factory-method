package factorymethod;

import entity.Product;

import java.util.ArrayList;

public class ProductService implements IService<Product> {
    public ArrayList<Product> getAll() {
        return Product.products;
    }

    public Product getById(int id) {
        return Product.products.stream().filter(product -> product.getId() == id).findFirst().orElse(null);
    }

    public void create(Product object) {
        object.setId(Product.products.size() + 1);
        Product.products.add(object);
    }

    public void update(Product object) throws Exception {
        Product foundProduct = this.getById(object.getId());

        if(foundProduct == null) {
            throw new Exception("Could not find this product");
        }

        int index = Product.products.indexOf(foundProduct);

        Product.products.set(index, object);
    }

    public void delete(Product object) throws Exception {
        Product foundProduct = this.getById(object.getId());

        if(foundProduct == null) {
            throw new Exception("Could not find this category");
        }

        Product.products.remove(foundProduct);
    }
}
