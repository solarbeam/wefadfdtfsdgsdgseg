package vu.lt.persistence;

import vu.lt.entities.Patron;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class PatronsDAO {

    @Inject
    private EntityManager em;

    public List<Patron> loadAll() {
        return em.createNamedQuery("Patron.findAll", Patron.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Patron patron){
        this.em.persist(patron);
    }

    public Patron findOne(Integer id) {
        return em.find(Patron.class, id);
    }
}