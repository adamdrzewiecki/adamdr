package com.adamdr.catalogue.model.dao;

import com.adamdr.catalogue.model.domain.Book;

import java.util.List;

public interface BookDAO {

    List<Book> getAll();

    Book findByTitle(String title);

    Book findById(Long id);

    void save(Book bookToInsert);

    void update(Book bookToUpdate);

    void delete(Book bookToRemove);
}
