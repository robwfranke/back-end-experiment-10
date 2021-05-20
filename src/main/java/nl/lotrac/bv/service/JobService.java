package nl.lotrac.bv.service;

import nl.lotrac.bv.model.Job;

import java.util.List;

public interface JobService {

    String createNewJob(Job job);


    public abstract List<Job> getAllJobs();

    public abstract List<Job>getAllJobsByJob (String departmentname);


    public  abstract Job getOneJobByID(Long id);
}
