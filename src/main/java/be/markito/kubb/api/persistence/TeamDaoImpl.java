package be.markito.kubb.api.persistence;

import be.markito.kubb.api.persistence.filter.TeamFilter;
import be.markito.kubb.api.persistence.model.Team;
import be.markito.kubb.api.persistence.model.Team_;
import be.markito.kubb.api.persistence.util.QueryResultHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO add some javadoc here.
 *
 * @author MArKiTo
 */
@Repository("teamDao")
public class TeamDaoImpl extends AbstractBaseDao implements TeamDao {
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Team> find(TeamFilter filter) {
        Assert.notNull(filter, "PRE: Filter should not be null");

        CriteriaBuilder cb = getEm().getCriteriaBuilder();
        CriteriaQuery<Team> query = cb.createQuery(Team.class);
        Root<Team> from = query.from(Team.class);
        query.distinct(true);

        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.isNotBlank(filter.getName())) {
            predicates.add(cb.equal(from.get(Team_.name), filter.getName()));
        }
        query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        query.orderBy(cb.asc(from.get(Team_.id)));
        TypedQuery<Team> q = getEm().createQuery(query);
        return q.getResultList();
    }
    
    /**
     * {@inheritDoc}
     */
    public Team findById(Long id) {
        assertPrimaryKey(id);

        CriteriaBuilder cb = getEm().getCriteriaBuilder();
        CriteriaQuery<Team> query = cb.createQuery(Team.class);
        Root<Team> from = query.from(Team.class);
        query.distinct(true);
        Predicate condition = cb.equal(from.get(Team_.id), id);
        query.where(condition);
        TypedQuery<Team> q = getEm().createQuery(query);
        return QueryResultHelper.handleSingleResult(q.getResultList());
    }
}
