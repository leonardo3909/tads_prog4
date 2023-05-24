package co.edu.umanizales.tads_prog4.controller.dto;

import lombok.Data;

@Data
public class PetDTO {

    private String identificationPet;
    private String namePet;
    private byte agePet;
    private char genderPet;
    private String codeLocationPet;
    private boolean bath;
    private byte numberOfTicks;
}
