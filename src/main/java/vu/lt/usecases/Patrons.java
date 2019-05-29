package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Patron;
import vu.lt.persistence.PatronsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Patrons {

    @Inject
    private PatronsDAO patronsDAO;

    @Getter
    @Setter
    private Patron patronToCreate = new Patron();

    @Getter
    private List<Patron> allPatrons;

    @PostConstruct
    public void init(){
        loadAllPatrons();
    }

    @Transactional
    public String createPatron(){
        this.patronsDAO.persist(patronToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllPatrons(){
        this.allPatrons = patronsDAO.loadAll();
    }
}
