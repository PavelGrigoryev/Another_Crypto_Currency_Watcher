package com.pavel.anothercryptocurrencywatcher.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceDto {

    private Long id;

    private String symbol;

    @JsonProperty("price_usd")
    private Double price;

    private LocalDateTime date;
}
