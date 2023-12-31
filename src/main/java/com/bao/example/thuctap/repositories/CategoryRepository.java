package com.bao.example.thuctap.repositories;

import com.bao.example.thuctap.dto.CategoryDTO;
import com.bao.example.thuctap.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CategoryRepository  extends JpaRepository<Category, Integer> {




//    Page<Category> findAll(Pageable pageable) ;
//    Page<Category> findAllByNameLike(String name,Pageable pageable);
    @Query("SELECT c FROM Category c WHERE " +
            "(:name IS NULL OR c.name LIKE %:name%)")
    Page<Category> findAllByNameLike(@Param("name") String name, Pageable pageable);




    @Query("SELECT c FROM Category c WHERE c.status = 1 ")
    List<Category> findAtive();

    @Query(value = "SELECT  * FROM  tblcategory c where  c.status = 1", nativeQuery = true)
    List<Category> findAtive1();

    @Query("SELECT c FROM Category c ORDER BY c.name ASC, c.status DESC")// HQl
    List<Category> getsortByName();


    @Query(value = "SELECT * FROM  tblcategory c ORDER BY c.name ASC, c.status DESC", nativeQuery = true)
    List<Category> getSortByName();

    List<Category> findByName(String name);

    List<Category> findByParentId(Integer parentId);


    @Query(value = "SELECT c FROM Category c  where  c.parentId = null ")
    List<Category> findByParentIdIsFale();

    @Query(value = "SELECT * FROM tblcategory c  LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Category> findByNameWithLimitAndOffset(
            @Param("limit") int limit,
            @Param("offset") int offset
    );


    @Query(value = "SELECT * FROM tblcategory LIMIT ?2 OFFSET ?1", nativeQuery = true)
    List<Category> findWithPagination(int offset, int size);
    @Query(value = "SELECT COUNT(*) FROM tblcategory", nativeQuery = true)
    long countTotalCategories();
}
