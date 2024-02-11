package com.pohostinstvo.pohostinstvo.repositories;

import com.pohostinstvo.pohostinstvo.entity.Pouzivatel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PouzivatelRepository extends JpaRepository<Pouzivatel, UUID> {
}
