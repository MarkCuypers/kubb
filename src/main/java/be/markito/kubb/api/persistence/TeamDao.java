package be.markito.kubb.api.persistence;

import be.markito.kubb.api.persistence.filter.TeamFilter;
import be.markito.kubb.api.persistence.model.Team;

import java.util.List;

/**
 * TODO add some javadoc here.
 *
 * @author Mark
 * @since 28/01/14 21:43
 */
public interface TeamDao {
    List<Team> find(TeamFilter filter);

    Team findById(Long id);
}
