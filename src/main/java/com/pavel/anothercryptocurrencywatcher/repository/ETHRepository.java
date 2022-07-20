package com.pavel.anothercryptocurrencywatcher.repository;

import com.pavel.anothercryptocurrencywatcher.entity.ETHPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ETHRepository extends JpaRepository<ETHPrice, Long> {

    Optional<ETHPrice> findFirstBySymbolOrderByDateOfReceivingDesc(String symbol);
}
