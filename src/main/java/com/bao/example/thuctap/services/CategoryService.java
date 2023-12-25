package com.bao.example.thuctap.services;

import com.bao.example.thuctap.dto.CategoryDTO;
import com.bao.example.thuctap.dto.ResponseBase;
import com.bao.example.thuctap.model.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryService {



    public List<Category> findAll();
    public List<Category> getsortByName();

    public ResponseBase getCategory(String name, Pageable pageable);

    public Optional<Category> findCategory(Integer id);

    public  Category save(Category category);

     public Integer  delete(Category category);

     public   Category update(Category category);
//     List<CategoryDTO> getrecursive(String name);

    List<CategoryDTO> getRecursive(String name);

    ResponseEntity<String> saveCategoryWithChildren(CategoryDTO categoryDTO);

    List<CategoryDTO> getAllRecursive();

    List<CategoryDTO> getalldatabase();

    ResponseBase getPaginatedData( int limit, int offset);

    ResponseBase getpagination(int page, int size);
}
