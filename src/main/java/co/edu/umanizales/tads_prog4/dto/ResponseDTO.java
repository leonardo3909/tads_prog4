package co.edu.umanizales.tads_prog4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResponseDTO {

    private int code;
    private Object data;
    private List<ErrrorDTO> errors;
}
