package repository;

import irepository.IJobRepository;
import model.Job;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JobRepositoryTest {
    private IJobRepository jobRepository;

    @BeforeEach
    void setUp() {
        jobRepository = new JobRepository();
    }

    @Test
    void insertJob() {
        Job job = Job.createJobModel("Tester", 1000);
        int id = jobRepository.insertJob(job);

        Job newJob = jobRepository.getJobById(id);
        assertEquals(1000, newJob.getSalary());
        assertEquals("Tester", newJob.getTitle());
    }

    @Test
    void updateJob() {
        Job job = Job.createJobModel("Tester", 1000);
        int id = jobRepository.insertJob(job);

        Job newJob = jobRepository.getJobById(id);
        newJob.setSalary(999);
        jobRepository.updateJob(newJob);

        newJob = jobRepository.getJobById(id);
        assertEquals(999, newJob.getSalary());
        assertEquals("Tester", newJob.getTitle());
    }

    @Test
    void deleteJob() {
        Job job = Job.createJobModel("Deleter", 1000);
        int id = jobRepository.insertJob(job);

        jobRepository.deleteJob(id);

        Job deletedJob = jobRepository.getJobById(id);
        assertNull(deletedJob);
    }

    @Test
    void getJobByIdExisting() {
        Job job = Job.createJobModel("Updater", 1001);
        int id = jobRepository.insertJob(job);

        Job newJob = jobRepository.getJobById(id);
        assertEquals(1001, newJob.getSalary());
        assertEquals("Updater", newJob.getTitle());
    }

    @Test
    void getJobByIdNotExisting() {
        Job job = jobRepository.getJobById(-1);
        assertNull(job);
    }
}