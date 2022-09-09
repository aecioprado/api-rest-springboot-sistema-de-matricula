package com.users.service;

import java.util.List;
import java.util.Optional;

public interface ServiceCrud <T>{

    public List<T> getAll();

    public Optional<T> getOneById(Long id);

    public T save(T t);

    public T update(Long id, T t);

    public boolean delete(Long id);
}
