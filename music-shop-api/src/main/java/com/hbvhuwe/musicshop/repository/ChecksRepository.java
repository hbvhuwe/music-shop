package com.hbvhuwe.musicshop.repository;

import com.hbvhuwe.musicshop.model.Checks;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChecksRepository extends CrudRepository<Checks, Integer> {
  List<Checks> findAllByClientId(long id);
}
