package mapper;

import entity.CarEntity;
import model.Car;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class CarEntityModelMapping {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static CarEntity toEntity(Car model) {
        return modelMapper.map(model, CarEntity.class);
    }

    public static Car toModel(CarEntity entity) {
        return modelMapper.map(entity, Car.class);
    }

    public static List<Car> ListToModel(List<CarEntity> entities) {
        List<Car> models = new ArrayList<>(entities.size());
        for (CarEntity entity : entities)
            models.add(toModel(entity));
        return models;
    }
}
