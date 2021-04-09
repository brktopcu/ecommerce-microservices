package com.ecommerce.accountservice.controller;

import com.ecommerce.accountservice.domain.ApplicationUser;
import com.ecommerce.accountservice.request.LoginRequest;
import com.ecommerce.accountservice.response.JWTLoginSuccessResponse;
import com.ecommerce.accountservice.security.JwtTokenProvider;
import com.ecommerce.accountservice.service.ApplicationUserService;
import com.ecommerce.accountservice.service.MapValidationErrorService;
import com.ecommerce.accountservice.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.ecommerce.accountservice.security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/api/account")
@CrossOrigin
public class AccountController {

    private final Environment env;
    private final ApplicationUserService applicationUserService;
    private final MapValidationErrorService mapValidationErrorService;
    private final UserValidator userValidator;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AccountController(Environment env, ApplicationUserService applicationUserService,
                             MapValidationErrorService mapValidationErrorService, UserValidator userValidator, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.env = env;
        this.applicationUserService = applicationUserService;
        this.mapValidationErrorService = mapValidationErrorService;
        this.userValidator = userValidator;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello from account controller" + env.getProperty("local.server.port");
    }

    @PostMapping("/public/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){

        ResponseEntity <?> errorMap = mapValidationErrorService.mapValidationService(result);
        if(result.hasFieldErrors()) return errorMap;

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername()
                        ,loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);
        return new ResponseEntity<>(new JWTLoginSuccessResponse(true,jwt),HttpStatus.OK);

    }

    @PostMapping("/public/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody ApplicationUser user, BindingResult result){

        userValidator.validate(user,result);

        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if(result.hasFieldErrors()) return  errorMap;

        ApplicationUser newUser = applicationUserService.saveApplicationUser(user);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }


}
