package sk.umb.systemSTK.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import sk.umb.systemSTK.security.JwtTokenProvider;
import sk.umb.systemSTK.services.UserService;
import sk.umb.systemSTK.utils.AuthResponse;
import sk.umb.systemSTK.utils.UserDTO;

import java.util.Optional;

@RestController
public class UserController {
    private final String AUTHORIZATION_HEADER = "Authorization";
    @Autowired
    UserService userService;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    private final AuthenticationManager authenticationManager;
    public UserController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

//    @PostMapping("api/login")
//    public void postLogin(){
//    }
    @PostMapping("/login")
    public ResponseEntity<?> postLogin(@RequestBody UserDTO userDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getUserName(), userDTO.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication); // Tu zavolaj JWT generátor

        return ResponseEntity.ok(new AuthResponse(jwt));

    }
}
