package mapper;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class EntityModelMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static <E, M> E toEntity(M model, Class<E> entityType) {
        return modelMapper.map(model, entityType);
    }

    public static <E, M> M toModel(E entity, Class<M> modelType) {
        return modelMapper.map(entity, modelType);
    }

    public static <E, M> List<M> ListToModel(List<E> entities, Class<M> modelsType) {
        List<M> models = new ArrayList<>(entities.size());
        for (E entity : entities)
            models.add(toModel(entity, modelsType));
        return models;
    }
}
