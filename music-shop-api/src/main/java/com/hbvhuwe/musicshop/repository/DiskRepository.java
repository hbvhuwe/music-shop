package com.hbvhuwe.musicshop.repository;

import com.hbvhuwe.musicshop.model.Disk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiskRepository extends JpaRepository<Disk, Integer> {
  List<Disk> getAllByGroupId(int groupId);
}
