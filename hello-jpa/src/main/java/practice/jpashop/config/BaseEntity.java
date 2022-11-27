package practice.jpashop.config;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {

    private String createdAt;
    private String status;

}
