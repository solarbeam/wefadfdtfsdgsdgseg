package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.mybatis.dao.ItemMapper;
import vu.lt.mybatis.model.Item;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class ItemsMyBatis {
    @Inject
    private ItemMapper itemMapper;

    @Getter
    private List<Item> allItems;

    @Getter @Setter
    private Item itemToCreate = new Item();

    @PostConstruct
    public void init(){
        this.loadAllItems();
    }

    private void loadAllItems(){
        this.allItems = itemMapper.selectAll();
    }

    @Transactional
    public String createItem(){
        itemToCreate.setPrice(100);
        itemMapper.insert(itemToCreate);
        return "/mb/index?faces-redirect=true";
    }
}
