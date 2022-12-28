package section7.domain;

import net.bytebuddy.matcher.InstanceTypeMatcher;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
public class Movie extends Item {

    private String director;
    private String actor;

}
