package org.api.apimockapi.controller;

import lombok.RequiredArgsConstructor;
import org.api.apimockapi.dto.UserDTO;
import org.api.apimockapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor //Esto quiere decir que todos los tipo final se incializen,
public class UserController {

    //Normalmente se usa autowired pero se usa final cuando se invoca RequiredArgsConstructor
    private final UserService userService;



    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody UserDTO user){
        userService.saveUser(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@RequestBody UserDTO user, @PathVariable Integer id){
        userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Integer id){
        try {
            userService.deleteUser(id);
        }
        catch (Exception e) {
            System.err.println("El metodo delete no soporta la api externa: " + e.getMessage());
            throw new UnsupportedOperationException("El metodo delete no esta disponible en la API externa");
        }
    }

}
