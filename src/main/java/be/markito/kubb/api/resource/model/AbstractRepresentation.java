package be.markito.kubb.api.resource.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * This abstract class is used to mark its implementers {@link java.io.Serializable} and
 * to automatically add a default toString implementation.
 *
 * @author MArKiTo
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractRepresentation implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
