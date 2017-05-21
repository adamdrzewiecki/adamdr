package com.adamdr.catalogue.model.dao;

import com.adamdr.catalogue.model.domain.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> getAll() {
        return entityManager.createQuery("select books from Book books", Book.class).getResultList();
    }

    @Override
    public Book findByTitle(String title) {
        return entityManager.createQuery("select book from Book book where book.title = :title", Book.class).getSingleResult();
    }

    @Override
    public Book findById(Long id) {
        return null;
    }

    @Override
    public void save(Book bookToInsert) {

    }

    @Override
    public void update(Book bookToUpdate) {

    }

    @Override
    public void delete(Book bookToRemove) {

    }
}
