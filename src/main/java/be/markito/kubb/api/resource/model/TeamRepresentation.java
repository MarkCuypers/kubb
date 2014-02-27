package be.markito.kubb.api.resource.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * TODO add some javadoc here.
 *
 * @author Mark
 * @since 28/01/14 21:19
 */
@XmlRootElement(name = "team")
public class TeamRepresentation extends AbstractPrimaryKeyRepresentation {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
