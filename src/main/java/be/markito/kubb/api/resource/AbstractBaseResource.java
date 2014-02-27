package be.markito.kubb.api.resource;

import be.markito.kubb.api.resource.model.WebError;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * This class marks a resource that exposes a restful webservice.
 * It sets the default language for the communication between the server and the client to JSON.
 * It can also contain helper methods that support this exposure.
 *
 * @author MArKiTo
 */
@Produces({MediaType.APPLICATION_JSON + "; charset=utf-8"})
@Consumes({MediaType.APPLICATION_JSON})
public abstract class AbstractBaseResource {
    /**
     * Helper method to make it easier to return a warning with custom message to the front-end.
     *
     * @param status  The status code to return to the front-end.
     * @param message The warning message that should be shown in the response body (as a {@link String}).
     * @return {@link javax.ws.rs.core.Response} with code equal to the given status. The response body will contain the message.
     */
    protected Response handleWebWarn(Response.Status status, String message) {
        Logger.getLogger(this.getClass()).warn(message);
        return Response.status(status).entity(new WebError(message)).build();
    }

    /**
     * Helper method to make it easier to return an error with custom message to the front-end.
     *
     * @param status  The status code to return to the front-end.
     * @param message The message that should be shown in the response body (as a {@link String}).
     * @param e       The {@link Throwable} containing the stacktrace of the error.
     * @return {@link javax.ws.rs.core.Response} with code equal to the given status. The response body will contain the message.
     */
    protected Response handleWebError(Response.Status status, String message, Throwable e) {
        if (StringUtils.isNotBlank(message)) {
            Logger.getLogger(this.getClass()).error(message, e);
            return Response.status(status).entity(new WebError(message)).build();
        } else {
            return Response.status(status).build();
        }
    }

    /**
     * Helper method to make it easier to return an error with custom message to the front-end.
     *
     * @param status  The status code to return to the front-end.
     * @param message The message that should be shown in the response body (as a {@link String}).
     * @return {@link javax.ws.rs.core.Response} with code equal to the given status. The response body will contain the message.
     */
    protected Response handleWebError(Response.Status status, String message) {
        return handleWebError(status, message, null);
    }

    /**
     * Helper method to make it easier to return an error with custom message to the front-end.
     *
     * @param status The status code to return to the front-end.
     * @return {@link javax.ws.rs.core.Response} with code equal to the given status. The response body will contain the message.
     */
    protected Response handleWebError(Response.Status status) {
        return handleWebError(status, null, null);
    }

    protected String updateValue(String originalValue, String newValue) {
        if (newValue != null) {
            return newValue;
        } else {
            return originalValue;
        }
    }

    protected Boolean updateValue(Boolean originalValue, String newValue) {
        if (newValue != null) {
            return Boolean.valueOf(newValue);
        } else {
            return originalValue;
        }
    }

    protected Integer updateValue(Integer originalValue, String newValue) {
        if (newValue != null) {
            try {
                return Integer.valueOf(newValue);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid Integer value: '" + newValue + "'", e);
            }
        } else {
            return originalValue;
        }
    }
}
