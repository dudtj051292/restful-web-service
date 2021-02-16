package com.suh.restfulwebservice.ctrl;

import com.suh.restfulwebservice.exception.UserNotFoundException;
import com.suh.restfulwebservice.service.UserDaoService;
import com.suh.restfulwebservice.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserDaoService service;

    @GetMapping("/users")
    public ResponseEntity<List<User>> retrieveAllUsers(){
        List<User> user= service.findAll();
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){
        User user = service.findOne(id);
        if(user==null){
            throw new UserNotFoundException(String.format("ID[%s] is not found", id));
        }
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = service.save(user);
        URI location =  ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = service.deleteById(id);
        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] is not found! delete Canceled", id));
        }
    }

    @PutMapping("/users")
    public ResponseEntity<User> modifyUser(@RequestBody User user){
        User modifiedUser = service.modify(user);
        if(modifiedUser==null){
            throw new UserNotFoundException(String.format("ID[%s] is not found! delete Canceled", user.getId()));
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(modifiedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
