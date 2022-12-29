package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.category.Category;
import jpabook.jpashop.domain.item.Item;

@Entity
public class CategoryItem { // 카테고리와 아이템의 다대다 매핑을 카테고리아이템 연결 테이블을 객체로 사용하여 해결

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "item_id")
    private Item item;

}
