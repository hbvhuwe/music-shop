package com.hbvhuwe.musicshop.controller;

import com.hbvhuwe.musicshop.model.Admin;
import com.hbvhuwe.musicshop.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Base64;
import java.util.Optional;

@Controller
@RequestMapping(path = "/admin/auth")
public class AdminAuthController {

  private final AdminRepository adminRepository;

  @Autowired
  public AuthController(AdminRepository adminRepository) {
    this.adminRepository = adminRepository;
  }

  @GetMapping(path = "/check_credentials")
  public ResponseEntity
  checkCredentials(@RequestHeader(value = "Authorization") String auth) {
    String authEncoded =
        new String(Base64.getDecoder().decode(auth.getBytes()));
    String[] cred = authEncoded.split(":");
    Optional<Admin> admin = adminRepository.findAdminByAdminIdAndPassword(
        Integer.parseInt(cred[0]), cred[1]);
    if (admin.isPresent()) {
      return ResponseEntity.ok("{\"status\":\"success\"}");
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body("{\"status\":\"error\"}");
    }
  }

  @GetMapping(path = "/user")
  public ResponseEntity<Admin>
  getUserInfo(@RequestHeader(value = "Authorization") String auth) {
    String authEncoded =
        new String(Base64.getDecoder().decode(auth.getBytes()));
    String[] cred = authEncoded.split(":");
    Optional<Admin> admin = adminRepository.findAdminByAdminIdAndPassword(
        Integer.parseInt(cred[0]), cred[1]);
    if (admin.isPresent()) {
      return ResponseEntity.ok(admin.get());
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
  }

  @GetMapping(path = "/login")
  public ResponseEntity
  logIn(@RequestParam(name = "login") String login,
        @RequestParam(name = "password") String password) {
    Optional<Admin> admin =
        adminRepository.findAdminByLoginAndPassword(login, password);
    if (admin.isPresent()) {
      return ResponseEntity.ok("{\"status\":\"success\",\"adminId\":\"" +
          admin.get().getAdminId() + "\"}");
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body("{\"status\":\"error\"}");
    }
  }
}
