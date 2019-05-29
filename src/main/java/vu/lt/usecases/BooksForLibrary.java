package vu.lt.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Book;
import vu.lt.entities.Library;
import vu.lt.persistence.BooksDAO;
import vu.lt.persistence.LibrariesDAO;
import vu.lt.interceptors.LoggedInvocation;

@Model
public class BooksForLibrary implements Serializable {

    @Inject
    private LibrariesDAO librariesDAO;

    @Inject
    private BooksDAO booksDAO;

    @Getter @Setter
    private Library library;

    @Getter @Setter
    private Book bookToCreate = new Book();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer libraryId = Integer.parseInt(requestParameters.get("libraryId"));
        this.library = librariesDAO.findOne(libraryId);
    }

    @LoggedInvocation
    @Transactional
    public String createBook() {
        bookToCreate.setLibrary(this.library);
        booksDAO.persist(bookToCreate);
        return "books?faces-redirect=true&libraryId=" + this.library.getId();
    }
}
