package practice.jpashop.domain;

import section6.practice3.domain.Item;

import javax.persistence.*;

@Entity
public class CategoryItem {

    @Id
    @GeneratedValue
    @JoinColumn(name = "categoryitem_id")
    private Long id;

    @ManyToOne @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne @JoinColumn(name = "item_id")
    private Item item;

}
