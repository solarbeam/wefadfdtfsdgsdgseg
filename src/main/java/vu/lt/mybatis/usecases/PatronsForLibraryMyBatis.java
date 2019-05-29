package vu.lt.mybatis.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.mybatis.model.Library;
import vu.lt.mybatis.model.Patron;
import vu.lt.mybatis.dao.PatronMapper;
import vu.lt.mybatis.dao.LibraryMapper;
import vu.lt.mybatis.dao.PatronLibraryMapper;
import vu.lt.mybatis.model.PatronLibrary;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class PatronsForLibraryMyBatis {

    @Inject
    private LibraryMapper libraryMapper;

    @Inject
    private PatronMapper patronMapper;

    @Inject
    private PatronLibraryMapper patronLibraryMapper;

    @Getter
    @Setter
    private int index;

    @Getter
    @Setter
    private Library library;

    @Getter
    @Setter
    private Patron patronToSet;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer libraryId = Integer.parseInt(requestParameters.get("libraryId"));
        this.library = libraryMapper.selectByPrimaryKey(libraryId);
    }

    @Transactional
    public String setPatron() {
            patronToSet = patronMapper.selectByPrimaryKey(index);
            PatronLibrary patronLibrary = new PatronLibrary();
            patronLibrary.setLibraryId(library.getId());
            patronLibrary.setPatronId(patronToSet.getId());
            patronLibraryMapper.insert(patronLibrary);
            return "/mybatis/books?faces-redirect=true&libraryId=" + this.library.getId();
    }
}
