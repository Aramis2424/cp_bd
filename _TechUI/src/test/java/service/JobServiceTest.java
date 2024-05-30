package service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import model.Job;
import irepository.IJobRepository;

@ExtendWith(MockitoExtension.class)
public class JobServiceTest {
    @Mock
    private IJobRepository mockRep;

    @InjectMocks
    private JobService service;

    @Test
    public void createJob() {
        Job newJob = Job.createJobModel("Manager", 50000);

        Mockito.when(mockRep.insertJob(newJob)).thenReturn(1);

        service.createJob(newJob);

        Mockito.verify(mockRep).insertJob(newJob);
        assertEquals(1, newJob.getId());
    }

    @Test
    public void updateJob() {
        Job updatingJob = new Job(1, "Guard", 90000);
        Mockito.doNothing().when(mockRep).updateJob(updatingJob);

        service.updateJob(updatingJob);

        Mockito.verify(mockRep).updateJob(updatingJob);
    }

    @Test
    public void removeJob() {
        int removingJobId = 1;
        Mockito.doNothing().when(mockRep).deleteJob(removingJobId);

        service.removeJob(removingJobId);

        Mockito.verify(mockRep).deleteJob(removingJobId);
    }

    @Test
    public void getJobById() {
        Job expected = new Job(1, "Guard", 90000);
        Mockito.when(mockRep.getJobById(1)).thenReturn(expected);

        Job actual = service.getJobById(1);

        assertEquals(expected, actual);
    }
}
