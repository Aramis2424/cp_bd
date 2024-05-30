package mapper;

import entity.TicketEntity;
import model.Ticket;

import org.modelmapper.ModelMapper;

public class TicketEntityModelMapping {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static TicketEntity toEntity(Ticket model) {
        return modelMapper.map(model, TicketEntity.class);
    }

    public static Ticket toModel(TicketEntity entity) {
        return modelMapper.map(entity, Ticket.class);
    }
}
