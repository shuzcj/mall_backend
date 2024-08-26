package com.mall.common.interceptor;


import com.mall.common.utils.UserContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserInfoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 获取请求头中的用户信息
        String userInfo = request.getHeader("user-info");

        // 2. 判断是否为空
        if (StringUtils.hasText(userInfo)) {
            // 不为空，保存到 ThreadLocal
            UserContext.setUserId(userInfo);
        }

        // 3. 放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 请求处理完毕，清理 ThreadLocal
        UserContext.clear();
    }
}
