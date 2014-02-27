package be.markito.kubb.api.persistence.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * This abstract class is used to mark its implementers {@link java.io.Serializable} and
 * to automatically add a default toString implementation.
 *
 * @author MArKiTo
 */
@MappedSuperclass
public abstract class AbstractModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
