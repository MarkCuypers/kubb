package be.markito.kubb.api.service;

import be.markito.kubb.api.persistence.TeamDao;
import be.markito.kubb.api.persistence.filter.TeamFilter;
import be.markito.kubb.api.persistence.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * TODO add some javadoc here.
 *
 * @author Mark
 * @since 28/01/14 21:41
 */
@Service("teamService")
public class TeamServiceImpl extends AbstractBaseService implements TeamService {
    @Autowired
    private TeamDao teamDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Team> find(TeamFilter filter) {
        Assert.notNull(filter, "filter should not be null");
        return teamDao.find(filter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Team findById(Long teamId) {
        assertValidId(teamId, "teamId");
        return teamDao.findById(teamId);
    }
}
