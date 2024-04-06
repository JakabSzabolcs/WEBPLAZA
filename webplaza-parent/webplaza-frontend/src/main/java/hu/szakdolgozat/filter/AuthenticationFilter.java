package hu.szakdolgozat.filter;

import hu.szakdolgozat.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter",
        urlPatterns = {"/xhtml/admin/*",
                       "/xhtml/courier/*",
                       "/xhtml/customer/*",
                       "/xhtml/shopowner/*" })
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (httpRequest.getSession().getAttribute("loggedInUser") == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/xhtml/login.xhtml");
            return;
        }

        String userType = ((User) httpRequest.getSession().getAttribute("loggedInUser")).getType().name();
        String requestURI = httpRequest.getRequestURI();

        if (("CUSTOMER".equals(userType) && requestURI.startsWith("/xhtml/customer/")) ||
                ("ADMIN".equals(userType) && requestURI.startsWith("/xhtml/admin/")) ||
                ("COURIER".equals(userType) && requestURI.startsWith("/xhtml/courier/")) ||
                ("SHOP_OWNER".equals(userType) && requestURI.startsWith("/xhtml/shopowner/"))) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/xhtml/login.xhtml");
        }
    }

}
