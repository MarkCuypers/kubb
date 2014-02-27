package be.markito.kubb.api.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * An abstract service that acts as a marker class for its implementers.
 * <p/>
 * Services inheriting from the {@link AbstractBaseService} also rollback all transactions
 * in their public methods by default.
 * This enforces developers to commit DELETE, INSERT and UPDATE statements
 * explicitly by setting the @Transactional(readOnly = false) on all public methods that modify data.
 * <p/>
 * This class also offers helper methods to services inheriting from this class.
 */
@Transactional(readOnly = true)
public abstract class AbstractBaseService {

    /**
     * Performs standard validation on a persistence ID. The ID shouldn't be NULL and should be greater than 0.
     *
     * @param id   The persistence ID to check.
     * @param name The name to use in the assert message in case it fails.
     */
    protected void assertValidId(Long id, String name) {
        Assert.notNull(id, "PRE: " + name + " should not be null");
        Assert.isTrue(id > 0, "PRE: " + name + " should be greater than 0");
    }

    /**
     * Performs not null validation on the given object.
     *
     * @param o    The object to check.
     * @param name The name to use in the assert message in case it fails.
     */
    protected void assertNotNull(Object o, String name) {
        Assert.notNull(o, "PRE: " + name + " should not be null");
    }
}
