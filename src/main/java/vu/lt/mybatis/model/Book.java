package vu.lt.mybatis.model;

public class Book {

    private Integer id;

    private String name;

    private Integer libraryId;

    //private Library library;

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

    public Integer getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Integer libraryId) {
        this.libraryId = libraryId;
    }

    /*public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }*/
}