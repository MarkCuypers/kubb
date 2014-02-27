package be.markito.kubb.api.mapper;

import be.markito.kubb.api.persistence.model.Team;
import be.markito.kubb.api.resource.model.TeamRepresentation;
import org.springframework.stereotype.Component;

/**
 * TODO add some javadoc here.
 *
 * @author MArKiTo
 */
@Component("teamMapper")
public class TeamMapper extends AbstractDozerMapper<Team, TeamRepresentation> {
    /**
     * {@inheritDoc}
     */
    @Override
    protected TeamRepresentation doMapDbToWeb(Team dbInput) {
        return getDozerBeanMapper().map(dbInput, TeamRepresentation.class);
    }
}
