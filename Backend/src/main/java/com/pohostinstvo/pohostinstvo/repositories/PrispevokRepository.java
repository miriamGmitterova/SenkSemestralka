package com.pohostinstvo.pohostinstvo.repositories;

import com.pohostinstvo.pohostinstvo.entity.Prispevok;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PrispevokRepository extends JpaRepository<Prispevok, UUID> {

    List<Prispevok> findAllByOrderByVytvoreneDesc();
}
