package vu.lt.usecases;

import lombok.Getter;
import vu.lt.entities.Item;
import vu.lt.entities.ItemListing;
import vu.lt.entities.Shop;
import vu.lt.persistence.ItemDAO;
import vu.lt.persistence.ItemListingDAO;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;

@Model
public class ItemListings implements Serializable {

    @Inject
    private ItemListingDAO itemListingDAO;

    @Inject
    private ShopDetails details;

    @Inject
    private ItemDAO itemDAO;

    @Getter
    private String itemToList = "";

}
