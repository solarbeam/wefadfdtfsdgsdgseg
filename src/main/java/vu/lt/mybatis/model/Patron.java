package vu.lt.mybatis.model;

import vu.lt.mybatis.model.Library;

import java.util.ArrayList;
import java.util.List;

public class Patron {

    private Integer id;

    private String name;

    private String surname;

    private List<Library> libraries;// = new ArrayList<>();

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Library> getLibraries(){
        return libraries;
    }

    public void setLibraries(List<Library> libraries){
        this.libraries = libraries;
    }
}
