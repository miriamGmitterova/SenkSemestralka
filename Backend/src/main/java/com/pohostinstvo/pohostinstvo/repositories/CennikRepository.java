package com.pohostinstvo.pohostinstvo.repositories;

import com.pohostinstvo.pohostinstvo.entity.Polozka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CennikRepository extends JpaRepository<Polozka, UUID> {
}
