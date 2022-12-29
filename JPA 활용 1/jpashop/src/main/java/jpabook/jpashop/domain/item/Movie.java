package jpabook.jpashop.domain.item;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Movie extends Item {

    private String director;
    private String etc;

}
