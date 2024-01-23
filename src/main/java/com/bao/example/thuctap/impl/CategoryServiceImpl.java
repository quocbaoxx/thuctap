package com.bao.example.thuctap.impl;


import com.bao.example.thuctap.dto.CategoryDTO;
import com.bao.example.thuctap.dto.ResponseBase;
import com.bao.example.thuctap.model.Category;
import com.bao.example.thuctap.repositories.CategoryRepository;
import com.bao.example.thuctap.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
//        return  new ResponseBase(categoryList);
    }



//    @Override
//    public List<CategoryDTO> getalldatabase() {
//        List<CategoryDTO> categoryDTOS = new ArrayList<>();
//
//        // Sắp xếp theo parentID
//        List<Category> categoryList = categoryRepository.findAllSortParentId();
//
//        Map<Integer, List<CategoryDTO>> categoryParent = new HashMap<>();
//
//        List<CategoryDTO> categoryDTOList = new ArrayList<>();
//
//        for (Category category : categoryList) {
//            CategoryDTO categoryDTO = new CategoryDTO();
//            categoryDTO.setId(category.getId());
//            categoryDTO.setParentId(category.getParentId());
//            categoryDTO.setStatus(category.getStatus());
//            categoryDTO.setName(category.getName());
//            categoryDTO.setDescription(category.getDescription());
//
//                if (category.getParentId() == null) {
//                    categoryDTOList.add(categoryDTO);
//                } else {
////                        categoryParent.put(category.getParentId(), new ArrayList<>());
////                        categoryParent.get(category.getParentId()).add(categoryDTO);
//                    categoryParent.computeIfAbsent(category.getParentId(), k -> new ArrayList<>()).add(categoryDTO);
//
//                }
//            //        Gắn lại vào các cha tương ứng
////            for (CategoryDTO categoryDTOH : categoryDTOS) {
////                categoryDTOH.setCategoryDTOS(categoryParent.get(categoryDTO.getId()));
////                categoryDTOS.add((CategoryDTO) categoryDTOList);
////            }
//
//        }
//        for (CategoryDTO categoryDTO : categoryDTOList) {
//            int parentId = categoryDTO.getId();
//            if (categoryParent.containsKey(parentId)) {
//                categoryDTO.setCategoryDTOS(categoryParent.get(parentId));
//            }
//            categoryDTOS.add(categoryDTO);
//        }
//        return categoryDTOS;
//    }

    @Override
    public List<CategoryDTO> getalldatabase1for() {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        List<CategoryDTO> categoryList = categoryRepository.findAllSortParentId();

        Map<Integer, List<CategoryDTO>> categoryParent = new HashMap<>();

        List<CategoryDTO> temporaryList = new ArrayList<>();

        for (CategoryDTO category : categoryList) {
            CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);

            if (category.getParentId() == null) {
                temporaryList.add(categoryDTO);
                categoryDTO.setCategoryDTOS(categoryParent.computeIfAbsent(category.getId(), k -> new ArrayList<>()));
            } else {
                categoryParent.computeIfAbsent(category.getParentId(), k -> new ArrayList<>()).add(categoryDTO);
            }
        }

        categoryDTOS.addAll(temporaryList);
        return categoryDTOS;


    }




     @Override
    public List<CategoryDTO> getalldatabase() {//1 vòng for
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        List<CategoryDTO> categoryList = categoryRepository.findAllSortParentId();

        Map<Integer, List<CategoryDTO>> categoryParent = new HashMap<>();

        List<CategoryDTO> temporaryList = new ArrayList<>();

        for (CategoryDTO category : categoryList) {
            CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
            if (category.getParentId() == null) {
                temporaryList.add(categoryDTO);
            } else {
                if (!categoryParent.containsKey(category.getParentId())) {
                    categoryParent.put(category.getParentId(), new ArrayList<>());
                }//cần else
                else {
                    categoryParent.get(category.getParentId()).add(categoryDTO);
                }
//              categoryParent.computeIfAbsent(category.getParentId(), k -> new ArrayList<>()).add(categoryDTO);
            }
        }
        for (CategoryDTO categoryDTO : temporaryList) {
            categoryDTO.setCategoryDTOS(categoryParent.get(categoryDTO.getId()));
            categoryDTOS.add(categoryDTO);
        }
        return categoryDTOS;
    }




    @Override
    public List<CategoryDTO> getalldatabaseStream1() {
        List<CategoryDTO> categoryList = categoryRepository.findAllSortParentId();

        return categoryList.stream()
//                .filter(category -> category.getParentId() == null)// 1 map 1 for
                .map(category  -> {
                    CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
                    for(CategoryDTO e : categoryList){
                        if(e.getParentId() != null && e.getParentId().equals(category.getId())){
                            CategoryDTO categoryDTOChild = modelMapper.map(e, CategoryDTO.class);
                            categoryDTO.getCategoryDTOS().add(categoryDTOChild);
                        }
                    }
                    return categoryDTO;
                }
//                        mapCategoryToDTO(category , categoryList)
                )
                .collect(Collectors.toList());
    }


    //đệ quy gọi chính nó
    private CategoryDTO mapCategoryToDTO(Category category, List<Category> categoryList) {
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);

        //TODO: return DTO, bo map

        List<CategoryDTO> categoryDTOList = categoryList.stream()
                .filter(child -> child.getParentId() != null && category.getId().equals(child.getParentId()))
                .map(child -> mapCategoryToDTO(child,categoryList))
                .collect(Collectors.toList());

        categoryDTO.setCategoryDTOS(categoryDTOList);

        return categoryDTO;
    }




    @Override
    public List<CategoryDTO> getallrecursivestream() {

        return  categoryRepository.findByParentIdIsFale().stream()
                .map(category -> {
                    List<CategoryDTO> categoryDTOS = new ArrayList<>();

                    List<Category> categoryList = categoryRepository.findByParentId(category.getId());
                    CategoryDTO categoryDTO = modelMapper.map(category , CategoryDTO.class);

                    List<CategoryDTO>  categoryDTOList = new ArrayList<>();

                    for (Category child : categoryList) {
                        CategoryDTO childDTO = modelMapper.map(child, CategoryDTO.class);

                        categoryDTOList.add(childDTO);
                    }

                    categoryDTO.setCategoryDTOS(categoryDTOList);
                    categoryDTOS.add(categoryDTO);

                    return categoryDTO;
                }).collect(Collectors.toList());
    }



    @Override
    public List<CategoryDTO> getallrecursive() {

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





//    @Override
//    public List<CategoryDTO> getRecursive(String name) {
//
//
//        List<CategoryDTO> categoryDTOS = new ArrayList<>();
//        List<Category> allCategories;
//        //return list DTO
//        if (!name.isEmpty()){
//            allCategories = categoryRepository.findByName(name);
//        }else {
//            allCategories = categoryRepository.findAll();
//        }
//        for (Category category: categoryDTOS){
//
//            //return list DTO
//            List<CategoryDTO> childCategories = categoryRepository.findByParentIdDTO(category.getId());
//
//            CategoryDTO categoryDTO = new CategoryDTO(category.getId(),category.getDescription(),category.getStatus(),category.getName());
//
//               List<CategoryDTO>  categoryDTOList = new ArrayList<>();
//
//                for (CategoryDTO child : childCategories) {
//                    CategoryDTO childDTO = new CategoryDTO(child.getId(), child.getDescription(), child.getStatus(), child.getDescription(),child.getParentId());
//                    categoryDTOList.add(childDTO);
//                 }
//
//                categoryDTO.setCategoryDTOS(categoryDTOList);
//                categoryDTOS.add(categoryDTO);
//
//        }
//
//
//        return categoryDTOS;
//    }

    @Override
    public List<CategoryDTO> getRecursive(String name) {

        List<CategoryDTO> categoryDTOS ;
        if (!name.isEmpty()){
            categoryDTOS = categoryRepository.findByNameDTO(name);
        }else {
            categoryDTOS = categoryRepository.fineAllDTO();
        }

        List<CategoryDTO> updatedCategoryDTOS = new ArrayList<>();


        for (CategoryDTO categoryDTO: categoryDTOS){
            List<CategoryDTO> childCategories = categoryRepository.findByParentIdDTO(categoryDTO.getId());

            CategoryDTO mappedCategoryDTO = modelMapper.map(categoryDTO, CategoryDTO.class);

            List<CategoryDTO>  categoryDTOList = new ArrayList<>();

            for (CategoryDTO child : childCategories) {
                CategoryDTO childDTO = modelMapper.map(child, CategoryDTO.class);
                    categoryDTOList.add(childDTO);
            }
            mappedCategoryDTO.setCategoryDTOS(categoryDTOList);
            updatedCategoryDTOS.add(mappedCategoryDTO);

        }


        return updatedCategoryDTOS;
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
