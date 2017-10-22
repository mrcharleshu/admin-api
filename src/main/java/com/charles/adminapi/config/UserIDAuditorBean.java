package com.charles.adminapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class UserIDAuditorBean implements AuditorAware<String> {
    @Override
    public String getCurrentAuditor() {
        System.out.println("UserIDAuditorBean");
        SecurityContext ctx = SecurityContextHolder.getContext();
        if (ctx == null) {
            return null;
        }
        if (ctx.getAuthentication() == null) {
            return "CharlesDefault";
        }
        if (ctx.getAuthentication().getPrincipal() == null) {
            return null;
        }
        Object principal = ctx.getAuthentication().getPrincipal();
        if (principal.getClass().isAssignableFrom(Long.class)) {
            return (String) principal;
        } else {
            return null;
        }
    }
}