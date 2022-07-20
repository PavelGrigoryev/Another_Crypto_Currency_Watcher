package com.pavel.anothercryptocurrencywatcher.entity;

import com.pavel.anothercryptocurrencywatcher.dto.CoinDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "coins")
public class Coin {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "external_id")
    private Long externalId;

    @Column(name = "symbol")
    private String symbol;

    public CoinDto toDto() {
        CoinDto dto = new CoinDto();
        dto.setId(this.externalId);
        dto.setSymbol(this.symbol);
        return dto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Coin coin = (Coin) o;
        return id != null && Objects.equals(id, coin.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
