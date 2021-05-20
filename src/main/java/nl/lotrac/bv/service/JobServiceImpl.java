package nl.lotrac.bv.service;


import nl.lotrac.bv.exceptions.NameExistsException;
import nl.lotrac.bv.model.Job;
import nl.lotrac.bv.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {




    @Autowired
    private JobRepository jobRepository;

    @Override

    public List<Job>getAllJobs(){
        return jobRepository.findAll();
    }


    @Override
    public String createNewJob(Job job){
       if(jobRepository.getJobByJobname(job.getJobname()) != null)
           throw new NameExistsException("job exists");
       job.setJobname(job.getJobname());
       job.setDepartment(job.getDepartment());
       Job newJob = jobRepository.save(job);
       return (newJob.getJobname());
    }


}
