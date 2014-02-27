package be.markito.kubb.api.resource;

import be.markito.kubb.api.mapper.TeamMapper;
import be.markito.kubb.api.persistence.filter.TeamFilter;
import be.markito.kubb.api.persistence.model.Team;
import be.markito.kubb.api.resource.model.TeamRepresentation;
import be.markito.kubb.api.resource.util.WebToServiceConverter;
import be.markito.kubb.api.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * TODO add some javadoc here.
 *
 * @author Mark
 * @since 28/01/14 21:25
 */
@Component("teamResource")
@Path("/teams")
public class TeamResource extends AbstractBaseResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(TeamResource.class);
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private TeamService teamService;

    @GET
    public Response find(@QueryParam("name") String name) {
        String teamName = WebToServiceConverter.convertIdToString(name, false);
        TeamFilter filter = new TeamFilter();
        filter.setName(teamName);
        List<Team> teams = teamService.find(filter);
        List<TeamRepresentation> result = teamMapper.mapToWeb(teams);
        LOGGER.info("Teams found");
        return Response.ok(result).build();
    }

    @GET
    @Path("/{id}")
    public Response byId(@PathParam("id") Long id) {
        Team team = null;
        team = teamService.findById(id);
        TeamRepresentation result = teamMapper.mapToWeb(team);
        if (result != null) {
            return Response.ok(result).build();
        } else {
            return handleWebWarn(Response.Status.NOT_FOUND, "No team found for ID " + id);
        }
    }

}
