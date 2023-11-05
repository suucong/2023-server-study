package com.sujin.book.springboot.web;

import com.sujin.book.springboot.service.products.ProductsService;
import com.sujin.book.springboot.web.dto.ProductsResponseDto;
import com.sujin.book.springboot.web.dto.ProductsSaveRequestDto;
import com.sujin.book.springboot.web.dto.ProductsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ProductsApiController {

    private final ProductsService productsService;

    @PostMapping("/api/v1/save/products")
    public Long save(@RequestBody ProductsSaveRequestDto requestDto) {
        return productsService.save(requestDto);
    }

    @PutMapping("/api/v1/update/products/{productNum}")
    public Long update(@PathVariable Long productNum, @RequestBody ProductsUpdateRequestDto requestDto) {
        return productsService.update(productNum, requestDto);
    }

    @GetMapping("/api/v1/get/products/{productNum}")
    public ProductsResponseDto getProducts(@PathVariable Long productNum) {
        return productsService.findByProductNum(productNum);
    }

    @DeleteMapping("/api/v1/delete/products/{productNum}")
    public Long deleteProducts(@PathVariable Long productNum) {
        return productsService.delete(productNum);
    }
}
