package kz.edu.sdu.grocery.model.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Status")
public class Status {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
}
