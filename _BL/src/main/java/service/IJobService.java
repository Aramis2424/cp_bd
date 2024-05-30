package service;

import model.Job;

public interface IJobService {
    public int createJob(Job job);

    public void updateJob(Job job);

    public void removeJob(int id);

    public Job getJobById(int id);
}
