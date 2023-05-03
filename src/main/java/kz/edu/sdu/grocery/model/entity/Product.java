package kz.edu.sdu.grocery.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;
    @Column(name = "category_id")
    private Long categoryId;
//    @ManyToOne
//    private Category category;
}
