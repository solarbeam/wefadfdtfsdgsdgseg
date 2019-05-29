package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Owner;
import vu.lt.persistence.OwnerDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
public class Owners implements Serializable {

    @Inject
    private OwnerDAO ownerDAO;

    @Getter
    private List<Owner> ownerList;

    @Getter @Setter
    private Owner ownerToCreate = new Owner();

    @PostConstruct
    public void init(){
        ownerList = ownerDAO.loadAll();
    }

    @Transactional
    public String createOwner(){
        ownerDAO.persist(ownerToCreate);
        return "success";
    }


}
