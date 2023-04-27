package co.edu.umanizales.tads_prog4.dto;

import co.edu.umanizales.tads_prog4.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KidsByLocationDTO {

    private Location location;
    private int quantity;
}
