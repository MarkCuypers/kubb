package be.markito.kubb.api.resource.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * A class that can be used for returning an error message back to the client using JSON or XML.
 */
@XmlRootElement(name = "WebError")
public class WebError implements Serializable {
    private static final long serialVersionUID = 1L;
    private String reason;

    public WebError() {
    }

    /**
     * A constructor that fills in the member variables.
     *
     * @param reason The reason of the error.
     */
    public WebError(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
