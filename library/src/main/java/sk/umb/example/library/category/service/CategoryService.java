package sk.umb.example.library.category.service;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import sk.umb.example.library.category.persistence.entity.CategoryEntity;
import sk.umb.example.library.category.persistence.repository.CategoryRepository;
import sk.umb.example.library.customer.persistence.entity.CustomerEntity;
import sk.umb.example.library.customer.persistence.repository.CustomerRepository;
import sk.umb.example.library.customer.service.CustomerDetailDTO;
import sk.umb.example.library.customer.service.CustomerRequestDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }



    public List<CategoryDetailDTO> getAllCategories() {
        return mapToDto(categoryRepository.findAll());
    }

    private void validateCategoryExists(Long categoryId) {
        if (! categoryRepository.existsById(categoryId)) {
            throw new IllegalArgumentException("CategoryId: " + categoryId + " does not exists!");
        }
    }
    public CategoryDetailDTO getCategorybyId(Long categoryId) {
        validateCategoryExists(categoryId);
        return mapToDto(categoryRepository.findById(categoryId).get());
    }
    public CategoryEntity getCategoryEntitybyId(Long categoryId) {
        validateCategoryExists(categoryId);
        return categoryRepository.findById(categoryId).get();
    }
    public Long createCategory(CategoryRequestDTO categoryRequestDTO) {
        return categoryRepository.save(mapToEntity(categoryRequestDTO)).getId();
    }

    private List<CategoryDetailDTO> mapToDto(List<CategoryEntity> categoryEntities) {
        List<CategoryDetailDTO> dtos = new ArrayList<>();

        for (CategoryEntity ce : categoryEntities) {
            CategoryDetailDTO dto = new CategoryDetailDTO();

            dto.setId(ce.getId());
            dto.setName(ce.getName());


            dtos.add(dto);
        }

        return dtos;
    }
    private CategoryDetailDTO mapToDto(CategoryEntity categoryEntity) {
        CategoryDetailDTO dto = new CategoryDetailDTO();

        dto.setId(categoryEntity.getId());
        dto.setName(categoryEntity.getName());

        return dto;
    }
    private CategoryEntity mapToEntity(CategoryRequestDTO dto) {
        CategoryEntity ce = new CategoryEntity();

        ce.setName(dto.getName());


        return ce;
    }

    public void updateCategory(Long categoryId, CategoryRequestDTO categoryRequestDTO) {
        validateCategoryExists(categoryId);

        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).get();

        if (! Strings.isEmpty(categoryRequestDTO.getName())) {
            categoryEntity.setName(categoryRequestDTO.getName());
        }

    }
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }


}
