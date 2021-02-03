package com.cd.dtstanley.carsgroup.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cd.dtstanley.carsgroup.models.User;
import com.cd.dtstanley.carsgroup.repositories.UserRepository;



@Component	//a generic stereotype for any Spring-managed component.- here we are creating custom code
public class UserValidator implements Validator{
	
	@Autowired
	public UserRepository userRep;
	
	  // Specifies that a instance of the User Domain Model can be validated with this custom validator
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
    
    // Creating our custom validation. We can add errors.
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        
        //Verify that password and confirm password match
        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            // Custom error msgs: the first argument is the member variable of our Domain model that we are validating. 
        	//The second argument is a code for us to use to set an error message.
            errors.rejectValue("passwordConfirmation", "Match", "Passwords do not match!!!!!!!");
        }   
        
        //Verify that e-mail is unique in the database
        if(this.userRep.existsByEmail(user.getEmail())) {
        	errors.rejectValue("email", "unique", "My Apologies. This e-mail is already being used!!!!!!!!!");
        }
        
//        if(user.getFirstName().equals("Matt")) {
//        	errors.rejectValue("firstName", "noMattsAllowed", "My apologies, we're not accepting any Matt's at this time!!!");
//        }
    }
}//end UserVAlidator
