package mapper;

import entity.AutoOwnerEntity;
import model.AutoOwner;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class AutoOwnerEntityModelMapping {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static AutoOwnerEntity toEntity(AutoOwner model) {
        return modelMapper.map(model, AutoOwnerEntity.class);
    }

    public static AutoOwner toModel(AutoOwnerEntity entity) {
        return modelMapper.map(entity, AutoOwner.class);
    }

    public static List<AutoOwner> ListToModel(List<AutoOwnerEntity> entities) {
        List<AutoOwner> models = new ArrayList<>(entities.size());
        for (AutoOwnerEntity entity : entities)
            models.add(toModel(entity));
        return models;
    }
}
