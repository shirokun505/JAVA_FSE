//package com.bookstoreapi.config;
//
//import com.bookstoreapi.entity.JwtRequest;
//import com.bookstoreapi.entity.JwtResponse;
//import com.bookstoreapi.security.JwtHelper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private AuthenticationManager manager;
//
//
//    @Autowired
//    private JwtHelper helper;
//
//    private Logger logger = LoggerFactory.getLogger(AuthController.class);
//
//
////    @PostMapping("/login")
////    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
////
////        this.doAuthenticate(request.getEmail(), request.getPassword());
////
////
////        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
////        String token = this.helper.generateToken(userDetails);
////
////        JwtResponse response = JwtResponse.builder()
////                .jwtToken(token)
////                .username(userDetails.getUsername()).build();
////        return new ResponseEntity<>(response, HttpStatus.OK);
////    }
////
////    private void doAuthenticate(String email, String password) {
////
////        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
////        try {
////            manager.authenticate(authentication);
////
////
////        } catch (BadCredentialsException e) {
////            throw new BadCredentialsException(" Invalid Username or Password  !!");
////        }
////
////    }
//
////    @PostMapping("/login")
////    public ResponseEntity<?> login(@RequestBody JwtRequest request) {
////        try {
////            this.doAuthenticate(request.getEmail(), request.getPassword());
////            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
////            String token = this.helper.generateToken(userDetails);
////
////            JwtResponse response = JwtResponse.builder()
////                    .jwtToken(token)
////                    .username(userDetails.getUsername())
////                    .build();
////
////            logger.info("User {} successfully logged in", userDetails.getUsername());
////            return new ResponseEntity<>(response, HttpStatus.OK);
////
////        } catch (BadCredentialsException e) {
////            logger.error("Invalid credentials for user {}", request.getEmail());
////            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
////        } catch (Exception e) {
////            logger.error("Error during login for user {}: {}", request.getEmail(), e.getMessage());
////            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
////        }
////    }
////
////
////    @ExceptionHandler(BadCredentialsException.class)
////    public String exceptionHandler() {
////        return "Credentials Invalid !!";
////    }
//
//
//
//}
//

package com.bookstoreapi.config;

import com.bookstoreapi.entity.JwtRequest;
import com.bookstoreapi.entity.JwtResponse;
import com.bookstoreapi.security.JwtHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        try {
            doAuthenticate(request.getEmail(), request.getPassword());
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            logger.error("Invalid username or password", e);
            throw e; // Rethrow to be caught by the login method's catch block
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException ex) {
        return new ResponseEntity<>("Invalid Username or Password!", HttpStatus.UNAUTHORIZED);
    }
}

