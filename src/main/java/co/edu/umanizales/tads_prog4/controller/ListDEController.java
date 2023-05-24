package co.edu.umanizales.tads_prog4.controller;

import co.edu.umanizales.tads_prog4.controller.dto.PetDTO;
import co.edu.umanizales.tads_prog4.controller.dto.PetsByLocationDTO;
import co.edu.umanizales.tads_prog4.controller.dto.ResponseDTO;
import co.edu.umanizales.tads_prog4.execption.ListDEExecpcion;
import co.edu.umanizales.tads_prog4.model.Location;
import co.edu.umanizales.tads_prog4.model.Pet;
import co.edu.umanizales.tads_prog4.servive.ListDEService;
import co.edu.umanizales.tads_prog4.servive.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/listde")
public class ListDEController {

    @Autowired
    private ListDEService listDEService;
    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getPets(){
        return new ResponseEntity<>(new ResponseDTO(
                200,listDEService.getPets().toList(),null), HttpStatus.OK);
    }

    @GetMapping("/invertpets")
    public ResponseEntity<ResponseDTO> invertPets() {
        try {
            listDEService.getPets().invertPets();
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Se ha invertido la lista",
                    null), HttpStatus.OK);
        } catch (ListDEExecpcion execpcion) {
            return new ResponseEntity<>(new ResponseDTO(
                    500, "Error al invertir la lista",
                    null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/changeextremespets")
    public ResponseEntity<ResponseDTO> changeExtremesPets() {
        try {
            listDEService.getPets().changeExtremesPets();
            return new ResponseEntity<>(new ResponseDTO(
                    200,"SE han intercambiado los extremos",
                    null), HttpStatus.OK);
    }catch (ListDEExecpcion ExceptionDE){
            return new ResponseEntity<>(new ResponseDTO(500,"no se pudo intercambiar los extremos",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(path = "/addpets")
    public ResponseEntity<ResponseDTO> addPets(@RequestBody PetDTO petDTO){
        try {
            Location location = locationService.getLocationByCode(petDTO.getCodeLocationPet());
            if(location == null){
                return new ResponseEntity<>(new ResponseDTO(
                        404,"La ubicación no existe",
                        null), HttpStatus.OK);
            }
            listDEService.getPets().addPets(
                    new Pet(petDTO.getIdentificationPet(),
                            petDTO.getGenderPet(), petDTO.getAgePet(),
                            petDTO.getNamePet(),location,false));
            return new ResponseEntity<>(new ResponseDTO(
                    200,"Se ha adicionado la mascota",
                    null), HttpStatus.OK);
        }catch (ListDEExecpcion ExceptionDE){
            return new ResponseEntity<>(new ResponseDTO(500,"no se pudo agregar a la lista la mascota",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }




    @GetMapping(path = "/petsbylocations/{code}")
    public ResponseEntity<ResponseDTO> getCountPetsByLocationCode(@PathVariable String code){
        try {
            listDEService.getPets().getCountPetsByLocationCode(code);
            List<PetsByLocationDTO> PetsByLocationDTOList = new ArrayList<>();
            for(Location location: locationService.getLocations()){
                int count = listDEService.getPets().getCountPetsByLocationCode(location.getCode());
                if(count>0){
                    PetsByLocationDTOList.add(new PetsByLocationDTO(location,count));
                }
            }
            return new ResponseEntity<>(new ResponseDTO(
                    200,PetsByLocationDTOList,
                    null), HttpStatus.OK);
        }catch (ListDEExecpcion ExceptionDE){
            return new ResponseEntity<>(new ResponseDTO(500,"no se pudo ubicar a la mascota",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path = "/petsbydepto/{code}")
    public ResponseEntity<ResponseDTO>getCountPetsByDeptoCode(@PathVariable String code){
        try {
            listDEService.getPets().getCountPetsByDeptoCode(code);
            List<PetsByLocationDTO> PetsByLocationDTOList1 = new ArrayList<>();
            for (Location loc: locationService.getLocations()){
                int count = listDEService.getPets().getCountPetsByDeptoCode(loc.getCode());
                if (count>0){
                    PetsByLocationDTOList1.add(new PetsByLocationDTO(loc,count));
                }
            }
            return new ResponseEntity<>(new ResponseDTO(200,PetsByLocationDTOList1,null),HttpStatus.OK);
        }catch (ListDEExecpcion ExceptionDE){
            return new ResponseEntity<>(new ResponseDTO(500,"no se pudo ubicar a la mascota",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping(path="/insertmalefemale")
    public ResponseEntity<ResponseDTO> petMaleIntercalateFemale()  {
        try {
            listDEService.getPets().intercalatePetsGender();
            return new ResponseEntity<>(new ResponseDTO(200, "Las mascotas se han intercalado.",
                    null), HttpStatus.OK);
        } catch (ListDEExecpcion execpcion) {
            return new ResponseEntity<>(new ResponseDTO(
                    500, "Ocurrió un error al intercalar el género de las mascotas",
                    null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/deletebyage/{age}")
    public ResponseEntity<ResponseDTO> deleteByAge(@PathVariable int age){
        try {
            listDEService.getPets().deleteByAge(age);
            return new ResponseEntity<>(new ResponseDTO(200, "Las mascotas han sido eliminados",
                    null), HttpStatus.OK);
        }catch (ListDEExecpcion ExceptionDE){
            return new ResponseEntity<>(new ResponseDTO(500,"no se pudo eliminar la mascota",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path="/averageage")
    public ResponseEntity<ResponseDTO> averageAge(){
        try {
            return new ResponseEntity<>(new ResponseDTO(200,
                    listDEService.getPets().averageAge(), null), HttpStatus.OK);
        }catch (ListDEExecpcion ExceptionDE){
            return new ResponseEntity<>(new ResponseDTO(500,"error al intentar organizar la lista segun lo indicado",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path="/sendbottom/{initial}")
    public ResponseEntity<ResponseDTO> sendBottomByLetter(@PathVariable char initial){
        try {
            listDEService.getPets().sendBottomByLetter(Character.toUpperCase(initial));
            return new ResponseEntity<>(new ResponseDTO(200, "Las mascotas con esa letra se han enviado al final",
                    null), HttpStatus.OK);
        }catch (ListDEExecpcion ExceptionDE){
            return new ResponseEntity<>(new ResponseDTO(500,"no se pudo enviar la mascota con esa letra al final por un error",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path = "/deletepet/{code}")
    public ResponseEntity<ResponseDTO> deletePetByIdentification(@PathVariable String code)  {
        try {
            listDEService.deletePetByIdentification(code);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Las mascotas con el código idicado han sido eliminado.",
                    null), HttpStatus.OK);
        } catch (ListDEExecpcion e) {
            return new ResponseEntity<>(new ResponseDTO(
                    500, "Error al eliminar las mascotas.",
                    null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/passpositions/{codepet}/{move}")
    public ResponseEntity<ResponseDTO> passPetPosition(@PathVariable  String codepet,  @PathVariable int move ) {
        try {
            listDEService.getPets().passPetPosition(codepet,move, listDEService.getPets());
            return new ResponseEntity<>(new ResponseDTO(200, "La mascota se ha adelantado de posición", null), HttpStatus.OK);
        } catch (ListDEExecpcion e) {
            return new ResponseEntity<>(new ResponseDTO(500, "el numero de posiciones indicadas a adelantar es mas grande que la lista", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/orderpetsmaletostart")
    public ResponseEntity<ResponseDTO> orderPetsMaleToStart() {
        try {
            listDEService.getPets().orderPetsToStart();
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Se han añadido las mascotas masculinas al inicio, las femeninas al final.",
                    null), HttpStatus.OK);
        } catch (ListDEExecpcion e) {
            return new ResponseEntity<>(new ResponseDTO(
                    500, "Ocurrió un error al ordenar el género de las mascotas.", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
