package co.edu.umanizales.tads_prog4.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pet {

    private String identification;
    private String name;
    private byte age;
    private char gender;
    private String race;
    private Location location;
    private boolean dirty;


    public Pet(String identification, String name, byte age, char gender, String raze, Location location) {
    }

}
