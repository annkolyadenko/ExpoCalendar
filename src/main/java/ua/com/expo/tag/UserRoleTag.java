package ua.com.expo.tag;

import ua.com.expo.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.Objects;

public class UserRoleTag extends TagSupport {
    private String role;

    public void setRole(String role) {
        this.role = role;
    }

    public HttpServletRequest getRequest() {
        return (HttpServletRequest) pageContext.getRequest();
    }

    @Override
    public int doStartTag() throws JspException {
        if (Objects.isNull(getRequest().getSession().getAttribute("authorizedUser"))) {
            if (role.equals("empty")) {
                return EVAL_BODY_INCLUDE;
            }
        } else {
            if (((User) getRequest().getSession().getAttribute("authorizedUser")).getRole().toString().equalsIgnoreCase(role)) {
                return EVAL_BODY_INCLUDE;
            }
        }
        return SKIP_BODY;
    }
}
