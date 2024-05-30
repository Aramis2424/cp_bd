package tui.controller;

import model.Job;
import repository.JobRepository;
import service.JobService;
import tui.view.JobView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JobController {
    private JobView jobView = new JobView();
    private JobRepository jobRepository = new JobRepository();
    private JobService jobService = new JobService(jobRepository);

    public Job getJobById(int id) {
        return jobService.getJobById(id);
    }

    public String getJobTitle(int id) {
        return getJobById(id).getTitle();
    }

    public Map<Integer, Integer> makeChoiceJobs() {
        List<Job> jobs = new ArrayList<>(3);
        jobs.add(jobService.getJobById(1));
        jobs.add(jobService.getJobById(2));
        jobs.add(jobService.getJobById(3));

        Map<Integer, Integer> indexToId = new HashMap<>();
        int i = 1;
        for (Job job : jobs) {
            jobView.printJob(i, job);
            indexToId.put(i, job.getId());
            i++;
        }
        return indexToId;
    }
}
