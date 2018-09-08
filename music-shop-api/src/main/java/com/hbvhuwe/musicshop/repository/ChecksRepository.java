package com.hbvhuwe.musicshop.repository;

import com.hbvhuwe.musicshop.model.Checks;
import org.springframework.data.repository.CrudRepository;

public interface ChecksRepository extends CrudRepository<Checks, Integer> {
  Iterable<Checks> findAllByClientId(long id);
}
