package com.fundamentsproyect.demo.service;

import com.fundamentsproyect.demo.entity.User;
import com.fundamentsproyect.demo.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    private final Log LOG = LogFactory.getLog(UserService.class);
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional // nos permite hacer un rootback
    public void saveTransactional(List<User> users){
        users.stream().peek(user -> LOG.info("Usuario insertado" + user)).forEach(userRepository::save);//forEach(user -> userRepository.save(user));
    }
    public List<User>  getAllUser(){
        return userRepository.findAll();
    }

    public User save(User newUser) {
        return userRepository.save(newUser);
    }

    public void delete(Long id) {
        userRepository.delete(new User(id));
    }

    public User update(User newUser, Long id)
    {
        return
         userRepository.findById(id)
                 .map(
                        user ->
                        {
                            user.setEmail(newUser.getEmail());
                            user.setBirthDate(newUser.getBirthDate());
                            user.setName(newUser.getName());
                            return userRepository.save(user);
                        }
                 ).get();

    }
}
