package co.edu.umanizales.tads_prog4.model;

import co.edu.umanizales.tads_prog4.execption.ListSEExecption;
import lombok.Data;

@Data
public class ListSE {

    private Node head;
    private int size;

    public void add(Kid kid) throws ListSEExecption {
        if(head != null){
            Node temp = head;
            while(temp.getNext() !=null)
            {
                if (temp.getData().getIdentification().equals(kid.getIdentification())){
                    throw new ListSEExecption("ya existe en la lista este niño");
                }
                temp = temp.getNext();
            }
            if (temp.getData().getIdentification().equals(kid.getIdentification())){
                throw new ListSEExecption("ya existe en la lista este niño");
            }
            /// Parado en el último
            Node newNode = new Node(kid);
            temp.setNext(newNode);
        }
        else {
            head = new Node(kid);
        }
        size ++;
    }

    public void addToStart(Kid kid) throws ListSEExecption{
        if(head !=null)
        {
            Node newNode = new Node(kid);
            newNode.setNext(head);
            head = newNode;
        }
        else {
            head = new Node(kid);
            throw new ListSEExecption("no se ha podido adicionar el niño al inicio de la lista");
        }



        size++;
    }

    public void orderBoysToStart() throws ListSEExecption{
        if(this.head !=null){
            ListSE listCp = new ListSE();
            Node temp = this.head;
            while(temp != null){
                if(temp.getData().getGender()=='M')
                {
                    listCp.addToStart(temp.getData());
                }
                else{
                    listCp.add(temp.getData());
                }

                temp = temp.getNext();
            }
            this.head = listCp.getHead();
        }
        else{
            throw new ListSEExecption("no se ha podido ordenar la lista");
        }
    }

    public void changeExtremes() throws ListSEExecption{
        if(this.head !=null && this.head.getNext() !=null)
        {
            Node temp = this.head;
            while(temp.getNext()!=null)
            {
                temp = temp.getNext();
            }
            //temp está en el último
            Kid copy = this.head.getData();
            this.head.setData(temp.getData());
            temp.setData(copy);
        }
        else{
            throw new ListSEExecption("no es posible cambiar de extremos");
        }

    }

    public int getCountKidsByLocationCode(String code) throws ListSEExecption{
        int count =0;
        if( this.head!=null){
            Node temp = this.head;
            while(temp != null){
                if(temp.getData().getLocation().getCode().equals(code)){
                    count++;
                }
                temp = temp.getNext();
            }
        }
        else{
            throw new ListSEExecption("no se pudo localizar al niño");
        }
        return count;
    }

    public int getCountKidsByDeptoCode(String code) throws ListSEExecption{
        int count = 0;
        if(this.head!=null){
            Node temp = this.head;
            while (temp != null){
                if (temp.getData().getLocation().getCode().substring(0,5).equals(code)){
                    count++;
                }
                temp = temp.getNext();
            }
        }
        else{
            throw new ListSEExecption("no se pudo localizar al niño");
        }
        return count;
    }


    public void invert() throws ListSEExecption {
        if (this.head != null) {
            ListSE listCP = new ListSE();
            Node temp = this.head;
            while (temp != null) {
                listCP.addToStart(temp.getData());
                temp = temp.getNext();
            }
            this.head = listCP.getHead();
        }
        else {
            throw new ListSEExecption("la lista esta vacia");
        }
    }

    public void boyStartGirlsLast() throws ListSEExecption{
        ListSE listCopy = new ListSE();
        Node temp = this.head;
        while (temp != null){
            if (temp.getData().getGender() == 'M'){
                listCopy.add(temp.getData());
                throw new ListSEExecption("no se ha podido organizar la lista");
            }
            temp = temp.getNext();
        }
        temp = this.head;

        while (temp != null){
            if (temp.getData().getGender() == 'F'){
                listCopy.add((temp.getData()));
                throw new ListSEExecption("no se ha podido organizar la lista");
            }
            temp = temp.getNext();
        }
        this.head = listCopy.getHead();
    }

    public void boyThenGirl() throws ListSEExecption{
        ListSE listMale = new ListSE();
        ListSE listFemale = new ListSE();
        Node temp = this.head;
        while (temp != null){
            if(temp.getData().getGender()=='M'){
                listMale.add(temp.getData());
                throw new ListSEExecption("no se a podido intercalar la lista");
            }
            if(temp.getData().getGender()=='F'){
                listFemale.add(temp.getData());
                throw new ListSEExecption("no se a podido intercalar la lista");
            }
            temp = temp.getNext();
        }

        ListSE sortedList = new ListSE();
        Node maleNode = listMale.getHead();
        Node femaleNode = listFemale.getHead();
        while (maleNode != null || femaleNode != null){
            if (maleNode != null){
                sortedList.add(maleNode.getData());
                maleNode = maleNode.getNext();
            }
            if (femaleNode != null){
                sortedList.add(femaleNode.getData());
                femaleNode = femaleNode.getNext();
            }
        }
        this.head = sortedList.getHead();
    }

    public void deleteByAge (int age) throws ListSEExecption{
        Node temp = head;
        Node prev = null;
        while (temp != null && temp.getData().getAge() != age){
            prev = temp;
            temp = temp.getNext();
        }
        if (temp != null){
            if (prev == null){
                head = temp.getNext();
            }
            else{
                prev.setNext(temp.getNext());
                throw new ListSEExecption("no se pudo eliminar al niño de la lista");
            }
        }
    }

    public float averageAge() throws ListSEExecption{

        if (head != null){
            Node temp = head;
            int contador = 0;
            int ages = 0;
            while(temp.getNext() != null) {
                contador++;
                ages = ages + temp.getData().getAge();
                temp = temp.getNext();

            }
            return (float) ages/contador;
        }
        else{
            throw new ListSEExecption("no se ha promediado la lista con la edad solicitada");

        }
    }

    public void sendBottomByLetter(char initial) throws ListSEExecption{


        ListSE sendBottom = new ListSE();
        Node temp = this.head;

        while (temp != null){
            if (temp.getData().getName().charAt(0) != Character.toUpperCase(initial)){
                sendBottom.add(temp.getData());
                throw new ListSEExecption("no se ha podido mandar al final de la lista los niños indicados");
            }
            temp = temp.getNext();
        }

        temp = this.head;

        while (temp != null){
            if (temp.getData().getName().charAt(0) == Character.toUpperCase(initial)){
                sendBottom.add(temp.getData());
                throw new ListSEExecption("no se ha podido mandar al final de la lista los niños indicados");
            }
            temp = temp.getNext();
        }

        this.head = sendBottom.getHead();
    }

    public int rangeByAge(int min,int max) throws ListSEExecption{
        Node temp = head;
        int counter = 0;
        while (temp != null){
            if (temp.getData().getAge() >= min && temp.getData().getAge()<= max){
                counter++;
                throw new ListSEExecption("no se pudo organizar la lista por el rango de edad indicado");

            }
            temp = temp.getNext();

        }
        return counter;
    }

    public void forwardPositions(String identification, int positions) throws ListSEExecption{
        if (head != null) {
            if (positions < size) {
                if (head.getData().getIdentification() == identification) {
                    //Como es la cabeza, entonces no puede subir posiciones
                } else {
                    int count = 1;
                    Node temp = head;
                    while (temp.getNext().getData().getIdentification() != identification) {
                        temp = temp.getNext();
                        count++;
                        if (temp.getNext() != null) {
                            return;
                        }
                    }
                    Node temp2 = new Node(temp.getNext().getData());
                    temp.setNext(temp.getNext().getNext());
                    if (positions >= count + 1) {
                        addToStart(temp2.getData());
                        throw new ListSEExecption("no se pudo organizar al niño en la pocicion indicada");
                    }
                }
            }
        }
        return;
    }

    public void afterwardsPositions(String identification, int positions) throws ListSEExecption{
        if (head!=null){
            if(positions<size){
                if(head.getData().getIdentification()==identification){
                    Node node = new Node(head.getNext().getData());
                    addByPosition(node.getData(), positions+1);
                    head = head.getNext();
                }
                else{
                    int count = 1;
                    Node temp = head;
                    while(temp.getNext().getData().getIdentification()!=identification){
                        temp = temp.getNext();
                        count++;
                        if(temp.getNext()!=null){
                            return;
                        }
                    }
                    Node temp2=new Node(temp.getNext().getData());
                    temp.setNext(temp.getNext().getNext());
                    addByPosition(temp2.getData(), count+1+positions);
                    throw new ListSEExecption("no se pudo organizar al niño en la pocicion indicada");
                }
            }
            return;


        }
    }

    private void addByPosition(Kid data, int i) {
    }
}


