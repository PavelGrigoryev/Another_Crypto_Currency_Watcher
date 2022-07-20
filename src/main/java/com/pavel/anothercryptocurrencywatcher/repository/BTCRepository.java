package com.pavel.anothercryptocurrencywatcher.repository;

import com.pavel.anothercryptocurrencywatcher.entity.BTCPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BTCRepository extends JpaRepository<BTCPrice, Long> {

    Optional<BTCPrice> findFirstBySymbolOrderByDateOfReceivingDesc(String symbol);
}
