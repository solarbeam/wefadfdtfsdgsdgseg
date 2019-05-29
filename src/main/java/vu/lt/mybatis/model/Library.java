package vu.lt.mybatis.model;

import java.util.List;

public class Library {

    private Integer id;

    private String name;

    private List<Book> books;

    private List<Patron> patrons;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks(){
        return books;
    }

    public void setBooks(List<Book> books){
        this.books = books;
    }

    public List<Patron> getPatrons(){
        return patrons;
    }

    public void setPatrons(List<Patron> patrons){
        this.patrons = patrons;
    }
}