package sk.umb.example.library.borrowing.service;

import org.springframework.stereotype.Service;
import sk.umb.example.library.book.pesistence.entity.BookEntity;
import sk.umb.example.library.book.service.BookDetailDTO;
import sk.umb.example.library.borrowing.persistence.entity.BorrowingEntity;
import sk.umb.example.library.borrowing.persistence.repository.BorrowingRepository;
import sk.umb.example.library.customer.persistence.entity.CustomerEntity;
import sk.umb.example.library.customer.persistence.repository.CustomerRepository;
import sk.umb.example.library.customer.service.CustomerDetailDTO;
import sk.umb.example.library.customer.service.CustomerRequestDTO;


import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BorrowingService {
    private final BorrowingRepository borrowingRepository;

    public BorrowingService(BorrowingRepository borrowingRepository) {
        this.borrowingRepository = borrowingRepository;
    }

    public List<BorrowingDetailDTO> getAllBorrowings() {
        return mapToDto(borrowingRepository.findAll());
    }

    public BorrowingDetailDTO retrieveBorrowing(Long borrowingId) {
        validateBorrowingExists(borrowingId);

        return mapToDto(borrowingRepository.findById(borrowingId).get());
    }

    public Long createBorrowing(BorrowingRequestDTO borrowingRequestDTO, BookDetailDTO bookDetailDTO, CustomerDetailDTO customerDetailDTO) {
        return borrowingRepository.save(mapToEntity(borrowingRequestDTO, bookDetailDTO, customerDetailDTO)).getId();
    }

    private List<BorrowingDetailDTO> mapToDto(List<BorrowingEntity> borrowingEntities) {
        List<BorrowingDetailDTO> dtos = new ArrayList<>();

        for (BorrowingEntity be : borrowingEntities) {
            BorrowingDetailDTO dto = new BorrowingDetailDTO();

            dto.setId(be.getId());
            dto.setCustomerDetailDTO(customerEntityToDto(be.getCustomer()));
            dto.setBookDetailDTO(bookEntityToDto(be.getBookEntity()));

            dtos.add(dto);
        }

        return dtos;
    }
    private BorrowingDetailDTO mapToDto(BorrowingEntity borrowingEntity) {
        BorrowingDetailDTO dto = new BorrowingDetailDTO();

        dto.setId(borrowingEntity.getId());
        dto.setCustomerDetailDTO(customerEntityToDto(borrowingEntity.getCustomer()));
        dto.setBookDetailDTO(bookEntityToDto(borrowingEntity.getBookEntity()));

        return dto;
    }
    private CustomerDetailDTO customerEntityToDto(CustomerEntity customerEntity){
        CustomerDetailDTO dto = new CustomerDetailDTO();

        dto.setId(customerEntity.getId());
        dto.setFirstName(customerEntity.getFirstName());
        dto.setLastName(customerEntity.getLastName());
        dto.setEmailContact(customerEntity.getEmailContact());

        return dto;
    }
    private BookDetailDTO bookEntityToDto(BookEntity bookEntity){
        BookDetailDTO dto = new BookDetailDTO();

        dto.setBookId(bookEntity.getBookId());
        dto.setAuthorFirstName(bookEntity.getAuthorFirstName());
        dto.setAuthorLastName(bookEntity.getAuthorLastName());
        dto.setTitle(bookEntity.getTitle());
        dto.setCount(bookEntity.getCount());
        dto.setIsbn(bookEntity.getIsbn());
        dto.setCategoryIds(bookEntity.getCategoryIds());

        return dto;
    }

    private CustomerEntity customerDtoToEntity(CustomerDetailDTO customerDetailDTO){
        CustomerEntity ce = new CustomerEntity();

        ce.setId(customerDetailDTO.getId());
        ce.setFirstName(customerDetailDTO.getFirstName());
        ce.setLastName(customerDetailDTO.getLastName());
        ce.setEmailContact(customerDetailDTO.getEmailContact());

        return ce;
    }

    private BookEntity bookDtoToEntity(BookDetailDTO bookDetailDTO){
        BookEntity be = new BookEntity();

        be.setBookId(bookDetailDTO.getBookId());
        be.setAuthorFirstName(bookDetailDTO.getAuthorFirstName());
        be.setAuthorLastName(bookDetailDTO.getAuthorLastName());
        be.setTitle(bookDetailDTO.getTitle());
        be.setCount(bookDetailDTO.getCount());
        be.setIsbn(bookDetailDTO.getIsbn());
        be.setCategoryIds(bookDetailDTO.getCategoryIds());

        return be;
    }

    private BorrowingEntity mapToEntity(BorrowingRequestDTO dto, BookDetailDTO bdto, CustomerDetailDTO cdto) {
        BorrowingEntity be = new BorrowingEntity();

        be.setCustomer(customerDtoToEntity(cdto));
        be.setBookEntity(bookDtoToEntity(bdto));


        return be;
    }

    public void updateBorrowing(Long borrowingId, BorrowingRequestDTO borrowingRequestDTO,  BookDetailDTO bookDetailDTO, CustomerDetailDTO customerDetailDTO) {
        validateBorrowingExists(borrowingId);

        BorrowingEntity borrowingEntity = borrowingRepository.findById(borrowingId).get();

        if (borrowingRequestDTO.getBookId()!= null) {
            borrowingEntity.setBookEntity(bookDtoToEntity(bookDetailDTO));
        }

        if (borrowingRequestDTO.getCustomerId()!= null) {
            borrowingEntity.setCustomer(customerDtoToEntity(customerDetailDTO));
        }
        borrowingRepository.save(borrowingEntity);
    }

    private void validateBorrowingExists(Long borrowingId) {
        if (! borrowingRepository.existsById(borrowingId)) {
            throw new IllegalArgumentException("CustomerId: " + borrowingId + " does not exist!");
        }
    }

    public void deleteBorrowing(Long borrowingId) {
        borrowingRepository.deleteById(borrowingId);
    }
}
