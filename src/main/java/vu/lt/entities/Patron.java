package vu.lt.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Patron.findAll", query = "select t from Patron as t")
})
@Table(name = "PATRON")
@Getter
@Setter
public class Patron implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @Size(max = 50)
    @Column(name = "SURNAME")
    private String surname;


    @JoinTable(name = "PATRON_LIBRARY", joinColumns = {
            @JoinColumn(name = "PATRON_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
            @JoinColumn(name = "LIBRARY_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Library> libraries = new ArrayList<Library>();

    public Patron() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patron patron = (Patron) o;
        return Objects.equals(id, patron.id) &&
                Objects.equals(name, patron.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
