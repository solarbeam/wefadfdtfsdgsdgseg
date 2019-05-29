package vu.lt.usecases;

import vu.lt.entities.Shop;
import vu.lt.persistence.ShopDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
public class Shops implements Serializable {

    @Inject
    private ShopDAO shopDAO;

    private Shop shopToCreate = new Shop();
    private List<Shop> shopList;

    @PostConstruct
    public void init(){
        this.shopList = this.shopDAO.loadAll();
    }

    @Transactional
    public String createShop(){
        this.shopDAO.persist(shopToCreate);
        return "success";
    }

    public Shop getShopToCreate() {
        return shopToCreate;
    }

    public void setShopToCreate(Shop shopToCreate) {
        this.shopToCreate = shopToCreate;
    }

    public List<Shop> getShopList() {
        return shopList;
    }
}
