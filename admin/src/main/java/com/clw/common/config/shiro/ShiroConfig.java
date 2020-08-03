package com.clw.common.config.shiro;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: clw
 * @Description: Shiro 配置类
 * @Date: 2020/8/3 21:43
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 添加自己的过滤器并且取名为jwt
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new JWTFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        // 自定义URL规则
        Map<String, String> filterRuleMap = new HashMap<>();
        // 所有请求通过我们自己的JWT Filter
        filterRuleMap.put("/**", "jwt");
        filterRuleMap.put("/user/login", "anon");
        // 开放API接口文档
        filterRuleMap.put("/swagger-ui.html", "anon");
        filterRuleMap.put("/webjars/**","anon");
        filterRuleMap.put("/swagger-resources/**","anon");
        filterRuleMap.put("/v2/**","anon");
        return shiroFilterFactoryBean;
    }

    /**
     * 自定义的认证类，继承自AuthorizingRealm
     * 负责用户的认和权限处理
     */
    @Bean
    public UserRealm realm() {
        UserRealm userRealm = new UserRealm();
        // 注入加密

        return userRealm;
    }

    /**
     * 权限管理，这个类组合了登陆，登出，权限，session的处理，是个比较重要的类
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 使用自定义的realm
        securityManager.setRealm(realm());
        // 关闭自带session，因为在前后端分离项目中前端是无法获取后端的Session的，即无法实现用户登录状态的登录
        DefaultSessionStorageEvaluator evaluator = new DefaultSessionStorageEvaluator();
        evaluator.setSessionStorageEnabled(false);
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        subjectDAO.setSessionStorageEvaluator(evaluator);
        return securityManager;
    }

    /***
     * 开启Shiro的注解（如@RequiresRoles, @RequiresPermissions），需要借助SpringAOP扫描Shiro注解类，并在必要时进行安全的逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * DefaultAdvisorAutoProxyCreator,Spring的一个bean,由Advisor决定对哪些类的方法进行AOP代理
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * LifecycleBeanPostProcessor 是 DestructionAwareBeanPostProcessor的子类
     * 负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁
     * 主要是AuthorizingRealm类的子类，以及EhCacheManager类
     * ps: 在spring-shiro中的ShiroBeanConfiguration已经配置过了，无需再次配置
     */
    // @Bean
    // public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
    //     return new LifecycleBeanPostProcessor();
    // }

    /**
     * AuthorizationAttributeSourceAdvisor, shiro里实现的Advisor类
     * 内部使用AopAllianceAnnotationsAuthorizingMethodInterceptor来拦截用以下注解的方法。
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }
}
