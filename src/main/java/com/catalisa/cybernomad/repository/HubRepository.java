package com.catalisa.cybernomad.repository;

import com.catalisa.cybernomad.model.HubModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HubRepository extends JpaRepository<HubModel, Long> {
}
