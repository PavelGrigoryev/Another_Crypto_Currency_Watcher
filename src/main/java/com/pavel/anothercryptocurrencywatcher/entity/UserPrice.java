package com.pavel.anothercryptocurrencywatcher.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "user_coin_price")
public class UserPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.ms")
    private LocalDateTime memberDate = LocalDateTime.now();

    @Column(name = "member_price")
    private Double userPrice;

    public UserPrice(String userName, String symbol, Double userPrice) {
        this.userName = userName;
        this.symbol = symbol;
        this.userPrice = userPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserPrice userPrice = (UserPrice) o;
        return id != null && Objects.equals(id, userPrice.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
