package mapper;

import entity.ParkingEntity;
import model.Parking;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ParkingEntityModelMapping {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static ParkingEntity toEntity(Parking model) {
        return modelMapper.map(model, ParkingEntity.class);
    }

    public static Parking toModel(ParkingEntity entity) {
        return modelMapper.map(entity, Parking.class);
    }

    public static List<Parking> ListToModel(List<ParkingEntity> entities) {
        List<Parking> models = new ArrayList<>(entities.size());
        for (ParkingEntity entity : entities)
            models.add(toModel(entity));
        return models;
    }
}
