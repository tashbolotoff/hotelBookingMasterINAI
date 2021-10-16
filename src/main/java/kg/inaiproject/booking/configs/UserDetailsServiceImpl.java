package kg.inaiproject.booking.configs;

import kg.inaiproject.booking.entities.UserRole;
import kg.inaiproject.booking.entities.User;
import kg.inaiproject.booking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getUserRole()));
    }


    private List<GrantedAuthority> mapToGrantedAuthorities(UserRole role) {
        SimpleGrantedAuthority auth = new SimpleGrantedAuthority(role.getName());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(auth);
        return authorities;
    }

}