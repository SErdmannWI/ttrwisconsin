package com.serdmannwi.practiceprograms.tickettoridewisconsin.service;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model.UserRequest;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.UserRecord;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserRecord> getAllUsers() {
        return userRepository.findAll();
    }

    public UserRecord createNewUser(UserRequest userRequest) {
        return userRepository.save(new UserRecord(userRequest.getId(), userRequest.getUserName(), userRequest.getUserNumber()));
    }

    public UserRecord getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }
}
