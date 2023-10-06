package com.sujin.book.springboot.web.dto;

import com.sujin.book.springboot.domain.products.Products;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProductsSaveRequestDto {

    private String name;
    private Long price;
    private Long stock;
    private String category;

    @Builder
    public ProductsSaveRequestDto(String name, Long price, Long stock, String category) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public Products toEntity() {
        return Products.builder()
                .name(name)
                .price(price)
                .stock(stock)
                .category(category)
                .build();
    }

}
