package co.edu.umanizales.tads_prog4.servive;

import co.edu.umanizales.tads_prog4.model.ListSE;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ListSEService {

    private ListSE kids;

    public ListSEService() {
        kids = new ListSE();

    }
}
