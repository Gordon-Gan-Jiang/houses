package com.mooc.house.web.interceptor;

import com.google.common.base.Joiner;
import com.mooc.house.common.constants.CommonConstants;
import com.mooc.house.common.models.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Map;


/**
 * @author Gordon Gan(Gordon.gan@alo7.com)
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    //controller 执行之前执行的
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Map<String, String[]> map = request.getParameterMap();
        for (Map.Entry<String, String[]> parameters : map.entrySet()) {
            if ("errorMsg".equals(parameters.getKey()) || "successMsg".equals(parameters.getKey()) || "target".equals(
                    parameters.getKey())) {
                request.setAttribute(parameters.getKey(), Joiner.on(",").join(parameters.getValue()));
            }
        }

        String reUri=request.getRequestURI();
        if (reUri.startsWith("/static")||reUri.startsWith("/error")){
            return true;
        }
        HttpSession session =request.getSession();
        User user= (User) session.getAttribute(CommonConstants.USER_ATTRIBUTE);
        if (user!=null){
            UserContext.setUser(user);
        }
        return true;
    }


    // controller 执行之后执行的
    @Override
    public void postHandle(HttpServletRequest request,HttpServletResponse httpServletResponse,Object handler,
            ModelAndView andView) throws Exception {
    }

    //页面渲染之后执行的
    @Override
    public  void  afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler, Exception ex) throws Exception{
       UserContext.remove();
    }

}
