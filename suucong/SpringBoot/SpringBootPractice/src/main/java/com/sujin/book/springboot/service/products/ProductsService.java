package com.sujin.book.springboot.service.products;

import com.sujin.book.springboot.domain.products.Products;
import com.sujin.book.springboot.domain.products.ProductsRepository;
import com.sujin.book.springboot.web.dto.ProductsResponseDto;
import com.sujin.book.springboot.web.dto.ProductsSaveRequestDto;
import com.sujin.book.springboot.web.dto.ProductsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductsService {

    private final ProductsRepository productsRepository;

    // 상품 저장
    @Transactional
    public Long save(ProductsSaveRequestDto requestDto) {
        return productsRepository.save(requestDto.toEntity()).getProductNum();
    }

    // 상품 정보 수정
    @Transactional
    public Long update(Long productNum, ProductsUpdateRequestDto requestDto) {
        Products products = productsRepository.findById(productNum)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. productNum=" + productNum));
        products.update(requestDto.getName(), requestDto.getPrice(), requestDto.getStock(), requestDto.getCategory());

        return productNum;
    }

    // 상품 조회
    @Transactional
    public ProductsResponseDto findByProductNum(Long productNum) {
        Products products = productsRepository.findById(productNum)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. productNum=" + productNum));

        return new ProductsResponseDto(products);
    }

    // 상품 삭제
    @Transactional
    public Long delete(Long productNum) {
        Products products = productsRepository.findById(productNum)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. productNum=" + productNum));
        productsRepository.delete(products);

        return productNum;
    }

}
