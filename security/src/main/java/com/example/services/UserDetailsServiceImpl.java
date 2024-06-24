package com.example.services;


import com.example.models.Role;
import com.example.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private Map<String, User> users = new HashMap<>();

    public UserDetailsServiceImpl() {
        // Initializing with a sample user
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("admin"));
        users.put("user", new User("test1", "test1", roles));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.get(username);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
        return UserDetailsImpl.build(user);
    }
}
