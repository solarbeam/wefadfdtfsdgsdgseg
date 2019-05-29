package vu.lt.persistence;

import vu.lt.entities.Owner;
import vu.lt.entities.Shop;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class OwnerDAO  {

    @PersistenceContext
    private EntityManager em;

    public List<Owner> loadAll() {
        return em.createNamedQuery("Owner.findAll", Owner.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Owner owner){
        this.em.persist(owner);
    }
}
