package be.markito.kubb.api.resource.model;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * This abstract class is used to mark its implementers {@link Comparable} and
 * to automatically add a default compareTo implementation that compares on the primary key, persistenceId in this case.
 * Also equals and hashCode methods have a default implementation on persistenceId.
 *
 * @author MArKiTo
 */
public abstract class AbstractPrimaryKeyRepresentation extends AbstractRepresentation implements Comparable<AbstractPrimaryKeyRepresentation> {
    private static final long serialVersionUID = 1L;
    private Long persistenceId;

    public Long getPersistenceId() {
        return persistenceId;
    }

    public void setPersistenceId(Long persistenceId) {
        this.persistenceId = persistenceId;
    }

    @Override
    public int compareTo(AbstractPrimaryKeyRepresentation o) {
        return new CompareToBuilder().append(persistenceId, o.getPersistenceId()).toComparison();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AbstractPrimaryKeyRepresentation)) {
            return false;
        }
        AbstractPrimaryKeyRepresentation o = (AbstractPrimaryKeyRepresentation) obj;
        return new EqualsBuilder().append(persistenceId, o.getPersistenceId()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(persistenceId).toHashCode();
    }
}
