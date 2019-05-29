package vu.lt.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Owner.findAll", query = "select a from Owner as a")
})
public class Owner implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany
    @JoinTable(
            name="SHOPOWNERS",
            joinColumns=@JoinColumn(name="ownerId"),
            inverseJoinColumns=@JoinColumn(name="shopId")
    )
    private List<Shop> shop;

    public Owner() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return id == owner.id &&
                name.equals(owner.name) &&
                shop.equals(owner.shop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, shop);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Shop> getShop() {
        return shop;
    }

    public void setShop(List<Shop> shop) {
        this.shop = shop;
    }
}
