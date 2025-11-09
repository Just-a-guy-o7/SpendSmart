package com.spendSmart.spendSmart.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {



    @NotBlank(message = "Name is required")
    public String fullName;
    
    @Email(message = "Please provide a valid email address")
    @NotBlank(message = "Email is required")
    public String email;
    
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    public String password;
    
    // @AssertTrue(message = "passwords do not match")
    // public boolean isPasswordMatching() {
    //     if (password == null || confirmPassword == null) {
    //         return false;
    //     }
    //     return password.equals(confirmPassword);
    // }
    // @NotBlank(message = "Confirm Password is required")
    // @Size(min = 6, message = "Confirm Password must be at least 6 characters long")
    // public String confirmPassword;


    
}
