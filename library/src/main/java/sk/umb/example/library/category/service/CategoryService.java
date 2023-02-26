package sk.umb.example.library.category.service;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
@Service
public class CategoryService {
    private final AtomicLong lastIndex = new AtomicLong(0);
    private final Map<Long, CategoryDetailDTO> categoryDatabase = new HashMap();
    public List<CategoryDetailDTO> getAllCategories() {
        return new ArrayList<>(categoryDatabase.values());
    }
    public List<CategoryDetailDTO> searchCategoryByName(String lastName) {
        return categoryDatabase.values().stream()
                .filter(dto -> lastName.equals(dto.getName()))
                .toList();
    }
    private void validateCategoryExists(Long categoryId) {
        if (! categoryDatabase.containsKey(categoryId)) {
            throw new IllegalArgumentException("CategoryID: " + categoryId + " does not exists!");
        }
    }
    public CategoryDetailDTO getCategorybyId(Long categoryId) {
        validateCategoryExists(categoryId);

        return categoryDatabase.get(categoryId);
    }
    public Long createCategory(CategoryRequestDTO categoryRequestDTO) {
        CategoryDetailDTO categoryDetailDTO = mapToCategoryDetailDTO(lastIndex.getAndIncrement(),
                categoryRequestDTO);

        categoryDatabase.put(categoryDetailDTO.getId(), categoryDetailDTO);

        return categoryDetailDTO.getId();
    }
    private static CategoryDetailDTO mapToCategoryDetailDTO(Long index, CategoryRequestDTO categoryRequestDTO) {
        CategoryDetailDTO dto = new CategoryDetailDTO();

        dto.setId(index);
        dto.setName(categoryRequestDTO.getName());
        return dto;
    }
    public void updateCategory(Long categoryId, CategoryRequestDTO categoryRequestDTO) {
        validateCategoryExists(categoryId);

        CategoryDetailDTO categoryDetailDTO = categoryDatabase.get(categoryId);

        if (! Strings.isEmpty(categoryRequestDTO.getName())) {
            categoryDetailDTO.setName(categoryRequestDTO.getName());
        }

    }
    public void deleteCategory(Long categoryId) {
        categoryDatabase.remove(categoryId);
    }

    public CategoryDetailDTO retrieveCategory(Long categoryId) {
        validateCategoryExists(categoryId);

        return categoryDatabase.get(categoryId);    }
}
