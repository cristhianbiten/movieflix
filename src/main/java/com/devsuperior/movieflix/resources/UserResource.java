package com.devsuperior.movieflix.resources;

import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping(value = "/profile")
    public ResponseEntity<UserDTO> currentUser() {
        UserDTO dto = service.currentUser();
        return ResponseEntity.ok().body(dto);
    }
}
