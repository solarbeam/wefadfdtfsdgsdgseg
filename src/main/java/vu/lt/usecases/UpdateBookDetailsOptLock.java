package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Book;
import vu.lt.interceptors.LoggedInvocation;
import vu.lt.persistence.BooksDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Specializes;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Model
@Getter @Setter
@Specializes
public class UpdateBookDetailsOptLock extends UpdateBookDetails implements IUpdateBookDetails, Serializable {

    private Book book;

    @Inject
    private BooksDAO booksDAO;

    @PostConstruct
    private void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer bookId = Integer.parseInt(requestParameters.get("bookId"));
        System.out.println(bookId);
        this.book = booksDAO.findOne(bookId);
    }

    @Transactional
    @LoggedInvocation
    public String updateBookISBN() {
        try{
            booksDAO.update(this.book);
        } catch (OptimisticLockException e) {
            return "/bookDetails.xhtml?faces-redirect=true&bookId=" + this.book.getId() + "&error=optimistic-lock-exception";
        }
        return "books.xhtml?libraryId=" + this.book.getLibrary().getId() + "&faces-redirect=true";
    }
}
