package com.example.jwtexample.Services;

import com.example.jwtexample.Entities.UserEntity;
import com.example.jwtexample.Repos.UserDao;
import com.example.jwtexample.Entities.JwtRequest;
import com.example.jwtexample.Entities.JwtResponse;
import com.example.jwtexample.Security.Impl.UserDetailsImpl;
import com.example.jwtexample.Security.Utils.JwtUtil;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtService implements UserDetailsService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        System.out.println("Tried creating a JWT token");
        String loginUserName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();
        authenticate(loginUserName, userPassword);

        System.out.println("Tried username in JWTToken: "+loginUserName); //TESTING <--
        System.out.println("Tried password in JWTToken: "+userPassword);
        UserDetails userDetails = loadUserByUsername(loginUserName);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);

        UserEntity userEntity = userDao.findByuserName(loginUserName).get();
        return new JwtResponse(userEntity, newGeneratedToken);
    }
    private void authenticate(String userName, String userPassword) throws Exception {
        System.out.println("Tried to authenticate");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,userPassword));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        System.out.println("Tried find by username");
        Optional<UserEntity> user = userDao.findByuserName(userName);
        System.out.println("Called username: "+userName);

        UserEntity u = user.orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        return new UserDetailsImpl(u);
    }
}
