package co.edu.umanizales.tads_prog4.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pet {

    private String codePet;
    private char genderPet;
    private int agePet;
    private String namePet;
    private Location locationPets;
    private boolean bath;



}
