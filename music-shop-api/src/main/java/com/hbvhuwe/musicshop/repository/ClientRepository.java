package com.hbvhuwe.musicshop.repository;

import com.hbvhuwe.musicshop.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Integer> {
  Optional<Client> findClientByClientIdAndPassword(int id, String password);
  Optional<Client> findClientByLoginAndPassword(String login, String password);
}
