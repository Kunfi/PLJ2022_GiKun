package ch.noseryoung.sbdemo01.domain.user;


import ch.noseryoung.sbdemo01.domain.exceptions.IdExistsException;
import ch.noseryoung.sbdemo01.domain.exceptions.NotFoundException;
import ch.noseryoung.sbdemo01.domain.role.Role;
import ch.noseryoung.sbdemo01.domain.role.RoleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, RoleService roleService, UserMapper userMapper) {
        this.userService = userService;
        this.roleService = roleService;
        this.userMapper = userMapper;
    }

    // Get all users
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    // Get DTO users
    @GetMapping("/DTO")
    @ResponseBody
    public Collection<UserDTO> getUsersDTO() {
        return userMapper.userToDTO(userService.getAllUsers());
    }

    // Get single User by ID
    @GetMapping("/{userId}")
    public ResponseEntity<User> findUserById
        (@PathVariable("userId") Integer userId) {
        return ResponseEntity.ok().body(userService.findById(userId));
    }

    // Create new User with this Info
    @PostMapping("/")
    public ResponseEntity<User> createNewUser(@Valid @RequestBody User newUser) {
        log.info("A user will be created...");
        return ResponseEntity.status(201).body(userService.createNewUser(newUser));
    }

    // Delete User with this ID
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser
    (@PathVariable("userId") Integer userId) {
        log.info("The User " + userService.findById(userId).getUserName() + " will be deleted.");
        return ResponseEntity.ok().body(userService.deleteUser(userId));
    }

    // Update User with this ID
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser
    (@PathVariable("userId") Integer userId, @Valid @RequestBody User user) {
        return ResponseEntity.ok().body(userService.updateUser(user));
    }

    // Create new Role
    @PostMapping("/Role")
    public ResponseEntity<Role> createNewRole(@Valid @RequestBody Role newRole) {
        log.info("A new role will be created...");
        return ResponseEntity.status(201).body(roleService.createNewRole(newRole));
    }



    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleStuffNotFoundException(NotFoundException nfe) {
        return ResponseEntity.status(404).body(nfe.getMessage());
    }

    @ExceptionHandler(IdExistsException.class)
    public ResponseEntity<String> handleIdExistException(IdExistsException iee) {
        return ResponseEntity.status(400).body(iee.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
        MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
