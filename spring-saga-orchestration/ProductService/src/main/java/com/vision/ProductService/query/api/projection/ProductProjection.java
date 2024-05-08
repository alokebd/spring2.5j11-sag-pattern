package com.vision.ProductService.query.api.projection;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.vision.ProductService.command.api.data.Product;
import com.vision.ProductService.command.api.data.ProductRepository;
import com.vision.ProductService.command.api.model.ProductRestModel;
import com.vision.ProductService.query.api.queries.GetProductsQuery;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection {

    private ProductRepository productRepository;

    public ProductProjection(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<ProductRestModel> handle(GetProductsQuery getProductsQuery) {
        List<Product> products =
                productRepository.findAll();

        List<ProductRestModel> productRestModels =
                products.stream()
                        .map(product -> ProductRestModel
                                .builder()
                                .quantity(product.getQuantity())
                                .price(product.getPrice())
                                .name(product.getName())
                                .build())
                        .collect(Collectors.toList());

        return productRestModels;
    }
}
