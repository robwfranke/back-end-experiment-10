package nl.lotrac.bv.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString


@Entity
@Table(name = "order_lines")
public class OrderLine {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String itemname;

    @Column
    private Integer quantity;



    //gedeelte voor order
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_order")
    private Order order;
//


    //    gedeelte voor job
    @ManyToMany
    @JoinTable(name = "orderline_job",
            joinColumns = {@JoinColumn(name = "fk_orderLine")},
            inverseJoinColumns =
                    {@JoinColumn(name = "fk_job")})

    private List<Job>jobs;


}
