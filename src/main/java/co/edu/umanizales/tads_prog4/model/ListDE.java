package co.edu.umanizales.tads_prog4.model;

import lombok.Data;

@Data
public class ListDE {

    private Node head;
    private int size;

    public void add(Pet pet){
        if(head != null){
            Node temp = head;
            while(temp.getNext() !=null)
            {
                temp = temp.getNext();
            }
            /// Parado en el último
            Node newNode = new Node(Pet);
            temp.setNext(newNode);
            newNode.setNext(temp);
        }
        else {
            head = new Node(Pet);
        }
        size ++;
    }

}
