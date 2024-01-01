package com.ECommerce.major.Service;

import com.ECommerce.major.Model.Role;
import com.ECommerce.major.Model.User;
import com.ECommerce.major.Repository.RoleRepo;
import com.ECommerce.major.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GoogleOAuthSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;

    RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
//
//    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        OAuth2User user=(OAuth2User) authentication.getPrincipal();
        if(!userRepo.findByEmail(user.getAttribute("email").toString()).isPresent()){
            User u =new User();
            u.setEmail(user.getAttribute("email").toString());
            u.setFirstName(user.getAttribute("given_name"));
            u.setLastName(user.getAttribute("family_user"));
            List<Role>roles=new ArrayList<>();
            roles.add(roleRepo.findById(2).get());
            u.setRoles(roles);
            userRepo.save(u);

        }
        redirectStrategy.sendRedirect(httpServletRequest,httpServletResponse,"/");
    }
}
