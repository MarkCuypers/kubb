package be.markito.kubb.api.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * TODO add some javadoc here.
 *
 * @author Mark
 * @since 28/01/14 21:10
 */
@Entity
@Table(name="TEAM")
public class Team extends AbstractPrimaryKeyModel {
    private static final long serialVersionUID = 1L;

    @Column(name = "NAME")
    @Size(max = 255)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
