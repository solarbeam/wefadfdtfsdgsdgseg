package vu.lt.persistence;

import vu.lt.entities.Item;
import vu.lt.entities.ItemListing;
import vu.lt.entities.Shop;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.ApplicationPath;
import java.util.List;

@ApplicationScoped
public class ItemDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Item> loadAll() {
        return em.createNamedQuery("Item.findAll", Item.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Item item){
        this.em.persist(item);
    }

    public Item getByName(String name){
        Item i =  em.createQuery("select i from items where i.name = param", Item.class)
                .setParameter("param", name).getSingleResult();
        return i;
    }

    public Item getById(int id){
        return this.em.find(Item.class, id);
    }
}
