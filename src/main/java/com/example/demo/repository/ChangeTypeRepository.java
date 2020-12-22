package com.example.demo.repository;

import com.example.demo.entity.ChangeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeTypeRepository extends JpaRepository<ChangeType, Integer> {
    ChangeType findByCurrency(String currency);
}
