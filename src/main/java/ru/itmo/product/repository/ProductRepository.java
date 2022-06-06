package ru.itmo.product.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.itmo.product.model.Product;
import ru.itmo.product.model.enums.UnitOfMeasure;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product,Integer> {
    void deleteAllByUnitOfMeasure(UnitOfMeasure unitOfMeasure);
    List<Product> findAllByManufacturer_Id(Integer id);

    @Query(value = "SELECT DISTINCT ON (manufacturecost) * FROM product", nativeQuery = true)
    List<Product> findDistinctByManufactureCost();
}
