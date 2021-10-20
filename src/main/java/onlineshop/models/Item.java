package onlineshop.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "category")
    private String category;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;


/*    private BigInteger id;
    private int quantity;
    private String category;
    private String name;
    private String description;

    public Item(BigInteger id, int quantity, String category, String name, String description) {
        this.id = id;
        this.quantity = quantity;
        this.category = category;
        this.name = name;
        this.description = description;
    }

    public Item() {

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String header) {
        this.name = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }*/
}
