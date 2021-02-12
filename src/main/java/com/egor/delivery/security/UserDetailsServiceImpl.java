package com.egor.delivery.security;

import com.egor.delivery.model.User;
import com.egor.delivery.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    /**
     * Locates the user based on the username and checks if an authority is granted to the User
     *
     * @param username - the username identifying the user whose data is required
     * @return - new instance of AuthenticationUserDetails.
     * @throws UsernameNotFoundException - if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userService.findByUsername(username);
        final Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return new AuthenticationUserDetails(user.getId(), user.getName(), user.getPassword(), authorities);
    }
}

