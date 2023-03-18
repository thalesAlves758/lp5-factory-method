package factorymethod;

import java.util.ArrayList;

public interface IService <T> {
    ArrayList<T> getAll();
    T getById(int id);
    void create(T object);
    void update(T object);
    void delete(T object);
}
