package com.pavel.anothercryptocurrencywatcher.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pavel.anothercryptocurrencywatcher.dto.PriceDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "btc_price")
public class BTCPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "external_id")
    private Long externalId;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "save_date", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.ms")
    private LocalDateTime dateOfReceiving = LocalDateTime.now();

    @Column(name = "coin_price")
    private Double coinPrice;

    public PriceDto toDto() {
        PriceDto dto = new PriceDto();
        dto.setId(this.externalId);
        dto.setSymbol("BTC");
        dto.setPrice(this.coinPrice);
        dto.setDate(this.dateOfReceiving);
        return dto;
    }

    public static BTCPrice fromDto(PriceDto dto) {
        BTCPrice price = new BTCPrice();
        price.setExternalId(dto.getId());
        price.setSymbol(dto.getSymbol());
        price.setCoinPrice(dto.getPrice());
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BTCPrice btcPrice = (BTCPrice) o;
        return id != null && Objects.equals(id, btcPrice.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
