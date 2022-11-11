package section7.domain;

import net.bytebuddy.matcher.InstanceTypeMatcher;

import javax.persistence.Entity;

@Entity
public class Movie extends Item {

    private String director;
    private String actor;

}
