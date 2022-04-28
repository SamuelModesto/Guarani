package com.samuel.authuser.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.samuel.authuser.dtos.UserDto;
import com.samuel.authuser.enums.UserStatus;
import com.samuel.authuser.enums.UserType;
import com.samuel.authuser.models.User;
import com.samuel.authuser.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthenticationController {

    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@RequestBody
                                               @Validated(UserDto.UserView.RegistrationPost.class)
                                               @JsonView(UserDto.UserView.RegistrationPost.class) UserDto userDto) {

        if (userService.existsByUserName(userDto.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Username is Already Taken !");
        }
        if (userService.existsByEmail(userDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Email is Already Taken !");
        }

        var user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setUserStatus(UserStatus.ACTIVE);
        user.setUserType(UserType.INSTRUCTOR);
        user.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        user.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    /**
     * diferentes niveis de log que podemos aplicar
     */
    @GetMapping("/")
    public String index(){
        logger.trace("LEVEL TRACE"); // utiliza-se quando queremos muitos detalhes
        logger.debug("LEVEL DEBUG"); // principalmente usado em ambiente de desenvolvimento, informacoes relevantes para o dev
        logger.info("LEVEL INFO"); // principalmente usado em prod, tras detalhes dos fluxos que ocorreram corretamente
        logger.warn("LEVEL WARN"); //log para mostrar um alerta
        logger.error("LEVEL ERROR"); // usado quando algo da errado , inclusive deve ser utilazo dentro de blocos try catch
        return "Loggin Spring boot"; // mensagem default
    }
}
