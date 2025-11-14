package spendSmart.spendSmart.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;


public class Helper {

    @Autowired

    public String loggedInUser(Authentication authentication){
        String email="";
        if(authentication instanceof OAuth2AuthenticationToken){
            var oauth2user=(OAuth2User) authentication.getPrincipal();
            email=oauth2user.getAttribute("email").toString();
        }
        else{
            email=authentication.getName().toString();
        }

        
        return email;
    }
}
