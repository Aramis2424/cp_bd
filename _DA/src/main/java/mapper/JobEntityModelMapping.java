package mapper;

import entity.JobEntity;
import model.Job;

import org.modelmapper.ModelMapper;

public class JobEntityModelMapping {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static JobEntity toEntity(Job model) {
        return modelMapper.map(model, JobEntity.class);
    }

    public static Job toModel(JobEntity entity) {
        return modelMapper.map(entity, Job.class);
    }
}
