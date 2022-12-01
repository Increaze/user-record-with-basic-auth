package com.restfulapi.userrecords.controllers;

import com.restfulapi.userrecords.datas.models.User;
import com.restfulapi.userrecords.datas.repositories.UserRepository;
import com.restfulapi.userrecords.dtos.requests.CreateUserRequest;
import com.restfulapi.userrecords.dtos.requests.UpdateUserRequest;
import com.restfulapi.userrecords.exceptions.InvalidGenderException;
import com.restfulapi.userrecords.exceptions.UserNotFoundException;
import com.restfulapi.userrecords.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private  UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest createUserRequest){
        try{
            return new ResponseEntity<>(userService.createUser(createUserRequest), HttpStatus.OK);
        }catch (InvalidGenderException exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping()
    public  ResponseEntity<Map<String, Object>> getAllUsers(@RequestParam(name = "filter_field_firstname",required = false) String filterFirstName,
                                                            @RequestParam(name = "filter_field_lastname",required = false) String filterLastName,
                                                            @RequestParam(name = "filter_field_gender",required = false) String filterGender,
                                                            @RequestParam(name = "filter_field_dob",required = false) String filterDateOfBirth,
                                                            @RequestParam(name = "page",defaultValue = "0") int page,
                                                            @RequestParam(name = "page_size",defaultValue = "25") int size){
        try {
            Map<String, Object> response = userService.getAllUser(filterFirstName, filterLastName,filterGender,filterDateOfBirth,page,size);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/list")
    public  ResponseEntity<List<User>> findAllUsers(){
        try {
            List<User> users = userService.findAllUser();
            return new ResponseEntity<>(users, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{userId}")
    public  ResponseEntity<?> getUserById(@PathVariable Long userId){
        try {
            return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PutMapping("/{userId}")
    public  ResponseEntity<?> updateUserDetails(@RequestBody UpdateUserRequest updateUserRequest){
        try {
            return new ResponseEntity<>(userService.updateUser(updateUserRequest), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{userId}")
    public  int deleteUserById(@PathVariable Long userId){
        try {
            userService.deleteUserById(userId);
            return HttpStatus.OK.value();
        }catch (UserNotFoundException exception){
            return  HttpStatus.BAD_REQUEST.value();
        }

    }


}
