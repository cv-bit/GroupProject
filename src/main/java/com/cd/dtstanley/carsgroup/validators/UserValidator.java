package com.cd.dtstanley.carsgroup.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cd.dtstanley.carsgroup.models.User;
import com.cd.dtstanley.carsgroup.repositories.UserRepository;



@Component	
public class UserValidator implements Validator{
	
	@Autowired
	public UserRepository userRep;
	
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
    
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirmation", "Match", "Passwords do not match!!!!!!!");
        }   
        
        if(this.userRep.existsByEmail(user.getEmail())) {
        	errors.rejectValue("email", "unique", "My Apologies. This e-mail is already being used!!!!!!!!!");
        }
        
    }
}
