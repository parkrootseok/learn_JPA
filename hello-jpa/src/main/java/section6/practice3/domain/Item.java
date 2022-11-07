package section6.practice3.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item {

    @Id
    @GeneratedValue
    @JoinColumn(name = "item_id")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    @OneToMany(mappedBy = "category")
    private List<CategoryItem> categoryItems = new ArrayList<>();

}
