package section7.Config;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {

    private String createdAt;
    private String status;

}
