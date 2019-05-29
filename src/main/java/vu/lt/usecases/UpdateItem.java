package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Item;
import vu.lt.persistence.ItemDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
public class UpdateItem implements Serializable {

    @Getter @Setter
    private Item item;

    @Inject
    private ItemDAO itemDAO;

    @PostConstruct
    private void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int itemId = Integer.parseInt(requestParameters.get("itemId"));
        this.item = itemDAO.getById(itemId);
    }

    @Transactional
    public String update(){

        return "/index.xhtml?faces-redirect=true";
    }

}
