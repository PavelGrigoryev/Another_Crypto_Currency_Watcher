package com.pavel.anothercryptocurrencywatcher.controller;

import com.pavel.anothercryptocurrencywatcher.dto.CoinDto;
import com.pavel.anothercryptocurrencywatcher.dto.PriceDto;
import com.pavel.anothercryptocurrencywatcher.service.CoinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/coins")
public class CoinController {

    private final CoinService coinService;

    public CoinController(CoinService coinService) {
        this.coinService = coinService;
    }

    @GetMapping
    public ResponseEntity<List<CoinDto>> getAll() {
        return new ResponseEntity<>(coinService.getAllCoins(), HttpStatus.OK);
    }

    @GetMapping("/{symbol}")
    public ResponseEntity<PriceDto> getPrice(@PathVariable String symbol) {
        return new ResponseEntity<>(coinService.getLastPrice(symbol), HttpStatus.OK);
    }
}
