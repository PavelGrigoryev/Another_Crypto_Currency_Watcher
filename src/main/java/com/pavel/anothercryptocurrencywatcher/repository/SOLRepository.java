package com.pavel.anothercryptocurrencywatcher.repository;

import com.pavel.anothercryptocurrencywatcher.entity.SOLPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SOLRepository extends JpaRepository<SOLPrice, Long> {

    Optional<SOLPrice> findFirstBySymbolOrderByDateOfReceivingDesc(String symbol);
}
