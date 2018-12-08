package com.hbvhuwe.musicshop.repository;

import com.hbvhuwe.musicshop.model.Composition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompositionRepository extends JpaRepository<Composition, Integer> {
  List<Composition> getAllByDiskId(int diskId);
}
