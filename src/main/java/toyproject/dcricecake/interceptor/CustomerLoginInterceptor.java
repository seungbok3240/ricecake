package toyproject.dcricecake.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import toyproject.dcricecake.admin.domain.seller.login.SellerSessionConst;
import toyproject.dcricecake.customer.domain.Customer;
import toyproject.dcricecake.customer.domain.login.CustomerSessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CustomerLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(CustomerSessionConst.LOGIN_MEMBER) == null) {
            response.sendRedirect("/login?redirectURL=" + requestURI);
            return false;
        }
        return true;
    }
}
