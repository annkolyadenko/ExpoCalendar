package ua.com.expo.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.util.resource.ConfigurationManager;

public class AuthenticationFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(AuthenticationFilter.class.getName());
    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) {
        LOGGER.info("AuthenticationFilter successfully initialized");
        context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        if (Objects.isNull(session) || Objects.isNull(session.getAttribute("authorizedUser"))) {
            response.sendRedirect(request.getContextPath() + ConfigurationManager.PATH_MANAGER.getProperty("path.page.index"));
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        LOGGER.info("AuthenticationFilter successfully destroyed");
    }
}
