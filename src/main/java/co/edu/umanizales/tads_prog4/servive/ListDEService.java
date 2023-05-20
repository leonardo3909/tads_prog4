package co.edu.umanizales.tads_prog4.servive;

import co.edu.umanizales.tads_prog4.execption.ListDEExecpcion;
import co.edu.umanizales.tads_prog4.model.ListDE;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ListDEService {

    private ListDE pets;

    public ListDEService(){

        pets = new ListDE();
    }

    public void invertPets() throws ListDEExecpcion {
        pets.invertPets();
    }

}
