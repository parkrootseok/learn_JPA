package jpabook.jpashop.domain.item;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Album extends Item {

    private String artist;
    private String etc;

}
