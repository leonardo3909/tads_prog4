package co.edu.umanizales.tads_prog4.controller;

import co.edu.umanizales.tads_prog4.dto.*;
import co.edu.umanizales.tads_prog4.execption.ListDEExecpcion;
import co.edu.umanizales.tads_prog4.model.Kid;
import co.edu.umanizales.tads_prog4.model.Location;
import co.edu.umanizales.tads_prog4.model.Pet;
import co.edu.umanizales.tads_prog4.servive.ListDEService;
import co.edu.umanizales.tads_prog4.servive.ListSEService;
import co.edu.umanizales.tads_prog4.servive.LocationService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
        try {
            listDEService.getPets().invert();
            return new ResponseEntity<>(new ResponseDTO(200,
                    "The list has been inverted", null), HttpStatus.OK);
        }catch (ListDEExecpcion deExecpcion){
            return new ResponseEntity<>(new ResponseDTO(500,"Error al intentar invertir la lista",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path = "/change_extremes")
    public ResponseEntity<ResponseDTO> changeExtremes() {
        try {
            listDEService.getPets().changeExtremes();
            return new ResponseEntity<>(new ResponseDTO(
                    200,"SE han intercambiado los extremos",
                    null), HttpStatus.OK);
        }catch (ListDEExecpcion deExecpcion){
            return new ResponseEntity<>(new ResponseDTO(500,"no se pudo intercambiar los extremos",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addPet(@RequestBody PetDTO petDTO){
        try {
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
        }catch (ListDEExecpcion deExecpcion){
            return new ResponseEntity<>(new ResponseDTO(500,"no se pudo agregar a la lista la mascota",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping(path = "/petsbylocations")
    public ResponseEntity<ResponseDTO> getPetsByLocation(){
        try {
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
        }catch (ListDEExecpcion deExecpcion){
            return new ResponseEntity<>(new ResponseDTO(500,"no se pudo ubicar a la mascota",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path = "/petsbydepto")
    public ResponseEntity<ResponseDTO>getCountPetsByDeptoCode(){
        try {
            List<KidsByLocationDTO> KidsByLocationDTOList1 = new ArrayList<>();
            for (Location loc: locationService.getLocations()){
                int count = listDEService.getPets().getCountPetsByDeptoCode(loc.getCode());
                if (count>0){
                    KidsByLocationDTOList1.add(new KidsByLocationDTO(loc,count));
                }
            }
            return new ResponseEntity<>(new ResponseDTO(200,KidsByLocationDTOList1,null),HttpStatus.OK);
        }catch (ListDEExecpcion deExecpcion){
            return new ResponseEntity<>(new ResponseDTO(500,"no se pudo hacer la cuenta en la lista",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path="/boysfirstgirlslast")
    public ResponseEntity<ResponseDTO> boyStartGirlsLast(){
        try {
            listDEService.getPets().boyStartGirlsLast();
            return new ResponseEntity<>(new ResponseDTO(200, "Los machos salen al inicio, las hembras al final",
                    null), HttpStatus.OK);
        }catch (ListDEExecpcion deExecpcion){
            return new ResponseEntity<>(new ResponseDTO(500,"no se pudo organizar la lista segun lo indicado",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path="/boythengirl")
    public ResponseEntity<ResponseDTO> boyThenGirl(){
        try {
            listDEService.getPets().boyThenGirl();
            return new ResponseEntity<>(new ResponseDTO(200, "Las mascotas han sido alternados según su género",
                    null), HttpStatus.OK);
        }catch (ListDEExecpcion deExecpcion){
            return new ResponseEntity<>(new ResponseDTO(500,"error al intentar alternar la lista",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path = "/deletebyage/{age}")
    public ResponseEntity<ResponseDTO> deleteByAge(@PathVariable int age){
        try {
            listDEService.getPets().deleteByAge(age);
            return new ResponseEntity<>(new ResponseDTO(200, "Las mascotas han sido eliminados",
                    null), HttpStatus.OK);
        }catch (ListDEExecpcion deExecpcion){
            return new ResponseEntity<>(new ResponseDTO(500,"no se pudo eliminar la mascota",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path="/averageage")
    public ResponseEntity<ResponseDTO> averageAge(){
        try {
            return new ResponseEntity<>(new ResponseDTO(200,
                    listDEService.getPets().averageAge(), null), HttpStatus.OK);
        }catch (ListDEExecpcion deExecpcion){
            return new ResponseEntity<>(new ResponseDTO(500,"error al intentar organizar la lista segun lo indicado",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path="/sendbottom/{initial}")
    public ResponseEntity<ResponseDTO> sendBottomByLetter(@PathVariable char initial){
        try {
            listDEService.getPets().sendBottomByLetter(Character.toUpperCase(initial));
            return new ResponseEntity<>(new ResponseDTO(200, "Las mascotas con esa letra se han enviado al final",
                    null), HttpStatus.OK);
        }catch (ListDEExecpcion deExecpcion){
            return new ResponseEntity<>(new ResponseDTO(500,"no se pudo enviar la mascota con esa letra al final por un error",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path = "/Rangeage")
    public ResponseEntity<ResponseDTO> getRangeByKids(){
        List<RangeAgeKidsDTO> kidsRangeDTOList = new ArrayList<>();
        return new ResponseEntity<>(new ResponseDTO(200,kidsRangeDTOList,null),HttpStatus.OK);

    }

    @GetMapping(path = "deletebyid")
    public ResponseEntity<ResponseDTO>deleteById(@PathVariable String id){
        listDEService.getPets().deleteById(id);
        return new ResponseEntity<>(new ResponseDTO(200, "La mascota con esa identificacion han sido eliminada",
                null), HttpStatus.OK);
    }
}
