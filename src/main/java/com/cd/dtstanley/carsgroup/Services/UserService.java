package com.cd.dtstanley.carsgroup.Services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.cd.dtstanley.carsgroup.models.User;
import com.cd.dtstanley.carsgroup.repositories.UserRepository;



@Service
public class UserService {
    private final UserRepository userRepo;
    
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
    
    // register new user and hash their password
    public User registerUser(User newUser) {
    	//Generate a Hash
        String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        //Set the Hashed Password on the users password field
        newUser.setPassword(hashed);
        //Save that new user with the updated password to the database
        return userRepo.save(newUser);
    }

// find user by email
	public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
    
    // find user by id
    public User findUserById(Long id) {
    	Optional<User> u = userRepo.findById(id);
    	
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }
    
    // authenticate user
    public boolean authenticateUser(String email, String password) {
    	//make sure the user logging in is who they say they are
        // first find (query) the user by email
        User user = userRepo.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
        	// if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }// end of authenticateUser
    
    public User getByEmail(String email) {
    	return this.userRepo.findByEmail(email);
    
    }
    public User getSingleUser(Long id) {
		return this.userRepo.findById(id).orElse(null);
	}
	
}	//end of UserService

