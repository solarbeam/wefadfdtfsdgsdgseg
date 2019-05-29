package vu.lt.persistence;

import lombok.Setter;
import vu.lt.entities.Item;
import vu.lt.entities.ItemListing;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class ItemListingDAO {

    @PersistenceContext @Setter
    private EntityManager em;

    public List<ItemListing> loadAll() {
        return em.createNamedQuery("ItemListing.findAll", ItemListing.class).getResultList();
    }

    public void persist(ItemListing itemListing){

        this.em.persist(itemListing);

    }
}
