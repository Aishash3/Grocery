package kz.edu.sdu.grocery.repository;

import kz.edu.sdu.grocery.model.entity.Category;
import kz.edu.sdu.grocery.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getCategoryByName(String categoryName);
    @Query(nativeQuery = true, value = "SELECT MAX(ID) FROM CATEGORY")
    Long getMaxId();

}
