package co.edu.umanizales.tads_prog4.model;

import co.edu.umanizales.tads_prog4.execption.ListDEExecpcion;
import lombok.Data;

@Data
public class ListDECircular {

    private Node head;
    private int pet;
    private int size;
    private Node tail;

    public void add (Pet pet) throws ListDEExecpcion {
        if (head != null){
            Node temp = head;
            while (temp.getNext() != null){

                if (temp.getData2().getIdentification().equals(pet.getIdentification())){
                    throw new ListDEExecpcion("ya existe la mascota en la lista");
                }
                temp = temp.getNext();

            }
            if (temp.getData2().getIdentification().equals(pet.getIdentification())){
                throw new ListDEExecpcion("ya existe la mascota en la lista");
            }
            Node newNode = new Node(pet);
            temp.setNext(newNode);
            newNode.setPrev(temp);
        }
        else {
            head = new Node(pet);
        }
        size ++;
    }


}
