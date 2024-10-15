package com.example.busreservation.controller;

import com.example.busreservation.entities.AppUsers;
import com.example.busreservation.entities.Customer;
import com.example.busreservation.models.AuthResponseModel;
import com.example.busreservation.models.ResponseModel;
import com.example.busreservation.models.SignUpRequestModel;
import com.example.busreservation.models.SignUpResponseModel;
import com.example.busreservation.security.JwtTokenProvider;
import com.example.busreservation.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Value("${app-jwt-expiration-milliseconds}")
    private Long expiration;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AppUserService appUserService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseModel> login(@RequestBody AppUsers user) {
        final AuthResponseModel authResponseModel;
        final Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        user.getUserName(), user.getPassword()
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        String role = authentication.getAuthorities().stream()
                .findFirst()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .orElse("");
        if (role.startsWith("ROLE_")) {
            role = role.substring(5);  // Removes the "ROLE_" part
        }

        authResponseModel = new AuthResponseModel(
                HttpStatus.OK.value(),
                "Sucessfully logged in",
                token,
                System.currentTimeMillis(),
                expiration,
                role
        );
        return ResponseEntity.ok(authResponseModel);
    }

    @PostMapping("/signup")
    public ResponseModel<SignUpResponseModel> signup(@RequestBody SignUpRequestModel signUpRequestModel) {
        appUserService.signupUser(signUpRequestModel);
        return new ResponseModel<>(HttpStatus.OK.value(), "Signup success", null);
    }

    @GetMapping("/{userName}")
    public ResponseEntity<Customer> getCustomer(@PathVariable(name = "userName") String userName) {
        return ResponseEntity.ok(appUserService.getCustomerInfo(userName));
    }
}

