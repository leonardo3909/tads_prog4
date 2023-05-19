package co.edu.umanizales.tads_prog4.controller.dto;

import co.edu.umanizales.tads_prog4.model.Rangesk;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RangeAgeKidsDTO {

    private Rangesk range;
    int quantity;
}
