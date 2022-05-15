package ru.itmo.product.service;

import org.springframework.http.HttpStatus;
import ru.itmo.product.model.Count;
import ru.itmo.product.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Optional<List<Product>> getProducts();

    Optional<Product> getProduct(Long id);

    Optional<Product> saveProducts(Product product);

    HttpStatus deleteProducts(Long id);

    HttpStatus deleteProductsByMeasure(String unit);

    Count countByManufactureId(Long id);

    Optional<Product> changeProducts(Product product);

    Optional<List<Product>> getDistinctByManufactureCost() ;
}
