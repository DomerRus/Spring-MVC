package ru.itmo.product.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itmo.product.model.Coordinates;

public interface CoordinatesRepository extends CrudRepository<Coordinates, Long> {
}
