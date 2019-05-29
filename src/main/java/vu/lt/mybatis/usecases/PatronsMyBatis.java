package vu.lt.mybatis.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.mybatis.dao.PatronMapper;
import vu.lt.mybatis.model.Patron;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class PatronsMyBatis {

    @Inject
    private PatronMapper patronMapper;

    @Getter
    private List<Patron> allPatrons;

    @Getter
    @Setter
    private Patron patronToCreate = new Patron();

    @PostConstruct
    public void init(){
        loadAllPatrons();
    }

    @Transactional
    public String createPatron(){
        this.patronMapper.insert(patronToCreate);
        return "/mybatis/libraries?faces-redirect=true";
    }

    private void loadAllPatrons(){
        this.allPatrons = patronMapper.selectAll();
    }
}
