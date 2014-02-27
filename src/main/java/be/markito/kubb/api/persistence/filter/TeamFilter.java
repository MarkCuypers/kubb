package be.markito.kubb.api.persistence.filter;

import java.io.Serializable;

/**
 * TODO add some javadoc here.
 *
 * @author Mark
 * @since 28/01/14 21:36
 */
public class TeamFilter implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
