package sk.umb.example.library.customer.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import sk.umb.example.library.customer.persistence.entity.CustomerEntity;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long>{
    
}
