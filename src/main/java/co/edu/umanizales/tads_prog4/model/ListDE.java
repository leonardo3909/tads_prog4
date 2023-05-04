package co.edu.umanizales.tads_prog4.model;

import lombok.Data;

@Data
public class ListDE {

    private Node head;
    private int size;
    private int pet;
    private Node tail;

    public void add(Pet pet){
        if(head != null){
            Node temp = head;
            while(temp.getNext() !=null)
            {
                temp = temp.getNext();
            }
            /// Parado en el último
            Node newNode = new Node(pet);
            temp.setNext(newNode);
            newNode.setPrev(temp);
        }
        else {
            head = new Node(pet);
        }
        size ++;
    }

    public void addToStart(Pet pet) {
        Node newNode = new Node(pet);
        if (head != null) {
            newNode.setNext(head);
            head.setPrev(newNode);
        }
        head = newNode;
        size++;
    }


    public void changeExtremes() {
        if (head != null && head.getNext() != null) {
            Node temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            // temp está en el último nodo
            Kid copy = head.getData();
            head.setData(temp.getData());
            temp.setData(copy);

            Node prev = temp.getPrev();
            Node next = head.getNext();

            head.setNext(temp.getNext());
            temp.setNext(next);
            head.getNext().setPrev(head);
            temp.getPrev().setNext(temp);
            temp.setPrev(prev);
            head.setPrev(null);
        }
    }

    public int getCountPetsByLocationCode(String code) {
        int count = 0;
        if (head != null) {
            Node temp = head;
            while (temp != null) {
                if (temp.getData().getLocation().getCode().equals(code)) {
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }

    public int getCountPetsByDeptoCode(String code) {
        int count = 0;
        if (head != null) {
            Node temp = head;
            while (temp != null) {
                if (temp.getData().getLocation().getCode().substring(0, 5).equals(code)) {
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }

    public void invert() {
        if (head != null) {
            ListDE newList = new ListDE();
            Node temp = head;
            while (temp != null) {
                newList.addToStart(temp.getData2());
                temp = temp.getNext();
            }
            head = newList.getHead();
            tail = newList.getTail();
        }
    }
















}
