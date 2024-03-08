package com.sujin.book.springboot.domain.products;

import javax.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productNum;

    @Column(length = 200, nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long stock;

    @Column(length = 200, nullable = false)
    private String category;

    @Builder
    public Products(String name, Long price, Long stock, String category) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public void update(String name, Long price, Long stock, String category) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }
}
