package com.users.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class AbstractController <T>{

    public abstract ResponseEntity<T> listOneResource();

    public abstract ResponseEntity<List<T>> listAllResources();

    public abstract ResponseEntity<T> createResource();

    public abstract ResponseEntity<T> updateResource();

    //public abstract ResponseEntity<> deleteResource();
}
