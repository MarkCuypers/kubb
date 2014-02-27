package be.markito.kubb.api.resource.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Returns a 503 response code in case {@link IllegalArgumentException} is thrown.
 *
 * @author Mark
 * @since 28/01/14 21:00
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {
    private static Logger logger = LoggerFactory.getLogger(IllegalArgumentExceptionMapper.class);

    @Override
    public Response toResponse(IllegalArgumentException exception) {
        logger.warn("Illegal argument", exception);
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
