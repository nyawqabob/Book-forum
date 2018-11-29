package by.iba.boot_learning.interceptor;

import by.iba.boot_learning.entity.user.TestUser;
import by.iba.boot_learning.service.security.v1.SecurityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SecurityInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private SecurityServiceImpl securityService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!securityService.isRightData(request)) {
            return false;
        }
        TestUser testUser = securityService.getCurrentUser(request);
        return super.preHandle(request, response, handler);
    }
}
