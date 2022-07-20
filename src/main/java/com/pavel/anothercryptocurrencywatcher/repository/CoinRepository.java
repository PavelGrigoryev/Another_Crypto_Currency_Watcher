package com.pavel.anothercryptocurrencywatcher.repository;

import com.pavel.anothercryptocurrencywatcher.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Long> {
}
