package irepository;

import model.Job;

public interface IJobRepository {
    public int insertJob(Job job);

    public void updateJob(Job job);

    public void deleteJob(int jobId);

    public Job getJobById(int jobId);
}
