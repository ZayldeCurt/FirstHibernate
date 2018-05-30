package sda.pl.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table (name = "compleint")
public class OrderComplaint implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String message;
    @Enumerated
    ComplainStatus complainStatus;

    @ManyToMany(mappedBy = "orderComplaintSet",cascade = CascadeType.ALL)
    Set<Order> orderSet;

}
