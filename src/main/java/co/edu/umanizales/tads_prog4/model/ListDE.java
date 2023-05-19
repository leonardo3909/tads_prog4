package co.edu.umanizales.tads_prog4.model;

import co.edu.umanizales.tads_prog4.execption.ListDEExecpcion;
import co.edu.umanizales.tads_prog4.execption.ListSEExecption;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListDE {

    private NodeDE head;
    private int size;
    private int pet;
    private NodeDE tail;

    public void addPets(Pet pet) throws ListDEExecpcion {
        if(head != null){
            NodeDE temp = head;
            while(temp.getNext() !=null)
            {
                if (temp.getData2().getIdentification().equals(pet.getIdentification())){
                    throw new ListDEExecpcion("ya existe en la lista esta mascota");
                }
                temp = temp.getNext();

            }
            if (temp.getData2().getIdentification().equals(pet.getIdentification())){
                throw new ListDEExecpcion("ya existe en la lista mascota");
            }
            /// Parado en el último
            NodeDE newNode = new NodeDE(pet);
            temp.setNext(newNode);
            newNode.setPrev(temp);
        }
        else {
            head = new NodeDE(pet);
        }
        size ++;
    }

    public void addToStart(Pet pet) throws ListDEExecpcion {
        NodeDE newNode = new NodeDE(pet);
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
            NodeDE temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            // temp está en el último nodo
            Pet copy = head.getData2();
            head.setData2(temp.getData2());
            temp.setData2(copy);

            NodeDE prev = temp.getPrev();
            NodeDE next = head.getNext();

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
            NodeDE temp = head;
            while (temp != null) {
                if (temp.getData2().getLocation().getCode().equals(code)) {
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
            NodeDE temp = head;
            while (temp != null) {
                if (temp.getData2().getLocation().getCode().substring(0, 5).equals(code)) {
                    count++;
                    throw new ListDEExecpcion("no se pudo localizar la mascota");
                }
                temp = temp.getNext();
            }
        }
        return count;
    }

    public void invertPets() throws ListDEExecpcion{
        if (this.head != null) {
            ListDE listCP = new ListDE();
            NodeDE temp = this.head;
            while (temp != null) {
                listCP.addPets(temp.getData2());
                temp = temp.getNext();
            }
            this.head = listCP.getHead();
        }
        else{
            throw new ListDEExecpcion("La lista está vacía");
        }
    }

    public void boyStartGirlsLast() throws ListDEExecpcion {
        if (head != null) {
            ListDE boysList = new ListDE();
            ListDE girlsList = new ListDE();
            NodeDE temp = head;
            while (temp != null) {
                if (temp.getData2().getGender() == 'M') {
                    boysList.addToStart(temp.getData2());
                } else {
                    girlsList.addPets(temp.getData2());
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
                    girlsList.addPets(temp.getData2());
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
        NodeDE temp = this.head;
        while (temp != null){
            if(temp.getData2().getGender()=='M'){
                listMale.addPets(temp.getData2());
                throw new ListDEExecpcion("error al intentar intercalar la lista");
            }
            if(temp.getData2().getGender()=='F'){
                listFemale.addPets(temp.getData2());
                throw new ListDEExecpcion("error al intentar intercalar la lista");
            }
            temp = temp.getNext();
        }

        ListDE sortedList = new ListDE();
        NodeDE maleNode = listMale.getHead();
        NodeDE femaleNode = listFemale.getHead();
        while (maleNode != null || femaleNode != null){
            if (maleNode != null){
                sortedList.addPets(maleNode.getData2());
                maleNode = maleNode.getNext();

            }
            if (femaleNode != null){
                sortedList.addPets(femaleNode.getData2());
                femaleNode = femaleNode.getNext();

            }
        }
        this.head = sortedList.getHead();
    }

    public void deleteByAge(int age) throws ListDEExecpcion {
        NodeDE current = head;
        while (current != null && current.getData2().getAge() != age) {
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
            NodeDE temp = this.head;
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
        NodeDE temp = this.head;

        while (temp != null){
            if (temp.getData2().getName().charAt(0) != Character.toUpperCase(initial)){
                sendTop.addPets(temp.getData2());
            }
            else{
                sendBottom.addPets(temp.getData2());
                throw new ListDEExecpcion("no se pudo organizar la lista");
            }
            temp = temp.getNext();
        }

        this.head = sendTop.getHead();
    }


    public void deleteById(String id) {
        NodeDE current = head;
        while (current != null) {
            if (current.getData2().getIdentification().equals(id)) {
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

    public List<Pet> toList(){
        List<Pet> listPets= new ArrayList<>();
        NodeDE temp = head;
        while(temp!= null){
            listPets.add(temp.getData2());
            temp = temp.getNext();
        }

        return  listPets;
    }


}
