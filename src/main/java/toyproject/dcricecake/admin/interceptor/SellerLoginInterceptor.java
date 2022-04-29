package toyproject.dcricecake.admin.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import toyproject.dcricecake.admin.domain.seller.login.SellerSessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SellerLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(SellerSessionConst.LOGIN_MEMBER) == null) {
            response.sendRedirect("/admin/login?redirectURL=" + requestURI);
            return false;
        }
        return true;
    }
}
