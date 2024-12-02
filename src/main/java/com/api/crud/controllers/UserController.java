package com.api.crud.controllers;

import com.api.crud.dtos.CreateUserDTO;
import com.api.crud.dtos.UpdateUserDTO;
import com.api.crud.models.UserModel;
import com.api.crud.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Obtener todos los usuarios", description = "Recupera una lista de todos los usuarios en la base de datos")
    @GetMapping
    public ArrayList<UserModel> getUsers() {
        return this.userService.getUsers();
    }

    @Operation(summary = "Guardar un nuevo usuario", description = "Crea y guarda un nuevo usuario")
    @PostMapping
    public UserModel saveUser(@RequestBody CreateUserDTO userDTO){
        UserModel user = new UserModel();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());

        return this.userService.saveUser(user);
    }

    @Operation(summary = "Obtener un usuario por ID", description = "Recupera un usuario por su ID")
    @GetMapping(path = "/{id}")
    public Optional<UserModel> getUserById(@PathVariable("id") Long id) {
        return this.userService.getById(id);
    }

    @Operation(summary = "Actualizar un usuario por ID", description = "Actualiza un usuario existente por su ID")
    @PutMapping(path = "/{id}")
    public UserModel UpdateUserById(@RequestBody @Valid UpdateUserDTO request, @PathVariable("id")Long id){
        Optional<UserModel> existingUserOptional = this.userService.getById(id);
        if (existingUserOptional.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado: " + id);
        }

        UserModel existingUser = existingUserOptional.get();
        existingUser.setFirstName(request.getFirstName());
        existingUser.setLastName(request.getLastName());
        existingUser.setEmail(request.getEmail());

        return this.userService.saveUser(existingUser);
    }

    @Operation(summary = "Eliminar un usuario por ID", description = "Elimina un usuario por su ID")
    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        boolean ok = this.userService.deleteUser(id);
        if (ok) {
            return "Usuario con ID " + id + " eliminado";
        } else {
            return "Error al eliminar el usuario";
        }
    }
}