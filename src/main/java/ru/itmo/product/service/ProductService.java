package ru.itmo.product.service;

import org.springframework.http.HttpStatus;
import ru.itmo.product.model.Count;
import ru.itmo.product.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Optional<List<Product>> getProducts();

    Optional<Product> getProduct(Integer id);

    Optional<Product> saveProducts(Product product);

    HttpStatus deleteProducts(Integer id);

    HttpStatus deleteProductsByMeasure(String unit);

    Count countByManufactureId(Integer id);

    Optional<Product> changeProducts(Product product);

    Optional<List<Product>> getDistinctByManufactureCost() ;
}
