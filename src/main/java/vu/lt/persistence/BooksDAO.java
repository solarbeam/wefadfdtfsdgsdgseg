package vu.lt.persistence;

import vu.lt.entities.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class BooksDAO {

    @Inject
    private EntityManager em;

    public void persist(Book book){
        this.em.persist(book);
    }

    public Book findOne(Integer id){
        return em.find(Book.class, id);
    }

    public Book update(Book book){
        return em.merge(book);
    }
}
