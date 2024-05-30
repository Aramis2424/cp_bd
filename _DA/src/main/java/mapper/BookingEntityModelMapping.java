package mapper;

import entity.BookingEntity;
import model.Booking;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class BookingEntityModelMapping {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static BookingEntity toEntity(Booking model) {
        return modelMapper.map(model, BookingEntity.class);
    }

    public static Booking toModel(BookingEntity entity) {
        return modelMapper.map(entity, Booking.class);
    }

    public static List<Booking> ListToModel(List<BookingEntity> entities) {
        List<Booking> models = new ArrayList<>(entities.size());
        for (BookingEntity entity : entities)
            models.add(toModel(entity));
        return models;
    }
}
