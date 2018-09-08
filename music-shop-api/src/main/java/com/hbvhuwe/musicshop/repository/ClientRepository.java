package com.hbvhuwe.musicshop.repository;

import com.hbvhuwe.musicshop.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Integer> {
  Optional<Client> findClientByClientIdAndName(int id, String name);
}
