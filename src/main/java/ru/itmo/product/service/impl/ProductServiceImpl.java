package ru.itmo.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.itmo.product.model.Coordinates;
import ru.itmo.product.model.Count;
import ru.itmo.product.model.Organization;
import ru.itmo.product.model.Product;
import ru.itmo.product.model.enums.UnitOfMeasure;
import ru.itmo.product.repository.CoordinatesRepository;
import ru.itmo.product.repository.OrganizationRepository;
import ru.itmo.product.repository.ProductRepository;
import ru.itmo.product.service.ProductService;


import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private CoordinatesRepository coordinatesRepository;

    @Override
    public Optional<List<Product>> getProducts() {
        return Optional.of(
                (List<Product>) productRepository.findAll()
        );
    }

    @Override
    public Optional<Product> getProduct(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> saveProducts(Product product) {
        coordinatesRepository.save(product.getCoordinates());
        if(product.getManufacturer() != null) {
            Optional<Organization> organization =
                    organizationRepository.findByNameAndFullNameAndAndEmployeesCountAndType(
                            product.getManufacturer().getName(),
                            product.getManufacturer().getFullName(),
                            product.getManufacturer().getEmployeesCount(),
                            product.getManufacturer().getType());
            if (organization.isEmpty()) {
                organizationRepository.save(product.getManufacturer());
            } else {
                product.setManufacturer(organization.get());
            }
        }
        product.setCreationDate(LocalDateTime.now());
        return Optional.of(productRepository.save(product));
    }

    @Override
    @Transactional(dontRollbackOn = Exception.class)
    public HttpStatus deleteProducts(Integer id) {
        try{
            productRepository.deleteById(id);
            return HttpStatus.OK;
        } catch (Exception ex){
            return HttpStatus.BAD_REQUEST;
        }
    }

    @Override
    @Transactional(dontRollbackOn = Exception.class)
    public HttpStatus deleteProductsByMeasure(String measure) {
        try{
            productRepository.deleteAllByUnitOfMeasure(UnitOfMeasure.valueOf(measure));
            return HttpStatus.OK;
        } catch (Exception ex){
            return HttpStatus.BAD_REQUEST;
        }
    }

    @Override
    public Count countByManufactureId(Integer id){
        return new Count(productRepository.findAllByManufacturer_Id(id).size());
    }

    @Override
    public Optional<Product> changeProducts(Product product) {
        coordinatesRepository.save(product.getCoordinates());
        if(product.getManufacturer() != null) {
            Optional<Organization> organization =
                    organizationRepository.findByNameAndFullNameAndAndEmployeesCountAndType(
                            product.getManufacturer().getName(),
                            product.getManufacturer().getFullName(),
                            product.getManufacturer().getEmployeesCount(),
                            product.getManufacturer().getType());
            if (organization.isEmpty()) {
                organizationRepository.save(product.getManufacturer());
            } else {
                product.setManufacturer(organization.get());
            }
        }
        product.setCreationDate(productRepository.findById(product.getId()).get().getCreationDate());
        return Optional.of(productRepository.save(product));
    }

    @Override
    public Optional<List<Product>> getDistinctByManufactureCost() {
        return Optional.of(productRepository.findDistinctByManufactureCost());
    }
}
