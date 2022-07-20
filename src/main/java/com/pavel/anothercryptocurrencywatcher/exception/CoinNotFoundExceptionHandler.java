package com.pavel.anothercryptocurrencywatcher.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CoinNotFoundExceptionHandler {

    @ExceptionHandler(CoinNotFoundException.class)
    public ResponseEntity<Object> handleCoinNotFoundException(CoinNotFoundException exception) {

        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("no such currency supports",  "list of currencies: BTC, ETH, SOL");
        hashMap.put("timestamp", LocalDateTime.now().toString());
        hashMap.put("this item is not supported", exception.getMessage());

        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }
}
