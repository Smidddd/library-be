package sk.umb.example.library.customer.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import sk.umb.example.library.customer.persistence.entity.CustomerEntity;

import java.util.List;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long>{
    @Override
    List<CustomerEntity> findAll();
}
