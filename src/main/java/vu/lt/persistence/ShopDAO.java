package vu.lt.persistence;

import vu.lt.entities.Item;
import vu.lt.entities.ItemListing;
import vu.lt.entities.Shop;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class ShopDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Shop> loadAll() {
        return em.createNamedQuery("Shop.findAll", Shop.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Shop shop){
        this.em.persist(shop);
    }

    public Shop getById(int id){
        return em.find(Shop.class, id);
    }

}
