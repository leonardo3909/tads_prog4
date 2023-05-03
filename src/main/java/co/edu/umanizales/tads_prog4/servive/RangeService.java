package co.edu.umanizales.tads_prog4.servive;

import co.edu.umanizales.tads_prog4.model.Rangesk;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class RangeService {

    private List<Rangesk> ranges;

    public RangeService(){

        ranges = new ArrayList<>();
        ranges.add(new Rangesk(1,3));
        ranges.add(new Rangesk(4,6));
        ranges.add(new Rangesk(7,9));
        ranges.add(new Rangesk(10,12));
        ranges.add(new Rangesk(14,15));
    }
}
