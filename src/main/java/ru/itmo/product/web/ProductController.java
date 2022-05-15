package ru.itmo.product.web;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.product.model.Coordinates;
import ru.itmo.product.model.Organization;
import ru.itmo.product.model.Product;
import ru.itmo.product.model.enums.OrganizationType;
import ru.itmo.product.model.enums.UnitOfMeasure;
import ru.itmo.product.service.ProductService;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getProducts() {
        Optional<List<Product>> products = productService.getProducts();
        if(products.get().size()>0) {
            return products
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.noContent().build());
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        return productService.getProduct(id)
                .map(item -> ResponseEntity.ok().contentType(MediaType.APPLICATION_XML).body(item))
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> saveProduct(@RequestBody Product product) {
        return productService.saveProducts(product)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Product> changeProduct(@RequestBody Product product) {
        return productService.changeProducts(product)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> deleteProductById(@PathVariable("id") Long id) {
        return ResponseEntity.status(productService.deleteProducts(id)).build();
    }

    @DeleteMapping(value = "/measure", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> deleteProductByMeasure(@RequestParam(name = "unit") String unit) {
        return ResponseEntity.status(productService.deleteProductsByMeasure(unit)).build();
    }

    @GetMapping(value = "/measure", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getCountByManufacture(@RequestParam(name = "manufactureId") Long id){
        return ResponseEntity.ok(productService.countByManufactureId(id));
    }

    @GetMapping(value = "/distinct", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getDistinctByManufactureCost(){
        return productService.getDistinctByManufactureCost()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
}
