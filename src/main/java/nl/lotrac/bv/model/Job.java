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
@Table(name = "jobs")
public class Job {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String jobname;

    @Column
    private String department;



//    public  job(){
//
//        this.jobname=getJobname();
//        this.department=getDepartment();
//    }


//
////    @ManyToMany(mappedBy = "jobs")
    @JsonIgnore
    @ManyToMany(mappedBy = "jobsFromOrderline")
//
    private List<OrderLine> orderlines;

}
