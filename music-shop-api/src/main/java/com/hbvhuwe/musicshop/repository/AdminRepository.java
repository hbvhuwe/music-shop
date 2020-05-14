package com.hbvhuwe.musicshop.repository;

import com.hbvhuwe.musicshop.model.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AdminRepository extends CrudRepository<Admin, Integer> {
  Optional<Admin> findAdminByAdminIdAndPassword(int id, String password);
  Optional<Admin> findAdminByLoginAndPassword(String login, String password);
}
