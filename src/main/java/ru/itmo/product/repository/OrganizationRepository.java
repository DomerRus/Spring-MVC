package ru.itmo.product.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itmo.product.model.Organization;
import ru.itmo.product.model.enums.OrganizationType;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepository extends CrudRepository<Organization, Integer> {

    Optional<Organization> findByNameAndFullNameAndAndEmployeesCountAndType(String name,
                                                                            String fullName,
                                                                            Long emplCount,
                                                                            OrganizationType organizationType);
}
