package evertonsavio.dev.kanbanfullstack.services;

import evertonsavio.dev.kanbanfullstack.domain.User;
import evertonsavio.dev.kanbanfullstack.exceptions.UsernameAlreadyExistsException;
import evertonsavio.dev.kanbanfullstack.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser (User newUser){

        try{
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            //Username has to be unique (exception)
            newUser.setUsername(newUser.getUsername());
            newUser.setConfirmPassword("");
            // Make sure that password and confirmPassword match
            // We don't persist or show the confirmPassword
            return userRepository.save(newUser);

        }catch (Exception e){
            throw new UsernameAlreadyExistsException("Username "+ newUser.getUsername() + " already exists");
        }


    }



}
