package jpabook.jpashop.domain.item;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Book extends Item {

    private String author;
    private String isbn;

}
