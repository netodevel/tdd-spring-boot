package com.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SalarioMinimoRepository extends JpaRepository<SalarioMinimo, Integer> {

}
