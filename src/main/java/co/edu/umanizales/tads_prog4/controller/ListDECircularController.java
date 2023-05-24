package co.edu.umanizales.tads_prog4.controller;

import co.edu.umanizales.tads_prog4.controller.dto.PetDTO;
import co.edu.umanizales.tads_prog4.controller.dto.ResponseDTO;
import co.edu.umanizales.tads_prog4.execption.ListDEExecpcion;
import co.edu.umanizales.tads_prog4.model.Location;
import co.edu.umanizales.tads_prog4.model.Pet;
import co.edu.umanizales.tads_prog4.servive.ListDECircularService;
import co.edu.umanizales.tads_prog4.servive.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                200,listDECircularService.getPets().print(),null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addPetCircular(@RequestBody  PetDTO petDTO) {
        Location locationPets = locationService.getLocationByCode(petDTO.getCodeLocationPet());
        if (locationPets == null) {
            return new ResponseEntity<>(new ResponseDTO(
                    404, "La ubicación no existe ", null), HttpStatus.OK);
        } try {
            listDECircularService.getPets().addPetListCircular(
                    new Pet(petDTO.getIdentificationPet(),
                            petDTO.getGenderPet(), petDTO.getAgePet(),
                            petDTO.getNamePet(), locationPets,petDTO.isBath(), petDTO.getNumberOfTicks()));
        } catch (ListDEExecpcion e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409, "Ya existe una mascota con ese código", null
            ), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseDTO(
                200, "Se ha añadido.", null), HttpStatus.OK);
    }
    @GetMapping("/randomleftright/{side}")
    public ResponseEntity<ResponseDTO> random(@PathVariable String side) {
        List<Pet> pets = listDECircularService.getPets().print();

        if (pets.isEmpty()) {
            return new ResponseEntity<>(new ResponseDTO(
                    200, "No se encontraron mascotas",
                    null), HttpStatus.OK);
        } else {
            int randomPosition = listDECircularService.getPets().bathPet(side);
            if (randomPosition == -1) {
                return new ResponseEntity<>(new ResponseDTO(
                        200, "No se pudo bañar ningún perro",
                        null), HttpStatus.OK);
            } else if (randomPosition == 0) {
                return new ResponseEntity<>(new ResponseDTO(
                        200, "El perro ya fue bañado en esa dirección" + randomPosition,
                        null), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ResponseDTO(
                        200, "Se ha bañado el perro número " + randomPosition + " en la dirección " + side,
                        null), HttpStatus.OK);
            }
        }
    }

    @PostMapping(path = "/addtostart")
    public ResponseEntity<ResponseDTO> addPetToStart(@RequestBody PetDTO petDTO) {
        try {
            Location locationPets = locationService.getLocationByCode(petDTO.getCodeLocationPet());
            if (locationPets == null) {
                return new ResponseEntity<>(new ResponseDTO(
                        404, "La ubicación no existe",
                        null), HttpStatus.OK);
            }
            listDECircularService.getPets().addPetToStart(
                    new Pet(petDTO.getIdentificationPet(),
                            petDTO.getGenderPet(), petDTO.getAgePet(),
                            petDTO.getNamePet(), locationPets, petDTO.isBath(), petDTO.getNumberOfTicks()));
            return new ResponseEntity<>(new ResponseDTO(200, "Mascota adicionada al inicio", null),
                    HttpStatus.OK);
        } catch (ListDEExecpcion e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409, e.getMessage(),
                    null), HttpStatus.OK);
        }
    }

    @PostMapping(path = "/addtofinal")
    public ResponseEntity<ResponseDTO> addPetToFinal(@RequestBody  PetDTO petDTO) {
        try {
            Location locationPets = locationService.getLocationByCode(petDTO.getCodeLocationPet());
            if (locationPets == null) {
                return new ResponseEntity<>(new ResponseDTO(
                        404, "La ubicación no existe",
                        null), HttpStatus.OK);
            }
            listDECircularService.getPets().addPetToEnd(
                    new Pet(petDTO.getIdentificationPet(),
                            petDTO.getGenderPet(), petDTO.getAgePet(),
                            petDTO.getNamePet(), locationPets, petDTO.isBath(), petDTO.getNumberOfTicks()));
            return new ResponseEntity<>(new ResponseDTO(200, "Mascota adicionada al final", null),
                    HttpStatus.OK);
        } catch (ListDEExecpcion e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409, e.getMessage(),
                    null), HttpStatus.OK);
        }
    }

    @PostMapping(path = "/addpetposition/{position}")
    public ResponseEntity<ResponseDTO> addPetInPosition(@RequestBody Pet pet, @PathVariable int position) {
        try {
            listDECircularService.getPets().addPetInPosition(position, pet);
            return new ResponseEntity<>(new ResponseDTO(200, "Mascota adicionada en la posición " + position, null),
                    HttpStatus.OK);
        } catch (ListDEExecpcion e) {
            return new ResponseEntity<>(new ResponseDTO(500, "Ocurrió un error al añadir por posición en la lista", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
