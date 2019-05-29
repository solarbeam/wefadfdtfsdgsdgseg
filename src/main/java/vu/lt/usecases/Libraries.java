package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Library;
import vu.lt.persistence.LibrariesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Libraries {

    @Inject
    private LibrariesDAO librariesDAO;

    @Getter
    private List<Library> allLibraries;

    @Getter @Setter
    private Library libraryToCreate = new Library();

    @PostConstruct
    public void init(){
        loadAllLibraries();
    }

    @Transactional
    public String createLibrary(){
        this.librariesDAO.persist(libraryToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllLibraries(){
        this.allLibraries = librariesDAO.loadAll();
    }
}
