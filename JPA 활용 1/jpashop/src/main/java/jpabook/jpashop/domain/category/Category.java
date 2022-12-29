package jpabook.jpashop.domain.category;


import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "category")
public class Category {

    @Id @GeneratedValue
    private Long id;

    private List<Item> items = new ArrayList<>();

    @ManyToOne @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();


}
