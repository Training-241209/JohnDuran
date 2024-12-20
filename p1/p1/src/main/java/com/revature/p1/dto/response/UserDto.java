package com.revature.p1.dto.response;

import com.revature.p1.entity.User;

import lombok.Data;

@Data
public class UserDto {
    private long id;

    private String firstName;

    private String lastName;    
    
    private String username;    

    public UserDto(User user){
        id= user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        username = user.getUsername();

    }
}
