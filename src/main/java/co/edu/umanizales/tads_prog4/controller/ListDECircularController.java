package co.edu.umanizales.tads_prog4.controller;

import co.edu.umanizales.tads_prog4.servive.ListDECircularService;
import co.edu.umanizales.tads_prog4.servive.ListDEService;
import co.edu.umanizales.tads_prog4.servive.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/listdecircular")
public class ListDECircularController {

    @Autowired
    private ListDECircularService listDECircularService;

    @Autowired
    private LocationService locationService;
}
