package com.bao.example.thuctap.impl;


import com.bao.example.thuctap.dto.CategoryDTO;
import com.bao.example.thuctap.dto.ResponseBase;
import com.bao.example.thuctap.model.Category;
import com.bao.example.thuctap.repositories.CategoryRepository;
import com.bao.example.thuctap.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl  implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseBase getpagination(int page, int size) {


        List<Category> categoryList =  categoryRepository.findWithPagination((page - 1) * size, size);

        return new ResponseBase(categoryList,categoryRepository.countTotalCategories());
    }



    @Override
    public List<CategoryDTO> getalldatabase() {

        List<CategoryDTO> categoryDTOS = new ArrayList<>();

        //sort with parentID
        List<Category> categoryList = categoryRepository.findAll();

        //tạo để lưu trữ con

        Map<Integer, CategoryDTO> categoryDTOMap = new HashMap<>();

        Map<Integer, List<CategoryDTO>> categoryParent = new HashMap<>();//int laf cha con lisst la con

        String parentID = null;


        for (Category category : categoryList){
            CategoryDTO categoryDTO =  modelMapper.map(category, CategoryDTO.class);


            categoryDTO.setCategoryDTOS(new ArrayList<>());

            categoryDTOMap.put(category.getId(), categoryDTO);

            if (category.getParentId() !=  null ){
                CategoryDTO categoryDTO1 =  categoryDTOMap.get(category.getParentId());
                if (categoryDTO1 != null) { //
                    categoryDTO1.getCategoryDTOS().add(categoryDTO);
                }
            }else {
                categoryDTOS.add(categoryDTO);
            }
        }
        return categoryDTOS;
    }



    @Override
    public List<CategoryDTO> getAllRecursive() {

        List<CategoryDTO> categoryDTOS = new ArrayList<>();

        List<Category> categories =  categoryRepository.findByParentIdIsFale();

        for (Category category :categories){
            List<Category> categoryList = categoryRepository.findByParentId(category.getId());

            CategoryDTO categoryDTO = new CategoryDTO();

            categoryDTO.setId(category.getId());
            categoryDTO.setDescription(category.getDescription());
            categoryDTO.setName(category.getName());
            categoryDTO.setStatus(category.getStatus());
            List<CategoryDTO>  categoryDTOList = new ArrayList<>();

            for (Category child : categoryList) {
                CategoryDTO childDTO = new CategoryDTO();
                childDTO.setId(child.getId());
                childDTO.setParentId(child.getParentId());
                childDTO.setStatus(child.getStatus());
                childDTO.setName(child.getName());
                childDTO.setDescription(child.getDescription());

                categoryDTOList.add(childDTO);
            }

            categoryDTO.setCategoryDTOS(categoryDTOList);
            categoryDTOS.add(categoryDTO);


        }

        return categoryDTOS;
    }




    @Override
    public List<CategoryDTO> getRecursive(String name) {


        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        List<Category> allCategories;
        //return list DTO
        if (!name.isEmpty()){
            allCategories = categoryRepository.findByName(name);
        }else {
            allCategories = categoryRepository.findAll();
        }
        for (Category category: allCategories){

            //return list DTO
            List<Category> childCategories = categoryRepository.findByParentId(category.getId());

            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(category.getId());
            categoryDTO.setDescription(category.getDescription());
            categoryDTO.setName(category.getName());
            categoryDTO.setStatus(category.getStatus());

               List<CategoryDTO>  categoryDTOList = new ArrayList<>();

                for (Category child : childCategories) {
                    CategoryDTO childDTO = new CategoryDTO();
                    childDTO.setId(child.getId());
                    childDTO.setParentId(child.getParentId());
                    childDTO.setStatus(child.getStatus());
                    childDTO.setName(child.getName());
                    childDTO.setDescription(child.getDescription());

                    categoryDTOList.add(childDTO);
                 }

                categoryDTO.setCategoryDTOS(categoryDTOList);
                categoryDTOS.add(categoryDTO);

        }


        return categoryDTOS;
    }




    //Đệ quy

    @Override
    public ResponseEntity<String> saveCategoryWithChildren(CategoryDTO categoryDTO) {


       Category category = new Category();
       category.setName(categoryDTO.getName());
       category.setStatus(true);
       category.setDescription(categoryDTO.getDescription());

       categoryRepository.save(category);


        List<Category> childCategories = new ArrayList<>();

        if (categoryDTO.getCategoryDTOS() != null && !categoryDTO.getCategoryDTOS() .isEmpty()) {
            for (CategoryDTO childDTO : categoryDTO.getCategoryDTOS()) {

                Category childEntity = new Category();
                childEntity.setParentId(category.getId());
                childEntity.setName(childDTO.getName());
                childEntity.setStatus(true);
                childEntity.setDescription(childDTO.getDescription());

                childCategories.add(childEntity);

            }
            categoryRepository.saveAll(childCategories);

        }

        return  ResponseEntity.ok("ok");
    }




    @Override
    public ResponseBase getCategory(String name, Pageable pageable) {
        Page<Category> page = categoryRepository.findAllByNameLike(name,pageable);

        return new ResponseBase(page.getContent(), page.getTotalElements());
    }

    @Override
    public ResponseBase getPaginatedData( int limit, int offset) {
//        Pageable adjustedPageable = PageRequest.of(1, 5);
        List<Category> categoryPage =categoryRepository.findByNameWithLimitAndOffset( limit, offset);

        return new ResponseBase(categoryPage ,categoryPage.spliterator().getExactSizeIfKnown());
    }




    @Override
    public List<Category> findAll(){
       return categoryRepository.findAtive();
    }


    @Override
    public List<Category> getsortByName(){
        return categoryRepository.getsortByName();
    }




    @Override
    public Optional<Category> findCategory(Integer id){
        return categoryRepository.findById(id);
    }

    @Override
    public  Category save(Category category){
        category.setStatus(true);
        return categoryRepository.save(category);

    }


    @Override
    public  Category update(Category category){
        return  categoryRepository.save(category);
    }




    @Override
    public Integer delete(Category category){
        category.setStatus(Boolean.FALSE);
        Category deletedCategory = categoryRepository.save(category);
        return  deletedCategory.getId();

    }

}
