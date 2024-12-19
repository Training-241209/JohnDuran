package com.revature.p1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
    
    @NotBlank(message = "Username is required")
    @Size(min = 4,max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9]{5,20}$", message = "Username must be alphanumeric and between 5-20 characters")
    public String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8,max = 30)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[^\\s]*$", message ="The password must include at least one uppercase letter, one lowercase letter and one digit. Not spaces allowed.")
    public String password;
}
