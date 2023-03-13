package sk.umb.example.library.customer.service;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import sk.umb.example.library.customer.persistence.entity.CustomerEntity;
import sk.umb.example.library.customer.persistence.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDetailDTO> getAllCustomers() {
        return mapToDto(customerRepository.findAll());
    }


    public CustomerDetailDTO getCustomerById(Long customerId) {
        validateCustomerExists(customerId);
        return mapToDto(customerRepository.findById(customerId).get());
    }

    public Long createCustomer(CustomerRequestDTO customerRequestDTO) {
        return customerRepository.save(mapToEntity(customerRequestDTO)).getId();
    }

    private List<CustomerDetailDTO> mapToDto(List<CustomerEntity> customerEntities) {
        List<CustomerDetailDTO> dtos = new ArrayList<>();

        for (CustomerEntity ce : customerEntities) {
            CustomerDetailDTO dto = new CustomerDetailDTO();

            dto.setId(ce.getId());
            dto.setFirstName(ce.getFirstName());
            dto.setLastName(ce.getLastName());
            dto.setEmailContact(ce.getEmailContact());

            dtos.add(dto);
        }

        return dtos;
    }
    private CustomerDetailDTO mapToDto(CustomerEntity customerEntity) {
        CustomerDetailDTO dto = new CustomerDetailDTO();

        dto.setId(customerEntity.getId());
        dto.setFirstName(customerEntity.getFirstName());
        dto.setLastName(customerEntity.getLastName());
        dto.setEmailContact(customerEntity.getEmailContact());

        return dto;
    }

    private CustomerEntity mapToEntity(CustomerRequestDTO dto) {
        CustomerEntity ce = new CustomerEntity();

        ce.setFirstName(dto.getFirstName());
        ce.setLastName(dto.getLastName());
        ce.setEmailContact(dto.getEmailContact());

        return ce;
    }


    public void updateCustomer(Long customerId, CustomerRequestDTO customerRequestDTO) {
        validateCustomerExists(customerId);

        CustomerEntity customerEntity = customerRepository.findById(customerId).get();

        if (! Strings.isEmpty(customerRequestDTO.getFirstName())) {
            customerEntity.setFirstName(customerRequestDTO.getFirstName());
        }

        if (! Strings.isEmpty(customerRequestDTO.getLastName())) {
            customerEntity.setLastName(customerRequestDTO.getLastName());
        }

        if (! Strings.isEmpty(customerRequestDTO.getEmailContact())) {
            customerEntity.setEmailContact(customerRequestDTO.getEmailContact());
        }
        customerRepository.save(customerEntity);
    }

    private void validateCustomerExists(Long customerId) {
        if (! customerRepository.existsById(customerId)) {
            throw new IllegalArgumentException("CustomerId: " + customerId + " does not exists!");
        }
    }

    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }
}
