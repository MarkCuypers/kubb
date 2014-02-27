package be.markito.kubb.api.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Provides its implementers with an EntityManager. Also DAO helper methods could be implemented in this class.
 *
 * @author MArKiTo
 */
public abstract class AbstractBaseDao {
    /**
     * Inject the Persistence Context.
     * TRANSACTION is the default PersistenceContextType.
     * This is explicitly set to show that we want a shared EntityManager proxy from Spring.
     * A shared EntityManager proxy thread-safety.
     */
    @PersistenceContext(type = PersistenceContextType.TRANSACTION, unitName = "kubbPU")
    private EntityManager em;

    protected synchronized EntityManager getEm() {
        Assert.notNull(em, "PRE: EntityManager should not be null");
        return em;
    }

    protected void assertPrimaryKey(Long id) {
        Assert.notNull(id, "PRE: ID should not be null");
        Assert.isTrue(id > 0, "PRE: ID should be greater than 0");
    }

    /**
     * Builds a meaningful error message exposing the javax.validation error messages.
     *
     * @param validationResult The validation result which comes from the javax.validation framework.
     * @param <T>              The class on which the validator has run.
     * @return A String that contains the error messages that came back from the javax.validation framework.
     */
    protected <T> String buildValidationErrorMessage(Set<ConstraintViolation<T>> validationResult) {
        StringBuffer result = new StringBuffer();
        for (ConstraintViolation cv : validationResult) {
            result.append(cv.getPropertyPath() + ": " + cv.getMessage() + "\n");
        }
        return result.toString();
    }
}
