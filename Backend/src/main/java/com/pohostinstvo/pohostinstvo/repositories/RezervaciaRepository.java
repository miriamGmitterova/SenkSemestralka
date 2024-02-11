package com.pohostinstvo.pohostinstvo.repositories;


import com.pohostinstvo.pohostinstvo.entity.Rezervacia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RezervaciaRepository extends JpaRepository<Rezervacia, UUID> {
}
