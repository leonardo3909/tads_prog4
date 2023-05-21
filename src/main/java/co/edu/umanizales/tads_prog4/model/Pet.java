package co.edu.umanizales.tads_prog4.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pet {

    private String codePet;
    private String name;
    private byte age;
    private char genderPet;
    private String race;
    private Location location;
    private boolean dirty;



}
