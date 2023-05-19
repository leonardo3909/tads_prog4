package co.edu.umanizales.tads_prog4.controller;

import co.edu.umanizales.tads_prog4.controller.dto.KidDTO;
import co.edu.umanizales.tads_prog4.controller.dto.KidsByLocationDTO;
import co.edu.umanizales.tads_prog4.controller.dto.RangeAgeKidsDTO;
import co.edu.umanizales.tads_prog4.controller.dto.ResponseDTO;
import co.edu.umanizales.tads_prog4.execption.ListSEExecption;
import co.edu.umanizales.tads_prog4.model.Kid;
import co.edu.umanizales.tads_prog4.model.Location;
import co.edu.umanizales.tads_prog4.servive.ListSEService;
import co.edu.umanizales.tads_prog4.servive.LocationService;
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
        try {
            listSEService.getKids().invert();
            return new ResponseEntity<>(new ResponseDTO(200,
                    "The list has been inverted", null), HttpStatus.OK);
        }catch (ListSEExecption exception){
            return new ResponseEntity<>(new ResponseDTO(500,"Se ha presentado un error al intentar invertir la lista",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path = "/change_extremes")
    public ResponseEntity<ResponseDTO> changeExtremes() {
        try {
            listSEService.getKids().changeExtremes();
            return new ResponseEntity<>(new ResponseDTO(
                    200,"SE han intercambiado los extremos",
                    null), HttpStatus.OK);
        } catch (ListSEExecption exception){
            return new ResponseEntity<>(new ResponseDTO(500,"Error al intentar intercalar los extremos",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addKid(@RequestBody KidDTO kidDTO){
        try {
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

        }catch (ListSEExecption exception){
            return new ResponseEntity<>(new ResponseDTO(500,"Error al adicionar el petacon",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path = "/kidsbylocations")
    public ResponseEntity<ResponseDTO> getKidsByLocation(){
        try {
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
        } catch (ListSEExecption exception){
            return new ResponseEntity<>(new ResponseDTO(500,"Ocurrio un error al obtener los niños por ubicacion",null ),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path = "/kidsbydepto")
    public ResponseEntity<ResponseDTO>getCountKidsByDeptoCode(){
        try {
            List<KidsByLocationDTO> KidsByLocationDTOList1 = new ArrayList<>();
            for (Location loc: locationService.getLocations()){
                int count = listSEService.getKids().getCountKidsByDeptoCode(loc.getCode());
                if (count>0){
                    KidsByLocationDTOList1.add(new KidsByLocationDTO(loc,count));
                }
            }
            return new ResponseEntity<>(new ResponseDTO(200,KidsByLocationDTOList1,null),HttpStatus.OK);
        }catch (ListSEExecption exception){
            return new ResponseEntity<>(new ResponseDTO(500,"Se ha presentado un error al intentar ubicar al petacon",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path="/boysfirstgirlslast")
    public ResponseEntity<ResponseDTO> boyStartGirlsLast(){
        try {
            listSEService.getKids().boyStartGirlsLast();
            return new ResponseEntity<>(new ResponseDTO(200, "Los niños salen al inicio, las niñas al final",
                    null), HttpStatus.OK);
        }catch (ListSEExecption exception){
            return new ResponseEntity<>(new ResponseDTO(500,"Se a presentado un error al intentar modificar la lista",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path="/boythengirl")
    public ResponseEntity<ResponseDTO> boyThenGirl(){
        try {
            listSEService.getKids().boyThenGirl();
            return new ResponseEntity<>(new ResponseDTO(200, "Los niños han sido alternados según su género",
                    null), HttpStatus.OK);
        }catch (ListSEExecption exception){
            return new ResponseEntity<>(new ResponseDTO(500,"Se ha presentado un error al inentar alternar la lista",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path = "/deletebyage/{age}")
    public ResponseEntity<ResponseDTO> deleteByAge(@PathVariable int age){
        try {
            listSEService.getKids().deleteByAge(age);
            return new ResponseEntity<>(new ResponseDTO(200, "Los niños han sido eliminados",
                    null), HttpStatus.OK);
        }catch (ListSEExecption exception){
            return new ResponseEntity<>(new ResponseDTO(500,"Se presento un error al intentar eliminal al niño",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path="/averageage")
    public ResponseEntity<ResponseDTO> averageAge(){
        try {
            return new ResponseEntity<>(new ResponseDTO(200,
                    listSEService.getKids().averageAge(), null), HttpStatus.OK);
        }catch (ListSEExecption exception){
            return new ResponseEntity<>(new ResponseDTO(500,"se ha presentado un error al intentar organizar la lista por la edad promedio indicada",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path="/sendbottom/{initial}")
    public ResponseEntity<ResponseDTO> sendBottomByLetter(@PathVariable char initial){
        try {
            listSEService.getKids().sendBottomByLetter(Character.toUpperCase(initial));
            return new ResponseEntity<>(new ResponseDTO(200, "Los niños con esa letra se han enviado al final",
                    null), HttpStatus.OK);
        }catch (ListSEExecption exception){
            return new ResponseEntity<>(new ResponseDTO(500,"No se pudo mover los niños con la letra indicada al final de la lista",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path = "/Rangeage")
    public ResponseEntity<ResponseDTO> getRangeByKids(){
        List<RangeAgeKidsDTO> kidsRangeDTOList = new ArrayList<>();



        return new ResponseEntity<>(new ResponseDTO(200,kidsRangeDTOList,null),HttpStatus.OK);
    }

    @GetMapping(path="/forwardpositions/{identification}")
    public ResponseEntity<ResponseDTO> forwardPositions(@PathVariable String identification, int positions){
        try {
            listSEService.getKids().forwardPositions(identification, positions);
            return new ResponseEntity<>(new ResponseDTO(200, "The kid has been moved to the position", null), HttpStatus.OK);
        }catch (ListSEExecption exception){
            return new ResponseEntity<>(new ResponseDTO(500,"error al intentar mover mover ese niño de pocicion",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path="/afterwardspositions/{identification}")
    public ResponseEntity<ResponseDTO> afterwardsPositions(@PathVariable String identification, int positions){
        try {
            listSEService.getKids().afterwardsPositions(identification, positions);
            return new ResponseEntity<>(new ResponseDTO(200, "The kid has been moved to the position", null), HttpStatus.OK);
        }catch (ListSEExecption exception){
            return new ResponseEntity<>(new ResponseDTO(500,"error al intentar mover mover ese niño de pocicion",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }





}
