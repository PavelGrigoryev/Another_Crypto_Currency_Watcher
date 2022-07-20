package com.pavel.anothercryptocurrencywatcher.service;

import com.pavel.anothercryptocurrencywatcher.dto.CoinDto;
import com.pavel.anothercryptocurrencywatcher.dto.PriceDto;
import com.pavel.anothercryptocurrencywatcher.entity.UserPrice;

import java.util.List;

public interface CoinService {
    List<CoinDto> getAllCoins();

    PriceDto getLastPrice(String symbol);

    void addPriceToTracking(UserPrice userPrice);
}
