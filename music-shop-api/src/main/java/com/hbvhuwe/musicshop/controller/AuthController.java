package com.hbvhuwe.musicshop.controller;

import com.hbvhuwe.musicshop.model.Client;
import com.hbvhuwe.musicshop.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Optional;

@Controller
@RequestMapping(path = "/auth")
public class AuthController {

  private final ClientRepository clientRepository;

  @Autowired
  public AuthController(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  @GetMapping(path = "/check_credentials")
  public ResponseEntity login(@RequestHeader(value = "Authorization") String auth) {
    String authEncoded = new String(Base64.getDecoder().decode(auth.getBytes()));
    String[] cred = authEncoded.split(":");
    Optional<Client> client =
        clientRepository.findClientByClientIdAndPassword(Integer.parseInt(cred[0]), cred[1]);
    if (client.isPresent()) {
      return ResponseEntity.ok("{\"status\":\"success\"}");
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
  }

  @PutMapping(path = "/sign_up")
  public ResponseEntity signUp(
      @RequestParam(name = "Name") String name,
      @RequestParam(name = "Surname") String surname,
      @RequestParam(name = "Password") String password) {
    Client cl = new Client();
    cl.setName(name);
    cl.setSurname(surname);
    cl.setPassword(password);
    clientRepository.save(cl);
    return ResponseEntity.ok("{\"status\":\"success\"}");
  }
}