package com.catalisa.cybernomad.repository;

import com.catalisa.cybernomad.model.AdressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdressRepository extends JpaRepository<AdressModel, Long> {
}
