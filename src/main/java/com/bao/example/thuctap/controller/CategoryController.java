package com.bao.example.thuctap.controller;


import com.bao.example.thuctap.dto.CategoryDTO;
import com.bao.example.thuctap.dto.ResponseBase;
import com.bao.example.thuctap.dto.SaleOderDTO;
import com.bao.example.thuctap.model.Category;
import com.bao.example.thuctap.repositories.CategoryRepository;
import com.bao.example.thuctap.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;


    //save bậc 1 bậc 2 từ chính nó
    @PostMapping("/saverecursive")
    public ResponseEntity<String> saveCategoryWithChildren(@RequestBody CategoryDTO categoryDTO){
        return  categoryService.saveCategoryWithChildren(categoryDTO);
    }
    //get bậc 1 bậc 2 từ chính nó theo tên
    @GetMapping("/getrecursive/{name}")
    public ResponseEntity<List<CategoryDTO>> getrecursive(@PathVariable String name){
        List<CategoryDTO> categoryList = categoryService.getRecursive(name);

        return  ResponseEntity.ok(categoryList);
    }


//    Lấy all catergory theo dạng cha con
    @GetMapping("/getallrecursive")
    public ResponseEntity<List<CategoryDTO>> getAllRecursive(){
        List<CategoryDTO> categoryList = categoryService.getallrecursive();
        return  ResponseEntity.ok(categoryList);
    }

    @GetMapping("/getalldatabase")
    public ResponseEntity<List<CategoryDTO>> getalldatabase(){
        List<CategoryDTO> categoryList = categoryService.getalldatabase();
        return  ResponseEntity.ok(categoryList);
    }



    //Phân trang sql lấy từ 6 - 10
    @GetMapping("/getPaginatedData")
    public ResponseBase getCategory(
            @RequestParam(defaultValue = "5") int limit,
            @RequestParam(defaultValue = "5") int offset
    ) {
        return categoryService.getPaginatedData( limit, offset);
    }



    //Phân trang không dùng jpa
    @GetMapping("/getpagination")
    public  Object getpagination(  @RequestParam("page") int page,
                                   @RequestParam("size") int size){

        return  categoryService.getpagination(page,size);
    }




    //   Phân trang
    @GetMapping("/pagination")
    public Object showCategory(String name,
                                 @RequestParam("page") int page,
                                 @RequestParam("limit") int limit){

        Pageable pageable = PageRequest.of(page-1, limit, Sort.by("name").descending());

        return categoryService.getCategory(name,pageable);

    }

    @GetMapping
    public ResponseEntity<List<Category>> getAll(){
       return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/name")
    public ResponseEntity<List<Category>> getsortByName(){
        return ResponseEntity.ok(categoryService.getsortByName());
    }



    @GetMapping("/{id}")
    public  ResponseEntity<Category> getCategory(@PathVariable("id") Integer id){

        Optional<Category> category = categoryService.findCategory(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping
    public ResponseEntity<Category> save(@RequestBody Category category){
        return  ResponseEntity.ok(category);
    }

    @PutMapping
    public  ResponseEntity<Category> update(@RequestBody Category category){
        return  ResponseEntity.ok(categoryService.update(category));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id){
        Category categoryToDelete = categoryRepository.findById(id).orElseThrow();
        categoryService.delete(categoryToDelete);

       return new  ResponseEntity<>(HttpStatus.OK);
    }

























}
