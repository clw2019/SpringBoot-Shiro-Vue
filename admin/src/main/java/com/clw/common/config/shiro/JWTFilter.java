package com.clw.common.config.shiro;

import com.clw.common.constant.MyConstant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: clw
 * @Description: JWTFilter 实现前段请求的统一拦截
 * @Date: 2020/8/3 22:46
 */
public class JWTFilter extends BasicHttpAuthenticationFilter {

    /**
     * 认证之前执行该方法
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = SecurityUtils.getSubject();
        return subject != null && subject.isAuthenticated();
    }

    /**
     * 认证未通过执行该方法
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response)  {
        // 1. 检查请求头中是否含有token
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(MyConstant.TOKEN_KEY);
        // 2. 如果客户端没有携带token,拦截
        if (StringUtils.isEmpty(token)) {
            System.out.println("Token无效，您无权访问该接口");
        }
        // 3. 如果有,对token进行验证
        JWTToken jwtToken = new JWTToken(token);
        try {
            SecurityUtils.getSubject().login(jwtToken);
        } catch (AuthenticationException e) {
            System.out.println("Token验证失败 : " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}
