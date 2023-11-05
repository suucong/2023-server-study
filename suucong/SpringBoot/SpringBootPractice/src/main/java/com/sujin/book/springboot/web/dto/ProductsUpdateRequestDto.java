package com.sujin.book.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductsUpdateRequestDto {

    private String name;
    private Long price;
    private Long stock;
    private String category;

    @Builder
    public ProductsUpdateRequestDto(String name, Long price, Long stock, String category) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

}
