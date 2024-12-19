package com.revature.p1.dto;



import com.revature.p1.entity.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpRequest {

    

    @NotBlank(message = "First name is required")
    @Size(min = 2,max = 20)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only letters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2,max = 20)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only letters")
    private String lastName;
    
    @NotBlank(message = "Username is required")
    @Size(min = 4,max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9]{5,20}$", message = "Username must be alphanumeric and between 5-20 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8,max = 30)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[^\\s]*$", message ="The password must include at least one uppercase letter, one lowercase letter and one digit. Not spaces allowed.")
    private String password;

   


    public User convertToUserEntity(){
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }

}
