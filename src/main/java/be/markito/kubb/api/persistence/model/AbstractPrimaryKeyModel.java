package be.markito.kubb.api.persistence.model;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * This abstract class is used to mark its implementers {@link Comparable} and
 * to automatically add a default compareTo implementation that compares on the primary key, id in this case.
 * Also equals and hashCode methods have a default implementation on id.
 *
 * @author MArKiTo
 */
@MappedSuperclass
public abstract class AbstractPrimaryKeyModel extends AbstractModel implements Comparable<AbstractPrimaryKeyModel> {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int compareTo(AbstractPrimaryKeyModel o) {
        return new CompareToBuilder().append(id, o.getId()).toComparison();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AbstractPrimaryKeyModel o = (AbstractPrimaryKeyModel) obj;
        return new EqualsBuilder().append(id, o.getId()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).toHashCode();
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "[ID=" + id + "]";
    }
}
