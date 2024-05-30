package service;

import model.Job;
import irepository.IJobRepository;

public class JobService implements IJobService {
    private IJobRepository jobRep;

    public JobService(IJobRepository repository) {
        jobRep = repository;
    }

    @Override
    public int createJob(Job job) {
        int id = jobRep.insertJob(job);
        job.setId(id);
        return id;
    }

    @Override
    public void updateJob(Job job) {
        jobRep.updateJob(job);
    }

    @Override
    public void removeJob(int id) {
        jobRep.deleteJob(id);
    }

    @Override
    public Job getJobById(int id) {
        return jobRep.getJobById(id);
    }
}
