package sk.umb.example.library.borrowing.service;

import org.springframework.stereotype.Service;
import sk.umb.example.library.book.service.BookDetailDTO;
import sk.umb.example.library.customer.service.CustomerDetailDTO;


import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BorrowingService {
    private final AtomicLong lastIndex = new AtomicLong(0);

    private final Map<Long, BorrowingDetailDTO> borrowingDatabase = new HashMap();

    public List<BorrowingDetailDTO> getAllBorrowings() {
        return new ArrayList<>(borrowingDatabase.values());
    }

    public BorrowingDetailDTO retrieveBorrowing(Long borrowingId) {
        validateBorrowingExists(borrowingId);

        return borrowingDatabase.get(borrowingId);
    }

    public Long createBorrowing(BorrowingRequestDTO borrowingRequestDTO, BookDetailDTO bookDetailDTO, CustomerDetailDTO customerDetailDTO) {
        BorrowingDetailDTO borrowingDetailDTO = mapToBorrowingDetailDTO(lastIndex.getAndIncrement(),
                borrowingRequestDTO, bookDetailDTO, customerDetailDTO);

        borrowingDatabase.put(borrowingDetailDTO.getId(), borrowingDetailDTO);

        return borrowingDetailDTO.getId();
    }

    private static BorrowingDetailDTO mapToBorrowingDetailDTO(Long index, BorrowingRequestDTO borrowingRequestDTO, BookDetailDTO bookDetailDTO, CustomerDetailDTO customerDetailDTO) {
        BorrowingDetailDTO dto = new BorrowingDetailDTO();

        dto.setId(index);
        dto.setBookDetailDTO(bookDetailDTO);
        dto.setCustomerDetailDTO(customerDetailDTO);

        return dto;
    }

    public void updateBorrowing(Long borrowingId, BorrowingRequestDTO borrowingRequestDTO,  BookDetailDTO bookDetailDTO, CustomerDetailDTO customerDetailDTO) {
        validateBorrowingExists(borrowingId);

        BorrowingDetailDTO borrowingDetailDTO = borrowingDatabase.get(borrowingId);

        if (borrowingRequestDTO.getBookId()!= null) {
            borrowingDetailDTO.setBookDetailDTO(bookDetailDTO);
        }

        if (borrowingRequestDTO.getCustomerId()!= null) {
            borrowingDetailDTO.setCustomerDetailDTO(customerDetailDTO);
        }
    }

    private void validateBorrowingExists(Long borrowingId) {
        if (! borrowingDatabase.containsKey(borrowingId)) {
            throw new IllegalArgumentException("CustomerId: " + borrowingId + " does not exist!");
        }
    }

    public void deleteBorrowing(Long borrowingId) {
        borrowingDatabase.remove(borrowingId);
    }
}
