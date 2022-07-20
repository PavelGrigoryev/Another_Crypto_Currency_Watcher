package com.pavel.anothercryptocurrencywatcher.controller;

import com.pavel.anothercryptocurrencywatcher.entity.UserPrice;
import com.pavel.anothercryptocurrencywatcher.service.CoinService;
import com.pavel.anothercryptocurrencywatcher.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notify")
public class UserController {

    private final UserService userService;

    private final CoinService coinService;

    public UserController(UserService userService, CoinService coinService) {
        this.userService = userService;
        this.coinService = coinService;
    }

    @PostMapping
    public ResponseEntity<String> notify(@RequestParam String userName, String symbol) {
        UserPrice userPrice = new UserPrice(userName, symbol, coinService.getLastPrice(symbol).getPrice());
        userService.save(userPrice);
        return new ResponseEntity<>("The user " + userName +
                " will be notified when the price changes by more than 1%", HttpStatus.OK);
    }
}
