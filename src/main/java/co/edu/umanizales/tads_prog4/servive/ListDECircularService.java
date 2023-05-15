package co.edu.umanizales.tads_prog4.servive;

import co.edu.umanizales.tads_prog4.model.ListDE;
import co.edu.umanizales.tads_prog4.model.ListDECircular;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class ListDECircularService {

    private ListDECircular pets;

    public ListDECircularService(){

        pets = new ListDECircular();
    }
}
