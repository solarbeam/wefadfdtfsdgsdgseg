package vu.lt.usecases;


import vu.lt.entities.Item;
import vu.lt.persistence.ItemDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
public class Items implements Serializable {

    @Inject
    private ItemDAO itemDAO;

    private Item itemToCreate = new Item();
    private List<Item> allItems;

    @PostConstruct
    public void init(){
        this.allItems = this.itemDAO.loadAll();
    }

    public List<Item> getAllItems(){
        return allItems;
    }

    @Transactional
    public String createItem(){
        this.itemDAO.persist(itemToCreate);
        return "index?faces-redirect=true";
    }

    public Item getItemToCreate() {
        return itemToCreate;
    }

    public void setItemToCreate(Item itemToCreate) {
        this.itemToCreate = itemToCreate;
    }

}
