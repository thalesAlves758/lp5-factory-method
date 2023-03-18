package factorymethod;

import java.util.ArrayList;

public interface IService <T> {
    ArrayList<T> getAll();
    T getById(int id);
    void create(T object);
    void update(T object) throws Exception;
    void delete(T object) throws Exception;
}
