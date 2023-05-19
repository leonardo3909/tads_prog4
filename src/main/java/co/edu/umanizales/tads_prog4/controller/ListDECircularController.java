package co.edu.umanizales.tads_prog4.controller;

import co.edu.umanizales.tads_prog4.controller.dto.PetDTO;
import co.edu.umanizales.tads_prog4.controller.dto.ResponseDTO;
import co.edu.umanizales.tads_prog4.execption.ListDECircularExecpcion;
import co.edu.umanizales.tads_prog4.model.Location;
import co.edu.umanizales.tads_prog4.model.Pet;
import co.edu.umanizales.tads_prog4.servive.ListDECircularService;
import co.edu.umanizales.tads_prog4.servive.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/listdecircular")
public class ListDECircularController {

    @Autowired
    private ListDECircularService listDECircularService;

    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getPet(){
        return new ResponseEntity<>(new ResponseDTO(
                200,listDECircularService.getPets().getHead(),null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addPetCircular(@RequestBody PetDTO petDTO){
        try {
            Location location = locationService.getLocationByCode(petDTO.getCodeLocation());
            if(location == null){
                return new ResponseEntity<>(new ResponseDTO(
                        404,"La ubicación no existe",
                        null), HttpStatus.OK);
            }
            listDECircularService.getPets().addPetsCircular(
                    new Pet(petDTO.getIdentification(), petDTO.getName(), petDTO.getAge(), petDTO.getGender(), petDTO.getRaze(),location,true));
            return new ResponseEntity<>(new ResponseDTO(
                    200,"Se ha adicionado la mascota al final",
                    null), HttpStatus.OK);
        }catch (ListDECircularExecpcion deCircularExecpcion){
            return new ResponseEntity<>(new ResponseDTO(500,"no se pudo agregar a la lista la mascota",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @PostMapping(path = "addpetstart")
    public ResponseEntity<ResponseDTO> addPetStart(@RequestBody PetDTO petDTO){
        try {
            Location location = locationService.getLocationByCode(petDTO.getCodeLocation());
            if(location == null){
                return new ResponseEntity<>(new ResponseDTO(
                        404,"La ubicación no existe",
                        null), HttpStatus.OK);
            }
            listDECircularService.getPets().addPetsCircular(
                    new Pet(petDTO.getIdentification(), petDTO.getName(), petDTO.getAge(), petDTO.getGender(), petDTO.getRaze(),location,true));
            return new ResponseEntity<>(new ResponseDTO(
                    200,"Se ha adicionado la mascota",
                    null), HttpStatus.OK);
        }catch (ListDECircularExecpcion deCircularExecpcion){
            return new ResponseEntity<>(new ResponseDTO(500,"no se pudo agregar a la lista la mascota",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping(path = "/bathpets/{letter}" )
    public ResponseEntity<ResponseDTO> bathPets(@PathVariable char letter)  {
        int numero = 0;
        char letterLower = Character.toLowerCase(letter);

        try {
            numero= listDECircularService.getPets().bathPets(letter);
        } catch (ListDECircularExecpcion execpcion) {
            return new ResponseEntity<>(new ResponseDTO(
                    409,"no se pudo bañar a la mascota esta muy sucia.",
                    null), HttpStatus.OK);
        }
        if (numero ==1 ){
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Se ha bañado a la primera mascota ", null), HttpStatus.OK);
        } else {
            if (letterLower=='r'){
                return new ResponseEntity<>(new ResponseDTO(
                        200, "Se ha bañado la mascota #: " + numero +" iniciando de la cabeza a la derecha" , null), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(new ResponseDTO(
                        200, "Se ha bañado la mascota #:" + numero +" iniciando de la cabeza a la izquierda " , null), HttpStatus.OK);
            }

        }
    }

    @PostMapping(path = "/addposition/{position}")
    public ResponseEntity<ResponseDTO> addPosition(@RequestBody Pet pet, @PathVariable int position)  {
        try {
            listDECircularService.getPets().addPosition(position,pet);
        } catch (ListDECircularExecpcion execpcion) {
            return new ResponseEntity<>(new ResponseDTO(409, "no se pudo adicionar la mascota en la posicion deseada", null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(200, "la mascota fue adicionada en la posicion deseada  "+position , null),
                HttpStatus.OK);

    }
}
