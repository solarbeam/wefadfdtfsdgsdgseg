package vu.lt.mybatis.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.mybatis.model.Book;
import vu.lt.mybatis.model.Library;
import vu.lt.mybatis.dao.BookMapper;
import vu.lt.mybatis.dao.LibraryMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class BooksForLibraryMyBatis implements Serializable {

    @Inject
    private LibraryMapper libraryMapper;

    @Inject
    private BookMapper bookMapper;

    @Getter @Setter
    private Library library;

    @Getter @Setter
    private Book bookToCreate = new Book();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer libraryId = Integer.parseInt(requestParameters.get("libraryId"));
        this.library = libraryMapper.selectByPrimaryKey(libraryId);
    }

    @Transactional
    public String createBook() {
        bookToCreate.setLibraryId(this.library.getId());
        bookMapper.insert(bookToCreate);
        return "/mybatis/books?faces-redirect=true&libraryId=" + this.library.getId();
    }
}
