package com.jwt.example.service;

import com.jwt.example.dto.ProductDto;

import java.util.List;

public interface ProductService {
    public ProductDto createProduct(ProductDto productDto);

    public ProductDto getProductById(int productId);

    public List<ProductDto> getAllProducts();

    public ProductDto updateProductById(ProductDto productDto, int productId);

    public void deleteProduct(int productId);
}
