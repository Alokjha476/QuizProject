package com.quiz.utils;

import com.quiz.entity.CustomUserDetails;
import com.quiz.exceptions.ServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtility {

    private UserUtility() {
    }

    public static CustomUserDetails getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new ServiceException("EC-500", "No logged in user found");
        }
        return (CustomUserDetails) authentication.getPrincipal();
    }
}
