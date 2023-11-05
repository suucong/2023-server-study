package com.sujin.book.springboot.web;

import com.sujin.book.springboot.domain.products.Products;
import com.sujin.book.springboot.domain.products.ProductsRepository;
import com.sujin.book.springboot.web.dto.ProductsSaveRequestDto;
import com.sujin.book.springboot.web.dto.ProductsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductsApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductsRepository productsRepository;

    @After
    public void tearDown() throws Exception {
        productsRepository.deleteAll();
    }

    @Test
    public void Products_등록된다() throws Exception {

        // given
        String name = "name";
        String category  = "category";

        ProductsSaveRequestDto requestDto = ProductsSaveRequestDto.builder()
                .name(name)
                .price(2000L)
                .stock(20L)
                .category(category)
                .build();

        String url = "http://localhost:" + port + "/api/v1/save/products";

        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Products> all = productsRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(name);
        assertThat(all.get(0).getCategory()).isEqualTo(category);
    }

    @Test
    public void Products_수정된다() throws Exception {

        // given
        Products savedProducts = productsRepository.save(Products.builder()
                .name("name")
                .price(2000L)
                .stock(20L)
                .category("category")
                .build());

        Long updateNum = savedProducts.getProductNum();
        String expectedName = "name2";
        String expectedCategory = "category2";

        ProductsUpdateRequestDto requestDto = ProductsUpdateRequestDto.builder()
                .name(expectedName)
                .price(2000L)
                .stock(20L)
                .category(expectedCategory)
                .build();

        String url = "http://localhost:" + port + "/api/v1/update/products/" + updateNum;

        HttpEntity<ProductsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        // when
        ResponseEntity<Long> responseEntity = restTemplate.
                exchange(url, HttpMethod.PUT,
                        requestEntity, Long.class);

        // when
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Products> all = productsRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(expectedName);
        assertThat(all.get(0).getCategory()).isEqualTo(expectedCategory);
    }
}
