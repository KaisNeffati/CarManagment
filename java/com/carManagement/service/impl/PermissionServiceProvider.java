package com.carManagement.service.impl;

import com.carManagement.service.PermissionService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceProvider implements PermissionService {
    // inspired from http://www.baeldung.com/get-user-in-spring-security
    @Override
    public String getCurrentUserId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return null;
    }

    @Override
    public boolean checkApiAccess() {
        return getCurrentUserId() != null;
    }
}