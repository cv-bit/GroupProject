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

    public User registerUser(User newUser) {
        String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashed);
        return userRepo.save(newUser);
    }

	public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public User findUserById(Long id) {
    	Optional<User> u = userRepo.findById(id);
    	
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }

    public boolean authenticateUser(String email, String password) {
        User user = userRepo.findByEmail(email);
        if(user == null) {
            return false;
        } else {
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    public User getByEmail(String email) {
    	return this.userRepo.findByEmail(email);
    
    }
    public User getSingleUser(Long id) {
		return this.userRepo.findById(id).orElse(null);
	}
	
}

