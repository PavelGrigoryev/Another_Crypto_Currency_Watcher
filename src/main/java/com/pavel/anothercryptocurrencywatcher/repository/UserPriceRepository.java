package com.pavel.anothercryptocurrencywatcher.repository;

import com.pavel.anothercryptocurrencywatcher.entity.UserPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPriceRepository extends JpaRepository<UserPrice, Long> {
}
