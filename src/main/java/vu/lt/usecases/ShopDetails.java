package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Item;
import vu.lt.entities.ItemListing;
import vu.lt.entities.Shop;
import vu.lt.persistence.ItemDAO;
import vu.lt.persistence.ItemListingDAO;
import vu.lt.persistence.ShopDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Map;

@Model
public class ShopDetails {

    @Inject
    private ItemDAO itemDAO;

    @Inject
    private ShopDAO shopDAO;

    @Inject
    private ItemListingDAO itemListingDAO;

    @Getter @Setter
    private Shop shop;

    private int shopId;

    @Getter @Setter
    private Item itemToCreate;

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.shopId = Integer.parseInt(requestParameters.get("shopId"));
        shop = shopDAO.getById(shopId);
    }

    @Transactional
    public void listItem(Item item){
        ItemListing il = new ItemListing();
        il.setShop(shop);
        il.setItem(item);
        il.setQuantity(1);
        itemListingDAO.persist(il);
        //return "shop?faces-redirect=false&shopId=" + Integer.toString(shop.getId());
    }
}
