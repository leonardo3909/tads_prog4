package co.edu.umanizales.tads_prog4.model;

import co.edu.umanizales.tads_prog4.execption.ListDEExecpcion;
import co.edu.umanizales.tads_prog4.execption.ListSEExecption;
import lombok.Data;

@Data
public class ListDE {

    private Node head;
    private int size;
    private int pet;
    private Node tail;

    public void add(Pet pet) throws ListDEExecpcion {
        if(head != null){
            Node temp = head;
            while(temp.getNext() !=null)
            {
                if (temp.getData().getIdentification().equals(pet.getIdentification())){
                    throw new ListDEExecpcion("ya existe en la lista esta mascota");
                }
                temp = temp.getNext();

            }
            if (temp.getData().getIdentification().equals(pet.getIdentification())){
                throw new ListDEExecpcion("ya existe en la lista mascota");
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

    public void addToStart(Pet pet) throws ListDEExecpcion {
        Node newNode = new Node(pet);
        if (head != null) {
            newNode.setNext(head);
            head.setPrev(newNode);
        }
        else {
            throw new ListDEExecpcion("no se pudo adicionar a la lista la mascota");
        }
        head = newNode;
        size++;
    }


    public void changeExtremes() throws ListDEExecpcion {
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
        else {
            throw new ListDEExecpcion("No se pudo cambiar los extremos de la lista");
        }
    }

    public int getCountPetsByLocationCode(String code) throws ListDEExecpcion {
        int count = 0;
        if (head != null) {
            Node temp = head;
            while (temp != null) {
                if (temp.getData().getLocation().getCode().equals(code)) {
                    count++;
                    throw new ListDEExecpcion("no se pudo localizar la mascota");
                }
                temp = temp.getNext();
            }
        }
        return count;
    }

    public int getCountPetsByDeptoCode(String code) throws ListDEExecpcion {
        int count = 0;
        if (head != null) {
            Node temp = head;
            while (temp != null) {
                if (temp.getData().getLocation().getCode().substring(0, 5).equals(code)) {
                    count++;
                    throw new ListDEExecpcion("no se pudo localizar la mascota");
                }
                temp = temp.getNext();
            }
        }
        return count;
    }

    public void invertPets() throws ListDEExecpcion {
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
        else {
            throw new ListDEExecpcion("no se pudo imvertir la lista");
        }
    }

    public void boyStartGirlsLast() throws ListDEExecpcion {
        if (head != null) {
            ListDE boysList = new ListDE();
            ListDE girlsList = new ListDE();
            Node temp = head;
            while (temp != null) {
                if (temp.getData2().getGender() == 'M') {
                    boysList.addToStart(temp.getData2());
                } else {
                    girlsList.add(temp.getData2());
                }
                temp = temp.getNext();
            }
            boysList.getTail().setNext(girlsList.getHead());
            girlsList.getHead().setPrev(boysList.getTail());
            head = boysList.getHead();
            tail = girlsList.getTail();
            temp = tail;
            while (temp != null) {
                if (temp.getData2().getGender() == 'F') {
                    boysList.addToStart(temp.getData2());
                } else {
                    girlsList.add(temp.getData2());
                }
                temp = temp.getNext();
            }
        }
        else {
            throw new ListDEExecpcion("no se pudo organizar la lista segun lo indicado");
        }
    }

    public void boyThenGirl() throws ListDEExecpcion{
        ListDE listMale = new ListDE();
        ListDE listFemale = new ListDE();
        Node temp = this.head;
        while (temp != null){
            if(temp.getData().getGender()=='M'){
                listMale.add(temp.getData2());
                throw new ListDEExecpcion("error al intentar intercalar la lista");
            }
            if(temp.getData().getGender()=='F'){
                listFemale.add(temp.getData2());
                throw new ListDEExecpcion("error al intentar intercalar la lista");
            }
            temp = temp.getNext();
        }

        ListDE sortedList = new ListDE();
        Node maleNode = listMale.getHead();
        Node femaleNode = listFemale.getHead();
        while (maleNode != null || femaleNode != null){
            if (maleNode != null){
                sortedList.add(maleNode.getData2());
                maleNode = maleNode.getNext();

            }
            if (femaleNode != null){
                sortedList.add(femaleNode.getData2());
                femaleNode = femaleNode.getNext();

            }
        }
        this.head = sortedList.getHead();
    }

    public void deleteByAge(int age) throws ListDEExecpcion {
        Node current = head;
        while (current != null && current.getData().getAge() != age) {
            current = current.getNext();
        }

        if (current != null) {
            if (current.getPrev() == null && current.getNext() == null) {
                // The node to delete is the only node in the list
                head = null;
            } else if (current.getPrev() == null) {
                // The node to delete is the first node in the list
                head = current.getNext();
                current.getNext().setPrev(null);
            } else if (current.getNext() == null) {
                // The node to delete is the last node in the list
                current.getPrev().setNext(null);
            } else {
                // The node to delete is somewhere in the middle of the list
                current.getPrev().setNext(current.getNext());
                current.getNext().setPrev(current.getPrev());
            }
        }
        else {
            throw new ListDEExecpcion("no se pudo eliminar a la mascota");
        }
    }

    public float averageAge() throws ListDEExecpcion {
        if (this.head != null) {
            Node temp = this.head;
            int count = 0;
            int ages = 0;
            while (temp != null) {
                count++;
                ages += temp.getData2().getAge();
                temp = temp.getNext();
            }
            return (float) ages / count;
        } else {
            throw new ListDEExecpcion("no se ha promediado la lista con la edad solicitada");
        }
    }

    public void sendBottomByLetter(char initial) throws ListDEExecpcion{
        ListDE sendBottom = new ListDE();
        ListDE sendTop = new ListDE();
        Node temp = this.head;

        while (temp != null){
            if (temp.getData().getName().charAt(0) != Character.toUpperCase(initial)){
                sendTop.add(temp.getData2());
            }
            else{
                sendBottom.add(temp.getData2());
                throw new ListDEExecpcion("no se pudo organizar la lista");
            }
            temp = temp.getNext();
        }

        this.head = sendTop.getHead();
    }


    public void deleteById(String id) {
        Node current = head;
        while (current != null) {
            if (current.getData().getIdentification().equals(id)) {
                if (current == head) { // si el nodo es la cabeza
                    head = current.getNext();
                    if (head != null) {
                        head.setPrev(null);
                    }
                } else if (current.getNext() == null) { // si el nodo es cola
                    current.getPrev().setNext(null);
                } else { // si el nodo está en el medio
                    current.getPrev().setNext(current.getNext());
                    current.getNext().setPrev(current.getPrev());
                }
                break;
            }
            current = current.getNext();
        }
    }



}
