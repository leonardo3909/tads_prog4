package co.edu.umanizales.tads_prog4.controller;

import co.edu.umanizales.tads_prog4.dto.*;
import co.edu.umanizales.tads_prog4.model.Kid;
import co.edu.umanizales.tads_prog4.model.Location;
import co.edu.umanizales.tads_prog4.model.Pet;
import co.edu.umanizales.tads_prog4.servive.ListDEService;
import co.edu.umanizales.tads_prog4.servive.ListSEService;
import co.edu.umanizales.tads_prog4.servive.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/Listde")
public class ListDEController {

    @Autowired
    private ListDEService listDEService;
    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getPets(){
        return new ResponseEntity<>(new ResponseDTO(
                200,listDEService.getPets().getHead(),null), HttpStatus.OK);
    }

    @GetMapping(path = "/invert")
    public ResponseEntity<ResponseDTO> invert() {
        listDEService.getPets().invert();
        return new ResponseEntity<>(new ResponseDTO(200,
                "The list has been inverted", null), HttpStatus.OK);
    }

    @GetMapping(path = "/change_extremes")
    public ResponseEntity<ResponseDTO> changeExtremes() {
        listDEService.getPets().changeExtremes();
        return new ResponseEntity<>(new ResponseDTO(
                200,"SE han intercambiado los extremos",
                null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addPet(@RequestBody PetDTO petDTO){
        Location location = locationService.getLocationByCode(petDTO.getCodeLocation());
        if(location == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La ubicación no existe",
                    null), HttpStatus.OK);
        }
        listDEService.getPets().add(
                new Pet(petDTO.getIdentification(), petDTO.getName(), petDTO.getAge(), petDTO.getGender(), petDTO.getCodeLocation(),location));
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se ha adicionado la mascota",
                null), HttpStatus.OK);

    }

    @GetMapping(path = "/kidsbylocations")
    public ResponseEntity<ResponseDTO> getPetsByLocation(){
        List<KidsByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
        for(Location loc: locationService.getLocations()){
            int count = listDEService.getPets().getCountPetsByLocationCode(loc.getCode());
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
            int count = listDEService.getPets().getCountPetsByDeptoCode(loc.getCode());
            if (count>0){
                KidsByLocationDTOList1.add(new KidsByLocationDTO(loc,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200,KidsByLocationDTOList1,null),HttpStatus.OK);
    }

    @GetMapping(path="/boysfirstgirlslast")
    public ResponseEntity<ResponseDTO> boyStartGirlsLast(){
        listDEService.getPets().boyStartGirlsLast();
        return new ResponseEntity<>(new ResponseDTO(200, "Los machos salen al inicio, las hembras al final",
                null), HttpStatus.OK);
    }

    @GetMapping(path="/boythengirl")
    public ResponseEntity<ResponseDTO> boyThenGirl(){
        listDEService.getPets().boyThenGirl();
        return new ResponseEntity<>(new ResponseDTO(200, "Las mascotas han sido alternados según su género",
                null), HttpStatus.OK);
    }

    @GetMapping(path = "/deletebyage/{age}")
    public ResponseEntity<ResponseDTO> deleteByAge(@PathVariable int age){
        listDEService.getPets().deleteByAge(age);
        return new ResponseEntity<>(new ResponseDTO(200, "Las mascotas han sido eliminados",
                null), HttpStatus.OK);
    }

    @GetMapping(path="/averageage")
    public ResponseEntity<ResponseDTO> averageAge(){
        return new ResponseEntity<>(new ResponseDTO(200,
                listDEService.getPets().averageAge(), null), HttpStatus.OK);
    }

    @GetMapping(path="/sendbottom/{initial}")
    public ResponseEntity<ResponseDTO> sendBottomByLetter(@PathVariable char initial){
        listDEService.getPets().sendBottomByLetter(Character.toUpperCase(initial));
        return new ResponseEntity<>(new ResponseDTO(200, "Las mascotas con esa letra se han enviado al final",
                null), HttpStatus.OK);
    }

    @GetMapping(path = "/Rangeage")
    public ResponseEntity<ResponseDTO> getRangeByKids(){
        List<RangeAgeKidsDTO> kidsRangeDTOList = new ArrayList<>();



        return new ResponseEntity<>(new ResponseDTO(200,kidsRangeDTOList,null),HttpStatus.OK);
    }
}
