package com.example.backend.service.crud.Interface;

import java.util.Optional;

public interface IDataAccessible<T, S> {
    T save (T entity);
    Optional<T> getById(S id);
    void delete (T entity);
    void deleteById (S id);
    boolean existsById(S id);
}
