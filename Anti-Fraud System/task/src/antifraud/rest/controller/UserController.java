package antifraud.rest.controller;

import antifraud.domain.model.User;
import antifraud.domain.service.UserService;
import antifraud.exceptions.ExistingUsernameException;
import antifraud.rest.dto.DeletedUserDTO;
import antifraud.rest.dto.UserAccessDTO;
import antifraud.rest.dto.UserDTO;
import antifraud.rest.dto.UserRoleDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user")
    public UserDTO createUser(@Valid @RequestBody UserDTO userDTO) {
        User registeredUser = userService.registerUser(userDTO.toModel())
                .orElseThrow(() -> new ExistingUsernameException(HttpStatus.CONFLICT));
        return UserDTO.fromModel(registeredUser);
    }

    @GetMapping("/list")
    public List<UserDTO> getUsers() {
        List<User> allUsers = userService.getUsers();
        return allUsers.stream()
                .map(UserDTO::fromModel)
                .toList();
    }

    @DeleteMapping("/user/{username}")
    public DeletedUserDTO deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return DeletedUserDTO.builder()
                .username(username)
                .status("Deleted successfully!")
                .build();
    }

    @PutMapping("/role")
    UserRoleDTO changeUserRole(@Valid @RequestBody UserRoleDTO userRoleDTO) {
        User changedUserRole = userService.changeUserRole(userRoleDTO.toModel());
        return UserRoleDTO.fromModel(changedUserRole);
    }

    @PutMapping("/access")
    UserAccessDTO grantAccess(@Valid @RequestBody UserAccessDTO userAccessDTO) {
        User userPermission = userService.grantAccess(userAccessDTO.toModel());
        return UserAccessDTO.fromModel(userPermission);
    }
}