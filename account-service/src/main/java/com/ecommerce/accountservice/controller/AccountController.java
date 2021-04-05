package com.ecommerce.accountservice.controller;

import com.ecommerce.accountservice.domain.ApplicationUser;
import com.ecommerce.accountservice.service.ApplicationUserService;
import com.ecommerce.accountservice.service.MapValidationErrorService;
import com.ecommerce.accountservice.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/account")
@CrossOrigin
public class AccountController {

    private final Environment env;
    private final ApplicationUserService applicationUserService;
    private final MapValidationErrorService mapValidationErrorService;
    private final UserValidator userValidator;

    @Autowired
    public AccountController(Environment env, ApplicationUserService applicationUserService,
                             MapValidationErrorService mapValidationErrorService, UserValidator userValidator) {
        this.env = env;
        this.applicationUserService = applicationUserService;
        this.mapValidationErrorService = mapValidationErrorService;
        this.userValidator = userValidator;
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello from account controller" + env.getProperty("local.server.port");
    }

    @PostMapping("/public/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody ApplicationUser user, BindingResult result){

        userValidator.validate(user,result);

        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if(result.hasFieldErrors()) return  errorMap;

        ApplicationUser newUser = applicationUserService.saveApplicationUser(user);

        return new ResponseEntity<ApplicationUser>(newUser, HttpStatus.CREATED);
    }


}
