package com.spendSmart.spendSmart.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.spendSmart.spendSmart.entities.Provider;
import com.spendSmart.spendSmart.entities.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.spendSmart.spendSmart.helpers.AppConstants;
import com.spendSmart.spendSmart.repositories.userRepo;

public class oAuthAuthenticationSuccesshandler implements AuthenticationSuccessHandler{

    @Autowired
    private userRepo userRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        DefaultOAuth2User user= (DefaultOAuth2User) authentication.getPrincipal();

        String email= null;
        String name= null;

        OAuth2AuthenticationToken authToken= (OAuth2AuthenticationToken) authentication;
        
        User userToSave= new User();
        String logedInUsing=authToken.getAuthorizedClientRegistrationId();
       

        if(logedInUsing.equals("google")){
            email= user.getAttribute("email");
            name= user.getAttribute("name");
            userToSave.setProvider(Provider.GOOGLE);
        }
        
        else if(logedInUsing.equals("github")){
            email= user.getAttribute("email");
            if(email==null){
                email= user.getAttribute("login")+"@github";
            }
            name= user.getAttribute("name");
            userToSave.setProvider(Provider.GITHUB);
        }
        else{
            response.sendRedirect("/login?error=oauth2");
            return;
        }

        userToSave.setName(name);
        userToSave.setEmail(email);
        userToSave.setPassword("password");
        userToSave.setUserId(UUID.randomUUID().toString());
        List<String> roles= List.of(AppConstants.USER);
        userToSave.setRoleList(roles);
        userToSave.setEmailVerified(true);
        userToSave.setProviderId(user.getName());
        userToSave.setEnabled(true);

        
        User existingUser= userRepo.findByEmail(email).orElse(null);
        
        if(existingUser==null){
            userRepo.save(userToSave);  
        }

        response.sendRedirect("/user/dashboard");
 }

}
