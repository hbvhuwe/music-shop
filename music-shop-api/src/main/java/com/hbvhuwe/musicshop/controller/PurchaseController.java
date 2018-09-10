package com.hbvhuwe.musicshop.controller;

import com.hbvhuwe.musicshop.model.Checks;
import com.hbvhuwe.musicshop.model.Client;
import com.hbvhuwe.musicshop.repository.ChecksRepository;
import com.hbvhuwe.musicshop.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/purchase")
public class PurchaseController {

  private final ChecksRepository checksRepository;
  private final ClientRepository clientRepository;

  @Autowired
  public PurchaseController(ChecksRepository checksRepository, ClientRepository clientRepository) {
    this.checksRepository = checksRepository;
    this.clientRepository = clientRepository;
  }

  @PutMapping(path = "/{diskId}")
  @ResponseBody
  public ResponseEntity purchaseDisk(
      @PathVariable long diskId,
      @RequestParam(name = "PurchaseValue") double purchaseValue,
      @RequestHeader(name = "Authorization") String auth) {
    String authEncoded = new String(Base64.getDecoder().decode(auth));
    String[] cred = authEncoded.split(":");
    int clientId = Integer.parseInt(cred[0]);
    Optional<Client> cl = clientRepository.findClientByClientIdAndName(clientId, cred[1]);
    if (cl.isPresent()) {
      List<Checks> ch = checksRepository.findAllByClientId(clientId);
      final boolean[] alreadyPurchased = {false};
      ch.forEach(
          checks -> {
            if (checks.getDiskId() == diskId) {
              alreadyPurchased[0] = true;
            }
          });
      if (!alreadyPurchased[0]) {
        Checks checks = new Checks();
        checks.setClientId(Long.parseLong(cred[0]));
        checks.setDiskId(diskId);
        checks.setPurchaseValue(purchaseValue);
        checks.setPurchaseType(1);
        checks.setPurchaseDate(Date.valueOf(LocalDate.now()));
        checksRepository.save(checks);
        return ResponseEntity.ok("{\"status\":\"success\"}");
      } else {
        return ResponseEntity.ok("{\"status\":\"fail\", \"message\":\"already purchased\"}");
      }
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }
}
