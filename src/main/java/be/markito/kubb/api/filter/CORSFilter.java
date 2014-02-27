package be.markito.kubb.api.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This filter is processed on every request and will enable CORS for client apps running at localhost on port 63342.
 *
 * @author MArKiTo
 */
@Component("corsFilter")
public class CORSFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String requestedHeaders = req.getHeader("Access-Control-Request-Headers");
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Credentials", "false");
        resp.addHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", StringUtils.defaultIfBlank(StringUtils.replace(requestedHeaders, "Range", "Content-Range Accept-Range"), ""));
        resp.addHeader("Access-Control-Expose-Headers", "Content-Range");
        resp.addHeader("Access-Control-Max-Age", "86400");

        if (req.getMethod().equals("OPTIONS")) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("OK");
            resp.getWriter().flush();
            resp.getWriter().close();
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
