package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Library;
import vu.lt.entities.Patron;
import vu.lt.persistence.LibrariesDAO;
import vu.lt.persistence.PatronsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class PatronsForLibrary {

    @Inject
    private LibrariesDAO librariesDAO;

    @Inject
    private PatronsDAO patronsDAO;

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
        this.library = librariesDAO.findOne(libraryId);
    }

    @Transactional
    public String setPatron() {
            patronToSet = patronsDAO.findOne(index);
            patronToSet.getLibraries().add(library);
            return "books?faces-redirect=true&libraryId=" + this.library.getId();
    }
}
