package practice1.jpashop.domain;

import javax.persistence.*;

@Entity
public class Item {

    @Id @GeneratedValue @Column(name = "ITEM_ID")
    private Long Id;
    private String name;
    private int stockQuantity;

    public Item() {}

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
