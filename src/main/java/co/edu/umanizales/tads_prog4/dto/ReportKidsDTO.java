package co.edu.umanizales.tads_prog4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReportKidsDTO {

    private String city;
    private List<KidGenderDTO> genders;
    private int total;
}
