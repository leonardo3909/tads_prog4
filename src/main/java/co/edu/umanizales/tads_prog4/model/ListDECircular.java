package co.edu.umanizales.tads_prog4.model;

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


    public void addPetListCircular(Pet pet) throws ListDEExecpcion {
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp.getNextDE() != this.head) {
                if (temp.getData2().getCodePet().equals(pet.getCodePet())) {
                    throw new ListDEExecpcion("la mascota y existe en la lista");
                }
                temp = temp.getNextDE();
            }
            if (temp.getData2().getCodePet().equals(pet.getCodePet())) {
                throw new ListDEExecpcion("la mascota ya existe en la lista");
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


    public int bathPet(String side) {
        if (head == null) {
            return -1;
        }
        int size = getSize();
        Random random = new Random();
        int randomPosition = random.nextInt(size) + 1;
        NodeDE temp = head;
        int cont = 1;
        if (side.equals("L")) {
            temp = head.getPrev();
        }
        while (cont < randomPosition) {
            if (side.equals("R")) {
                temp = temp.getNextDE();
            } else if (side.equals("L")) {
                temp = temp.getPrev();
            }
            cont++;
        }
        if (side.equals("L")) {
            temp = temp.getNextDE();
        }
        Pet pet = temp.getData2();
        if (!pet.isBath()) {
            pet.setBath(true);
            return randomPosition;
        } else {
            return 0;
        }
    }

    public void addPetToStart(Pet pet) throws ListDEExecpcion {
        if (head == null) {
            addPetListCircular(pet);
        } else {
            NodeDE newNode = new NodeDE(pet);
            NodeDE temp = head.getPrev();
            if (temp == null) {
                throw new ListDEExecpcion("El nodo anterior de la cabeza es nulo.");
            }
            temp.setNextDE(newNode);
            newNode.setPrev(temp);
            newNode.setNextDE(head);
            head.setPrev(newNode);
            head = newNode;
            size++;
        }
    }

    public void addPetToEnd(Pet pet) throws ListDEExecpcion {
        if (head == null) {
            addPetListCircular(pet);
        } else {
            NodeDE newNode = new NodeDE(pet);
            NodeDE temp = head.getPrev();
            if (temp == null) {
                throw new ListDEExecpcion("El nodo anterior de la cabeza es nulo.");
            }
            temp.setNextDE(newNode);
            newNode.setPrev(temp);
            newNode.setNextDE(head);
            head.setPrev(newNode);
            size++;
        }
    }
    public void addPetInPosition(int position, Pet pet) throws ListDEExecpcion {
        if (size < position) {
            throw new ListDEExecpcion("Se ingresó una posición más grande que la lista.");
        }

        if (head != null) {
            if (position == 1) {
                addPetToStart(pet);
            } else {
                NodeDE temp = head;
                int count = 1;
                while (temp != null && count < position - 1) {
                    temp = temp.getNextDE();
                    count++;
                }
                if (temp != null) {
                    NodeDE newNode = new NodeDE(pet);
                    newNode.setNextDE(temp.getNextDE());
                    newNode.setPrev(temp);
                    if (temp.getNextDE() != null) {
                        temp.getNextDE().setPrev(newNode);
                    }
                    temp.setNextDE(newNode);
                }
            }
        }
    }

    public int bathingPetsWithTicks(String codePet){
        if (head == null) {
            return -1;
        }
        int size = getSize();
        Random random = new Random();
        int randomPosition = random.nextInt(size) + 1;
        NodeDE temp = head;
        int cont = 1;
        if (codePet.equals(codePet)) {
            temp = head.getPrev();
        }
        while (cont < randomPosition) {
            if (codePet.equals(codePet)) {
                temp = temp.getNextDE();
            } else if (codePet.equals(codePet)) {
                temp = temp.getPrev();
            }
            cont++;
        }
        if (codePet.equals(codePet)) {
            temp = temp.getNextDE();
        }
        Pet pet = temp.getData2();
        if (pet.getNumberOfTicks(cont)) {
            pet.setBath(true);
            return randomPosition;
        } else {
            return 0;
        }
    }

    public List<Pet> print() {
        List<Pet> pets = new ArrayList<>();
        if (head != null) {
            NodeDE temp = head;
            do {
                pets.add(temp.getData2());
                temp = temp.getNextDE();
            } while (temp != head);
        }
        return pets;
    }

}
