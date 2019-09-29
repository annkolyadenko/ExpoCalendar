package ua.com.expo.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.dto.UserDto;
import ua.com.expo.entity.enums.RoleEnum;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorisationFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(AuthorisationFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("AuthorisationFilter successfully initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute("authorizedUser");
        if (userDto.getRole().equals(RoleEnum.ADMINISTRATOR.toString())) {
            filterChain.doFilter(request, response);
        } else {
            LOGGER.warn("Accessing the page or resource is absolutely forbidden for security reason. User login :" + userDto.getEmail());
            response.setStatus(403);
        }
    }

    @Override
    public void destroy() {
        LOGGER.info("AuthorisationFilter successfully destroyed");
    }
}
