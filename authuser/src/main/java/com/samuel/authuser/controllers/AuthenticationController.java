package com.samuel.authuser.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.samuel.authuser.dtos.UserDto;
import com.samuel.authuser.enums.UserStatus;
import com.samuel.authuser.enums.UserType;
import com.samuel.authuser.models.User;
import com.samuel.authuser.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthenticationController {

//    Logger logger = LoggerFactory.getLogger(AuthenticationController.class); usado com logback

//     Logger logger = LogManager.getLogger(AuthenticationController.class); substituido pela anotattion @Log4j2

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@RequestBody
                                               @Validated(UserDto.UserView.RegistrationPost.class)
                                               @JsonView(UserDto.UserView.RegistrationPost.class) UserDto userDto) {

//        log.debug("POST registerUser userDto received {}", userDto.toString());  logs em modo debug nunca devem ir para prod
        if (userService.existsByUserName(userDto.getUsername())) {
            log.warn("Username {} is Already Taken ! ", userDto.getUsername());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Username is Already Taken !");
        }
        if (userService.existsByEmail(userDto.getEmail())) {
            log.warn("Email {} is Already Taken ! ", userDto.getEmail());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Email is Already Taken !");
        }

        var user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setUserStatus(UserStatus.ACTIVE);
        user.setUserType(UserType.INSTRUCTOR);
        user.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        user.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        userService.save(user);
//        log.debug("POST registerUser userModel saved {}", user.toString());
        log.info("User saved successfully userId {}", user.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    /**
     * diferentes niveis de log que podemos aplicar
     */
    @GetMapping("/")
    public String index(){
        log.trace("LEVEL TRACE"); // utiliza-se quando queremos muitos detalhes
        log.debug("LEVEL DEBUG"); // principalmente usado em ambiente de desenvolvimento, informacoes relevantes para o dev
        log.info("LEVEL INFO"); // principalmente usado em prod, tras detalhes dos fluxos que ocorreram corretamente
        log.warn("LEVEL WARN"); //log para mostrar um alerta em prod
        log.error("LEVEL ERROR"); // usado em prod quando algo da errado , inclusive deve ser utilazo dentro de blocos try catch

        try {
            throw new Exception("Exception message");
        }catch (Exception e){
            log.error("----------ERROR-----------");
        }
        return "Loggin Spring boot"; // mensagem default
    }
}
