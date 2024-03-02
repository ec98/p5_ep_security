package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.security.JwtUtils;
import com.example.demo.service.to.TokenTO;

@RestController
@RequestMapping("/token")
@CrossOrigin
public class AuthorizationControllerRestFul {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;

	// generar el token
//	@GetMapping(path = "/jwt", consumes = MediaType.APPLICATION_JSON_VALUE)
//	public String obtainToken(@RequestBody UsuarioTO usuario) {
//		this.autentication(usuario.getNombre(), usuario.getPassword());
//		return this.jwtUtils.wildTokenJWT(usuario.getNombre());
//	}

	// obtener el token
	@PostMapping(path = "/getToken", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getToken(@RequestBody TokenTO token) {
		this.autentication("Alison", "1234");
		return ResponseEntity.status(HttpStatus.OK).body(this.jwtUtils.getTokenBuilder("Alison", token.getSemilla(), token.getTiempo()));
	}

	// metodo estandar para realizar autenticacion
	private void autentication(String usuario, String password) {
		UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(usuario, password);
		Authentication authentication = this.authenticationManager.authenticate(userToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
