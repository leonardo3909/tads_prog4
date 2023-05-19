package co.edu.umanizales.tads_prog4.controller.dto;

import co.edu.umanizales.tads_prog4.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PetsByLocationDTO {

    private Location location;
    private int quantity;
}
