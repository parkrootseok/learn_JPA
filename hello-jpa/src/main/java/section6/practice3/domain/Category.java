package section6.practice3.domain;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id @GeneratedValue
    @JoinColumn(name = "category_id")
    private Long id;

    private String name;

    @ManyToOne @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "item")
    private List<CategoryItem> categoryItems = new ArrayList<>();


}
