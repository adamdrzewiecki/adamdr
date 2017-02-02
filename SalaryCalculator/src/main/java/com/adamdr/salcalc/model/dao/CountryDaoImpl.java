package com.adamdr.salcalc.model.dao;

import com.adamdr.salcalc.model.domain.Country;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CountryDaoImpl implements CountryDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Country> getAll() {
        return entityManager.createQuery("select c from Country c", Country.class).getResultList();
    }

    @Override
    public Country findByName(String name) {
        return entityManager.createQuery("select c from Country c WHERE LOWER(c.name) =LOWER(:countryName)", Country.class).
                setParameter("countryName", name).
                getSingleResult();
    }

    @Override
    public Country findById(Long id) {
        return entityManager.createQuery("select c from Country c WHERE c.id = :countryId", Country.class).
                setParameter("countryId", id).
                getSingleResult();
    }

    @Override
    @Transactional
    public void save(Country countryToInsert) {
        entityManager.persist(countryToInsert);
    }

    @Override
    @Transactional
    public void update(Country countryToUpdate) {
        entityManager.merge(countryToUpdate);
    }

    @Override
    @Transactional
    public void delete(Country countryToRemove) {
        entityManager.remove(entityManager.getReference(Country.class, countryToRemove.getId()));
    }
}
