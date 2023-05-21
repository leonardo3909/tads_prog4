package co.edu.umanizales.tads_prog4.model;

import co.edu.umanizales.tads_prog4.execption.ListDECircularExecpcion;
import co.edu.umanizales.tads_prog4.execption.ListDEExecpcion;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
public class ListDECircular {

    private NodeDE head;
    int size;
    private List<Pet> petsCircular = new ArrayList<>();


    public void addPetListCircular(Pet pet) throws ListDECircularExecpcion {
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp.getNextDE() != this.head) {
                if (temp.getData2().getCodePet().equals(pet.getCodePet())) {
                    throw new ListDECircularExecpcion("la mascota y existe en la lista");
                }
                temp = temp.getNextDE();
            }
            if (temp.getData2().getCodePet().equals(pet.getCodePet())) {
                throw new ListDECircularExecpcion("la mascota ya existe en la lista");
            }
            NodeDE newPet = new NodeDE(pet);
            temp.setNextDE(newPet);
            newPet.setPrev(temp);
            newPet.setNextDE(this.head);
            this.head.setPrev(newPet);
        } else {
            this.head = new NodeDE(pet);
            this.head.setNextDE(this.head);
            this.head.setPrev(this.head);
        }
        size++;
    }

   public void addPetStart(Pet pet) throws ListDECircularExecpcion{
       NodeDC newPet = new NodeDC(pet);
       if (this.head != null){
           NodeDC temp = this.head;
           while (temp.getNextDC() != this.head){
               temp = temp.getNextDC();
           }
           temp.setNextDC(newPet);
           newPet.setPrev(newPet);
           newPet.setNextDC(head);
           head.setPrev(newPet);
           this.head = newPet;
       }
       else{
           newPet.setNextDC(newPet);
           newPet.setPrev(newPet);
           this.head = newPet;
           throw new ListDECircularExecpcion("no se pudo adicionar la mascota al final");
       }
       size++;
   }

    public int bathPets(char letter) throws ListDECircularExecpcion {
        char start = Character.toLowerCase(letter);
        NodeDC temp = head;

        if (temp == null) {
            throw new ListDECircularExecpcion(" No se encontraron mascotas para bañar");
        }

        if (start != 'r' && start != 'l') {
            throw new ListDECircularExecpcion(" no ha ingresado lo siguente por favor intentelo de nuevo: R (right) o L (left)");
        }

        Random rand = new Random();
        int num = rand.nextInt(size) + 1;

        if (num == 1) {
            if (temp.getDataDC().isDirty()) {
                temp.getDataDC().setDirty(false);
            } else {
                throw new ListDECircularExecpcion("nombre de la mascota: " + temp.getDataDC().getName() + " y de id " +
                        temp.getDataDC().getCodePet() + " la mascota ya se encuentra bañada y libre de pulgas ");
            }
        } else {
            int count = 1;
            if (start == 'r') {
                while (count != num) {
                    temp = temp.getNextDC();
                    count++;
                }
            } else {
                while (count != num) {
                    temp = temp.getPrev();
                    count++;
                }
            }
            if (temp.getDataDC().isDirty()) {
                temp.getDataDC().setDirty(false);
            } else {
                throw new ListDECircularExecpcion("nombre de la mascota: " + temp.getDataDC().getName() + " identificacion: " +
                        temp.getDataDC().getCodePet() + " la mascota ya se encuentra bañada y libre de pulgas");
            }
        }
        return num;
    }

    public void addPosition(int position, Pet pet) throws ListDECircularExecpcion {
        if (size < position) {
            throw new ListDECircularExecpcion("la posicion que ingresastes es mas grande que la lista.");
        }
        if (head == null) {
            throw new ListDECircularExecpcion("la lista se encuentra vacia por favor verifique.");
        }
        NodeDC newNode = new NodeDC(pet);
        if (position == 1) {
            addPetStart(pet);
        } else {
            NodeDC temp = head;
            int count = 1;

            while (count < position) {
                temp = temp.getNextDC();
                count++;
            }
            newNode.setPrev(temp.getPrev());
            newNode.setNextDC(temp);
            temp.getPrev().setNextDC(newNode);
            temp.setPrev(newNode);
            size++;
        }
    }

}
