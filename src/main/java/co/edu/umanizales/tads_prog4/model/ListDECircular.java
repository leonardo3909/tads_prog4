package co.edu.umanizales.tads_prog4.model;

import co.edu.umanizales.tads_prog4.execption.ListDECircularExecpcion;
import co.edu.umanizales.tads_prog4.execption.ListDEExecpcion;
import lombok.Data;

import java.util.Random;

@Data
public class ListDECircular {

    private NodeDE head;
    private int pet;
    private int size;
    private NodeDE random;

   public void addPetsCircular(Pet pet) throws ListDECircularExecpcion{
       NodeDE newPet = new NodeDE(pet);
       if (this.head != null){
           NodeDE temp = this.head;
           while (temp.getNext() != this.head){
               temp = temp.getNext();
           }
           temp.setNext(newPet);
           newPet.setPrev(temp);
           newPet.setNext(this.head);
           this.head.setPrev(newPet);
       }
       else{
           newPet.setNext(newPet);
           newPet.setPrev(newPet);
           this.head = newPet;
           throw new ListDECircularExecpcion("no se pudo adicionar la mascota");
       }
       size++;
   }

   public void addPetStart(Pet pet) throws ListDECircularExecpcion{
       NodeDE newPet = new NodeDE(pet);
       if (this.head != null){
           NodeDE temp = this.head;
           while (temp.getNext() != this.head){
               temp = temp.getNext();
           }
           temp.setNext(newPet);
           newPet.setPrev(newPet);
           newPet.setNext(head);
           head.setPrev(newPet);
           this.head = newPet;
       }
       else{
           newPet.setNext(newPet);
           newPet.setPrev(newPet);
           this.head = newPet;
           throw new ListDECircularExecpcion("no se pudo adicionar la mascota al final");
       }
       size++;
   }

    public int bathPets(char letter) throws ListDECircularExecpcion {
        char start = Character.toLowerCase(letter);
        NodeDE temp = head;

        if (temp == null) {
            throw new ListDECircularExecpcion(" No se encontraron mascotas para bañar");
        }

        if (start != 'r' && start != 'l') {
            throw new ListDECircularExecpcion(" no ha ingresado lo siguente por favor intentelo de nuevo: R (right) o L (left)");
        }

        Random rand = new Random();
        int num = rand.nextInt(size) + 1;

        if (num == 1) {
            if (temp.getData2().isDirty()) {
                temp.getData2().setDirty(false);
            } else {
                throw new ListDECircularExecpcion("nombre de la mascota: " + temp.getData2().getName() + " y de id " +
                        temp.getData2().getIdentification() + " la mascota ya se encuentra bañada y libre de pulgas ");
            }
        } else {
            int count = 1;
            if (start == 'r') {
                while (count != num) {
                    temp = temp.getNext();
                    count++;
                }
            } else {
                while (count != num) {
                    temp = temp.getPrev();
                    count++;
                }
            }
            if (temp.getData2().isDirty()) {
                temp.getData2().setDirty(false);
            } else {
                throw new ListDECircularExecpcion("nombre de la mascota: " + temp.getData2().getName() + " identificacion: " +
                        temp.getData2().getIdentification() + " la mascota ya se encuentra bañada y libre de pulgas");
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
        NodeDE newNode = new NodeDE(pet);
        if (position == 1) {
            addPetStart(pet);
        } else {
            NodeDE temp = head;
            int count = 1;

            while (count < position) {
                temp = temp.getNext();
                count++;
            }
            newNode.setPrev(temp.getPrev());
            newNode.setNext(temp);
            temp.getPrev().setNext(newNode);
            temp.setPrev(newNode);
            size++;
        }
    }

}
