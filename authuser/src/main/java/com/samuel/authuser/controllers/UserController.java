package com.samuel.authuser.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.samuel.authuser.dtos.UserDto;
import com.samuel.authuser.models.User;
import com.samuel.authuser.services.UserService;
import com.samuel.authuser.specifications.SpecificationTemplate;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600) //métodos podem ser acessados de qualquer origem
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    /**
     *
     * @param spec
     * @param pageable paginacao numero da pagina, tamanho da pagina e ordenacao como default, sendo
     *                 possível que o usuário defina essas propriedades.
     * @return
     */
    @GetMapping
    public ResponseEntity<Page<User>> getAllUsers(SpecificationTemplate.UserSpec spec,
                                                  @PageableDefault(page = 0, size = 10, sort = "userId",
                                                          direction = Sort.Direction.ASC) Pageable pageable) {
        Page<User> userPage = userService.findAll(spec, pageable);
        if (!userPage.isEmpty()) {
            userPage.stream().forEach(e -> e.add(
                    linkTo(methodOn(UserController.class).getOneUser(e.getUserId())).withSelfRel())
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(userPage);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "userId") UUID userId) {
        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(userOptional.get());
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "userId") UUID userId) {
        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } else {
            userService.delete(userOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("User deleted successfull");
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "userId") UUID userId,
                                             @RequestBody
                                             @Validated(UserDto.UserView.UserPut.class)
                                             @JsonView(UserDto.UserView.UserPut.class) UserDto dto) {
        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } else {

            var user = userOptional.get();
            user.setFullName(dto.getFullName());
            user.setPhoneNumber(user.getPhoneNumber());
            user.setCpf(dto.getCpf());
            user.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
            userService.save(user);
            log.debug("PUT change userId updated {}", user.getUserId());
            log.info("User changed successfully userId {}", user.getUserId());
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
    }

    @PutMapping("/{userId}/password")
    public ResponseEntity<Object> updatePassword(@PathVariable(value = "userId") UUID userId,
                                                 @RequestBody
                                                 @Validated(UserDto.UserView.PasswordPut.class)
                                                 @JsonView(UserDto.UserView.PasswordPut.class) UserDto dto) {
        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        if (!userOptional.get().getPassword().equals(dto.getOldPassword())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Mismatched old password");
        } else {
            var user = userOptional.get();
            user.setPassword(dto.getPassword());
            user.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
            userService.save(user);
            log.debug("PUT change password userId updated {}", user.getUserId());
            log.info("User changed successfully userId {}", user.getUserId());
            return ResponseEntity.status(HttpStatus.OK).body("Password has change!");
        }
    }

    @PutMapping("/{userId}/image")
    public ResponseEntity<Object> updateImage(@PathVariable(value = "userId") UUID userId,
                                              @RequestBody
                                              @Validated(UserDto.UserView.ImagePut.class)
                                              @JsonView(UserDto.UserView.ImagePut.class) UserDto dto) {
        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } else {
            var user = userOptional.get();
            user.setImageUrl(dto.getImageUrl());
            user.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
            userService.save(user);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
    }
}
