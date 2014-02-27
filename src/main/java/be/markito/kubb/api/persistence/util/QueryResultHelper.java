package be.markito.kubb.api.persistence.util;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Helper methods to make life easier when handling JPA query results.
 *
 * @author MArKiTo
 */
public final class QueryResultHelper {
    private static final Logger LOG = LoggerFactory.getLogger(QueryResultHelper.class);

    private QueryResultHelper() {
    }

    /**
     * This method can be used to avoid exceptions when querying the database for a single record
     * and you get a different number of results than expected.
     *
     * @param resultList The {@link java.util.List} containing the result of the JPA query
     *                   Exactly 1 record is expected in the resultList
     * @return When the resultList contains exactly 1 record, that record is returned
     *         When the resultList is empty or null, null value is returned
     *         When the resultList contains more than 1 record, {@link javax.persistence.PersistenceException} is thrown
     */
    public static <T> T handleSingleResult(Collection<T> resultList) {
        if (resultList != null) {
            List<T> result = new ArrayList<>(resultList);
            if (CollectionUtils.size(result) == 1) {
                return result.get(0);
            } else if (CollectionUtils.isEmpty(resultList)) {
                return null;
            } else {
                LOG.warn("More than 1 record found");
                throw new PersistenceException("More than 1 record found for ID");
            }
        } else {
            return null;
        }
    }
}
