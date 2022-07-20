package com.pavel.anothercryptocurrencywatcher.service.impl;

import com.pavel.anothercryptocurrencywatcher.entity.UserPrice;
import com.pavel.anothercryptocurrencywatcher.repository.UserPriceRepository;
import com.pavel.anothercryptocurrencywatcher.service.CoinService;
import com.pavel.anothercryptocurrencywatcher.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserPriceRepository userPriceRepository;

    private final CoinService coinService;

    public UserServiceImpl(UserPriceRepository userPriceRepository, CoinService coinService) {
        this.userPriceRepository = userPriceRepository;
        this.coinService = coinService;
    }

    @Override
    public void save(UserPrice userPrice) {
        userPriceRepository.save(userPrice);
        coinService.addPriceToTracking(userPrice);
    }
}
