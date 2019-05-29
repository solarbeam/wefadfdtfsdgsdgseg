package vu.lt.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Shop.findAll", query = "select a from Shop as a")
})
public class Shop implements Serializable
{
    @Size(max = 50)
    @Column(name = "NAME")
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "shop")
    private Set<ItemListing> itemList;

    @ManyToMany(mappedBy = "shop", fetch = FetchType.EAGER) @Getter @Setter
    private Set<Owner> owner;

    public Shop(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return id == shop.id &&
                name.equals(shop.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<ItemListing> getItemList() {
        return itemList;
    }

    public void setItemList(Set<ItemListing> itemList) {
        this.itemList = itemList;
    }
}
