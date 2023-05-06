package kz.edu.sdu.grocery.model.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Orders_history")
public class Orders_history {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "modify_date")
    private Timestamp modify_date;

    @Column(name = "status_id")
    private Long statusId;
    @Column(name = "order_id")
//    @ManyToOne
    private Long orderId;
}
