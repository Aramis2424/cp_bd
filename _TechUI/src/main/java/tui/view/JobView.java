package tui.view;

import model.Job;

public class JobView extends View{
    public void printJob(int index, Job job) {
        System.out.printf("%d) %s - зарплата: %d\n",
                index, job.getTitle(), job.getSalary());
    }
}
