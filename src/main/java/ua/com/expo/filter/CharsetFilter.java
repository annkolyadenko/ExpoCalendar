package ua.com.expo.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(CharsetFilter.class);
    private static final String CHARACTER_ENCODING = "UTF-8";
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    @Override
    public void init(FilterConfig filterConfig) {
        LOGGER.info("CharsetFilter successfully initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(CHARACTER_ENCODING);
        servletResponse.setContentType(CONTENT_TYPE);
        servletResponse.setCharacterEncoding(CHARACTER_ENCODING);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        LOGGER.info("CharsetFilter successfully destroyed");
    }
}
