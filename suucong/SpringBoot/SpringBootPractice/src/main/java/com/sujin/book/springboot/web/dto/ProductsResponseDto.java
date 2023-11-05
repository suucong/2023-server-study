package com.sujin.book.springboot.web.dto;

import com.sujin.book.springboot.domain.products.Products;
import lombok.Getter;

@Getter
public class ProductsResponseDto {
    private Long productNum;
    private String name;
    private Long price;
    private Long stock;
    private String category;

    public ProductsResponseDto(Products products) {
        this.productNum = products.getProductNum();
        this.name = products.getName();
        this.price = products.getProductNum();
        this.stock = products.getStock();
        this.category = products.getCategory();
    }

}
