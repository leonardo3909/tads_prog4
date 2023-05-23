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
            while(temp.getNextDE() !=null)
            {
                if (temp.getData2().getCodePet().equals(pet.getCodePet())){
                    throw new ListDEExecpcion("ya existe en la lista esta mascota");
                }
                temp = temp.getNextDE();

            }
            if (temp.getData2().getCodePet().equals(pet.getCodePet())){
                throw new ListDEExecpcion("ya existe en la lista mascota");
            }
            /// Parado en el último
            NodeDE newNode = new NodeDE(pet);
            temp.setNextDE(newNode);
            newNode.setPrev(temp);
        }
        else {
            head = new NodeDE(pet);
        }
        size ++;
    }

    public void addToStartPet(Pet pet) {
        if (head != null) {
            NodeDE newNodeDE = new NodeDE(pet);
            newNodeDE.setNextDE(head);
            head.setPrev(newNodeDE);
            head = newNodeDE;
        } else {
            head = new NodeDE(pet);
        }
        size++;
    }


    public void changeExtremesPets() throws ListDEExecpcion {
        if (this.head != null && this.head.getNextDE() != null) {
            NodeDE temp = this.head;
            while (temp.getNextDE() != null) {
                temp = temp.getNextDE();
            }

            Pet copy = this.head.getData2();
            this.head.setData2(temp.getData2());
            temp.setData2(copy);

            NodeDE tempPrev = temp.getPrev();
            NodeDE headNext = this.head.getNextDE();
            this.head.setNextDE(temp.getNextDE());
            this.head.setPrev(temp);
            temp.setNextDE(headNext);
            temp.setPrev(tempPrev);
        }
        else
        {
            throw new ListDEExecpcion("No es posible cambiar de extremos.");
        }
    }



    public int getCountPetsByLocationCode(String code) throws ListDEExecpcion {
        int count = 0;
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp != null) {
                if (temp.getData2().getLocationPets().getCode().equals(code)) {
                    count++;
                }
                temp = temp.getNextDE();
            }
            return count;
        } else{
            throw new ListDEExecpcion("La lista se encuentra vacia verifique por favor");
        }
    }

    public int getCountPetsByDeptoCode(String code) throws ListDEExecpcion {
        int count = 0;
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp != null) {
                if (temp.getData2().getLocationPets().getCode().equals(code)) {
                    count++;
                }
                temp = temp.getNextDE();
            }
            return count;
        }
        else{
            throw new ListDEExecpcion("La lista se encuentra vacia verifique por favor");
        }
    }

    public void invertPets() throws ListDEExecpcion{
        if (this.head != null) {
            ListDE listCP = new ListDE();
            NodeDE temp = this.head;
            while (temp != null) {
                listCP.addToStartPet(temp.getData2());
                temp = temp.getNextDE();
            }
            this.head = listCP.getHead();
        }
        else{
            throw new ListDEExecpcion("La lista está vacía");
        }
    }

    public void intercalatePetsGender() throws ListDEExecpcion{
        ListDE listPetMale = new ListDE();
        ListDE listPetFemale = new ListDE();
        NodeDE temp = this.head;

        if (temp == null) {
            throw new ListDEExecpcion("La lista está vacía");
        }

        while (temp != null) {
            if (temp.getData2().getGenderPet() == 'M') {
                listPetMale.addPets(temp.getData2());
            }
            if (temp.getData2().getGenderPet() == 'F') {
                listPetFemale.addPets(temp.getData2());
            }
            temp = temp.getNextDE();
        }
        ListDE newListPetsFemale = new ListDE();
        NodeDE petMaleNode = listPetMale.getHead();
        NodeDE petFemaleNode = listPetFemale.getHead();
        while (petMaleNode != null || petFemaleNode != null) {
            if (petMaleNode != null) {
                newListPetsFemale.addPets(petMaleNode.getData2());
                petMaleNode = petMaleNode.getNextDE();
            }
            if (petFemaleNode != null) {
                newListPetsFemale.addPets(petFemaleNode.getData2());
                petFemaleNode = petFemaleNode.getNextDE();
            }
        }
        this.head = newListPetsFemale.getHead();
    }



    public void deleteByAge(int age) throws ListDEExecpcion {
        NodeDE current = head;
        while (current != null && current.getData2().getAgePet() != age) {
            current = current.getNextDE();
        }

        if (current != null) {
            if (current.getPrev() == null && current.getNextDE() == null) {
                // The node to delete is the only node in the list
                head = null;
            } else if (current.getPrev() == null) {
                // The node to delete is the first node in the list
                head = current.getNextDE();
                current.getNextDE().setPrev(null);
            } else if (current.getNextDE() == null) {
                // The node to delete is the last node in the list
                current.getPrev().setNextDE(null);
            } else {
                // The node to delete is somewhere in the middle of the list
                current.getPrev().setNextDE(current.getNextDE());
                current.getNextDE().setPrev(current.getPrev());
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
                ages += temp.getData2().getAgePet();
                temp = temp.getNextDE();
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
            if (temp.getData2().getNamePet().charAt(0) != Character.toUpperCase(initial)){
                sendTop.addPets(temp.getData2());
            }
            else{
                sendBottom.addPets(temp.getData2());
                throw new ListDEExecpcion("no se pudo organizar la lista");
            }
            temp = temp.getNextDE();
        }

        this.head = sendTop.getHead();
    }


    public void deletePetByIdentification(String code) throws ListDEExecpcion {
        if (this.head != null) {
            if (this.head.getData2().getCodePet().equals(code)) {
                this.head = this.head.getNextDE();
                if (this.head != null) {
                    this.head.setPrev(null);
                }
            }
            else {
                NodeDE temp = this.head;
                while (temp != null) {
                    if (temp.getData2().getCodePet().equals(code)) {
                        temp.getPrev().setNextDE(temp.getNextDE());
                        if (temp.getNextDE() != null) {
                            temp.getNextDE().setPrev(temp.getPrev());
                        }
                        return;
                    }
                    temp = temp.getNextDE();
                }
                throw new ListDEExecpcion("El código " + code + " no existe en la lista");
            }
        }
        else {
            throw new ListDEExecpcion("No hay datos en la lista");
        }
    }

    public void orderPetsToStart() throws ListDEExecpcion {
        if (this.head != null) {
            ListDE listCP = new ListDE();
            NodeDE temp = this.head;
            while (temp != null) {
                if (temp.getData2().getGenderPet() == 'M') {
                    listCP.addToStartPet(temp.getData2());
                } else {
                    listCP.addPets(temp.getData2());
                }
                temp = temp.getNextDE();
            }
            this.head = listCP.getHead();
        }
        else{
            throw new ListDEExecpcion("La lista está vacía");
        }
    }


    public void addPetInPosition(int position, Pet pet) throws ListDEExecpcion {
        if (size < position) {
            throw new ListDEExecpcion("Se ingresó una posición más grande que la lista.");
        }

        if (head != null) {
            if (position == 1) {
                addToStartPet(pet);
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

    public void passPetPosition(String codePet, int position, ListDE listDE) throws ListDEExecpcion{
        NodeDE temp = this.head;
        int count = 1;
        while (temp != null && !temp.getData2().getCodePet().equals(codePet)) {
            temp = temp.getNextDE();
            count++;
        }
        if (temp != null) {
            int difference = count - position;
            Pet pet = temp.getData2();
            listDE.deletePetByIdentification(temp.getData2().getCodePet());
            if (difference > 0) {
                listDE.addPetInPosition(difference, pet);
            } else {
                listDE.addToStartPet(pet);
            }
        } else {
            throw new ListDEExecpcion("No se encontró ningún niño con la identificación especificada.");
        }
    }


    public List<Pet> toList(){
        List<Pet> listPets= new ArrayList<>();
        NodeDE temp = head;
        while(temp!= null){
            listPets.add(temp.getData2());
            temp = temp.getNextDE();
        }

        return  listPets;
    }


}
