package co.edu.umanizales.tads_prog4.model;

import co.edu.umanizales.tads_prog4.execption.ListDECircularExecpcion;
import co.edu.umanizales.tads_prog4.execption.ListDEExecpcion;
import lombok.Data;

@Data
public class ListDECircular {

    private NodeDE head;
    private int pet;
    private int size;
    private NodeDE tail;

   public void addPets(Pet pet){
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
       }
       size++;
   }

   public void addPetStart(Pet pet){
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
       }
       size++;
   }


}
