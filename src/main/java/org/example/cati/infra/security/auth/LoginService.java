package org.example.cati.infra.security.auth;

import org.example.cati.infra.security.config.JwtServiceGenerator;
import org.example.cati.model.usuario.Usuario;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Service
public class LoginService {

    @Autowired
    private LoginRepository repository;
    @Autowired
    private JwtServiceGenerator jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;


    public String logar(Login login) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getLogin(),
                        login.getSenha()
                )
        );
        Usuario user = repository.findByLogin(login.getLogin()).get();
        String jwtToken = jwtService.generateToken(user);

        return jwtToken;
    }

}
