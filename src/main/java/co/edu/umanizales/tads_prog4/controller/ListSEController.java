package co.edu.umanizales.tads_prog4.controller;

import co.edu.umanizales.tads_prog4.dto.KidDTO;
import co.edu.umanizales.tads_prog4.dto.KidsByLocationDTO;
import co.edu.umanizales.tads_prog4.dto.RangeAgeKidsDTO;
import co.edu.umanizales.tads_prog4.dto.ResponseDTO;
import co.edu.umanizales.tads_prog4.model.Kid;
import co.edu.umanizales.tads_prog4.model.Location;
import co.edu.umanizales.tads_prog4.model.Rangesk;
import co.edu.umanizales.tads_prog4.servive.ListSEService;
import co.edu.umanizales.tads_prog4.servive.LocationService;
import co.edu.umanizales.tads_prog4.servive.RangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/listse")
public class ListSEController {

    @Autowired
    private ListSEService listSEService;
    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getKids(){
        return new ResponseEntity<>(new ResponseDTO(
                200,listSEService.getKids().getHead(),null), HttpStatus.OK);
    }

    @GetMapping(path = "/invert")
    public ResponseEntity<ResponseDTO> invert() {
        listSEService.getKids().invert();
        return new ResponseEntity<>(new ResponseDTO(200,
                "The list has been inverted", null), HttpStatus.OK);
    }

    @GetMapping(path = "/change_extremes")
    public ResponseEntity<ResponseDTO> changeExtremes() {
        listSEService.getKids().changeExtremes();
        return new ResponseEntity<>(new ResponseDTO(
                200,"SE han intercambiado los extremos",
                null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addKid(@RequestBody KidDTO kidDTO){
        Location location = locationService.getLocationByCode(kidDTO.getCodeLocation());
        if(location == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La ubicación no existe",
                    null), HttpStatus.OK);
        }
        listSEService.getKids().add(
                new Kid(kidDTO.getIdentification(),
                        kidDTO.getName(), kidDTO.getAge(),
                        kidDTO.getGender(),location));
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se ha adicionado el petacón",
                null), HttpStatus.OK);

    }

    @GetMapping(path = "/kidsbylocations")
    public ResponseEntity<ResponseDTO> getKidsByLocation(){
        List<KidsByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
        for(Location loc: locationService.getLocations()){
            int count = listSEService.getKids().getCountKidsByLocationCode(loc.getCode());
            if(count>0){
                kidsByLocationDTOList.add(new KidsByLocationDTO(loc,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,kidsByLocationDTOList,
                null), HttpStatus.OK);
    }

    @GetMapping(path = "/kidsbydepto")
    public ResponseEntity<ResponseDTO>getCountKidsByDeptoCode(){
        List<KidsByLocationDTO> KidsByLocationDTOList1 = new ArrayList<>();
        for (Location loc: locationService.getLocations()){
            int count = listSEService.getKids().getCountKidsByDeptoCode(loc.getCode());
            if (count>0){
                KidsByLocationDTOList1.add(new KidsByLocationDTO(loc,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200,KidsByLocationDTOList1,null),HttpStatus.OK);
    }

    @GetMapping(path="/boysfirstgirlslast")
    public ResponseEntity<ResponseDTO> boyStartGirlsLast(){
        listSEService.getKids().boyStartGirlsLast();
        return new ResponseEntity<>(new ResponseDTO(200, "Los niños salen al inicio, las niñas al final",
                null), HttpStatus.OK);
    }

    @GetMapping(path="/boythengirl")
    public ResponseEntity<ResponseDTO> boyThenGirl(){
        listSEService.getKids().boyThenGirl();
        return new ResponseEntity<>(new ResponseDTO(200, "Los niños han sido alternados según su género",
                null), HttpStatus.OK);
    }

    @GetMapping(path = "/deletebyage/{age}")
    public ResponseEntity<ResponseDTO> deleteByAge(@PathVariable int age){
        listSEService.getKids().deleteByAge(age);
        return new ResponseEntity<>(new ResponseDTO(200, "Los niños han sido eliminados",
                null), HttpStatus.OK);
    }

    @GetMapping(path="/averageage")
    public ResponseEntity<ResponseDTO> averageAge(){
        return new ResponseEntity<>(new ResponseDTO(200,
                listSEService.getKids().averageAge(), null), HttpStatus.OK);
    }

    @GetMapping(path="/sendbottom/{initial}")
    public ResponseEntity<ResponseDTO> sendBottomByLetter(@PathVariable char initial){
        listSEService.getKids().sendBottomByLetter(Character.toUpperCase(initial));
        return new ResponseEntity<>(new ResponseDTO(200, "Los niños con esa letra se han enviado al final",
                null), HttpStatus.OK);
    }

    @GetMapping(path = "/Rangeage")
    public ResponseEntity<ResponseDTO> getRangeByKids(){
        List<RangeAgeKidsDTO> kidsRangeDTOList = new ArrayList<>();



        return new ResponseEntity<>(new ResponseDTO(200,kidsRangeDTOList,null),HttpStatus.OK);
    }

    @GetMapping(path="/forwardpositions")
    public ResponseEntity<ResponseDTO> forwardPositions(@PathVariable String identification, int positions){
        listSEService.getKids().forwardPositions(identification, positions);
        return new ResponseEntity<>(new ResponseDTO(200, "The kid has been moved to the position", null), HttpStatus.OK);
    }

    @GetMapping(path="/afterwardspositions")
    public ResponseEntity<ResponseDTO> afterwardsPositions(@PathVariable String identification, int positions){
        listSEService.getKids().afterwardsPositions(identification, positions);
        return new ResponseEntity<>(new ResponseDTO(200, "The kid has been moved to the position", null), HttpStatus.OK);
    }





}
