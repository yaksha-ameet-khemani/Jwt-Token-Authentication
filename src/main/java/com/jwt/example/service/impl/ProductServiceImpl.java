package com.jwt.example.service.impl;

import com.jwt.example.dto.ProductDto;
import com.jwt.example.entity.Product;
import com.jwt.example.exception.ResourceNotFound;
import com.jwt.example.repository.ProductRepository;
import com.jwt.example.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product toBeCreatedProduct = this.modelMapper.map(productDto, Product.class);
        Product createdProduct = this.productRepository.save(toBeCreatedProduct);
        return this.modelMapper.map(createdProduct, ProductDto.class);
    }

    @Override
    public ProductDto getProductById(int productId) {
        Product foundProduct = this.productRepository.findById(productId).orElseThrow(() -> new ResourceNotFound("Product", "id", String.valueOf(productId)));
        return this.modelMapper.map(foundProduct, ProductDto.class);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return this.productRepository.findAll().stream().map((product) -> this.modelMapper.map(product, ProductDto.class)).toList();
    }

    @Override
    public ProductDto updateProductById(ProductDto productDto, int productId) {
        Product foundProduct = this.productRepository.findById(productId).orElseThrow(() -> new ResourceNotFound("Product", "id", String.valueOf(productId)));
        foundProduct.setName(productDto.getName());
        foundProduct.setPrice(productDto.getPrice());
        Product updatedProduct = this.productRepository.save(foundProduct);
        return this.modelMapper.map(updatedProduct, ProductDto.class);
    }

    @Override
    public void deleteProduct(int productId) {
        Product foundProduct = this.productRepository.findById(productId).orElseThrow(() -> new ResourceNotFound("Product", "id", String.valueOf(productId)));
        this.productRepository.deleteById(productId);
    }
}
