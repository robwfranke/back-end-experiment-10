package nl.lotrac.bv.repository;

import nl.lotrac.bv.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository <Job, Long >{

Job getJobByJobname (String jobname);




}
