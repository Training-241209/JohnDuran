package com.revature.p1.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.p1.dto.SignUpRequest;
import com.revature.p1.entity.Role;
import com.revature.p1.entity.User;
import com.revature.p1.exception.custom.InvalidUserException;
import com.revature.p1.exception.custom.UnauthorizedException;
import com.revature.p1.exception.custom.UserAlreadyExistsException;
import com.revature.p1.repository.RoleRepository;
import com.revature.p1.repository.UserRepository;

import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@AllArgsConstructor
public class UserService  implements UserDetailsService {

    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    

   

    
    
    @Transactional
    public User registerUser(SignUpRequest request)throws UserAlreadyExistsException, InvalidUserException {
        
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException(request.getUsername());
        }
        User user = request.convertToUserEntity();
        Optional<Role> roleOptional = roleRepository.findByName("USER");
        Role role;
        if(roleOptional.isPresent()){
            role= roleOptional.get();
        }else{
            Role newRole = new Role();
            newRole.setName("USER");
            role= roleRepository.save(newRole); 
        }
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);        
    }

    public String Login(String username, String password)throws UnauthorizedException{        
        if (userRepository.existsByUsernameAndPassword(username,passwordEncoder.encode(password))) {
            return "Successfully logged";
        }
        throw new UnauthorizedException("Failed login");       
    }

   
    // private void addRoleToUser(Long userId, String roleName) {
    //     Optional<User> userOptional = userRepository.findById(userId);
    //     Optional<Role> roleOptional = roleRepository.findByName(roleName);
    //     if (userOptional.isPresent() && roleOptional.isPresent()) {
    //         User user = userOptional.get();
    //         Role role = roleOptional.get();

    //         user.getRoles().add(role);
    //         userRepository.save(user);
    //     } else {
    //         throw new RuntimeException("User or Role not found");
    //     }
    // }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        User user = userRepository.findUserByUsername(usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not exists by Username or Email"));

        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                usernameOrEmail,
                user.getPassword(),
                authorities
        );
    }

}
