package service;

import java.util.List;

/**
 * Interface for CRUD
 *
 * @param <T>-data
 */
public interface Service<T> {
    List<T> getAll();

    void save(T t);

    void update(T t);

    void delete(T t);
}
