package service;

import java.util.List;

public interface Service<T> {
    List<T> getAll();

    void save(T t);

    void update(T t);

    void delete(T t);
}
