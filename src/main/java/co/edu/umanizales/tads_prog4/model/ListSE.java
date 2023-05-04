package co.edu.umanizales.tads_prog4.model;

import lombok.Data;

@Data
public class ListSE {

    private Node head;
    private int size;

    public void add(Kid kid){
        if(head != null){
            Node temp = head;
            while(temp.getNext() !=null)
            {
                temp = temp.getNext();
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

    public void addToStart(Kid kid){
        if(head !=null)
        {
            Node newNode = new Node(kid);
            newNode.setNext(head);
            head = newNode;
        }
        else {
            head = new Node(kid);
        }
        size++;
    }

    public void orderBoysToStart(){
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
    }

    public void changeExtremes(){
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

    }

    public int getCountKidsByLocationCode(String code){
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
        return count;
    }

    public int getCountKidsByDeptoCode(String code){
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
        return count;
    }


    public void invert() {
        if (this.head != null) {
            ListSE listCP = new ListSE();
            Node temp = this.head;
            while (temp != null) {
                listCP.addToStart(temp.getData());
                temp = temp.getNext();
            }
            this.head = listCP.getHead();
        }
    }

    public void boyStartGirlsLast(){
        ListSE listCopy = new ListSE();
        Node temp = this.head;
        while (temp != null){
            if (temp.getData().getGender() == 'M'){
                listCopy.add(temp.getData());
            }
            temp = temp.getNext();
        }
        temp = this.head;

        while (temp != null){
            if (temp.getData().getGender() == 'F'){
                listCopy.add((temp.getData()));
            }
            temp = temp.getNext();
        }
        this.head = listCopy.getHead();
    }

    public void boyThenGirl(){
        ListSE listMale = new ListSE();
        ListSE listFemale = new ListSE();
        Node temp = this.head;
        while (temp != null){
            if(temp.getData().getGender()=='M'){
                listMale.add(temp.getData());
            }
            if(temp.getData().getGender()=='F'){
                listFemale.add(temp.getData());
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

    public void deleteByAge (int age){
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
            }
        }
    }

    public float averageAge(){

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
            return (float) 0;
        }
    }

    public void sendBottomByLetter(char initial){


        ListSE sendBottom = new ListSE();
        Node temp = this.head;

        while (temp != null){
            if (temp.getData().getName().charAt(0) != Character.toUpperCase(initial)){
                sendBottom.add(temp.getData());
            }
            temp = temp.getNext();
        }

        temp = this.head;

        while (temp != null){
            if (temp.getData().getName().charAt(0) == Character.toUpperCase(initial)){
                sendBottom.add(temp.getData());
            }
            temp = temp.getNext();
        }

        this.head = sendBottom.getHead();
    }

    public int rangeByAge(int min,int max){
        Node temp = head;
        int counter = 0;
        while (temp != null){
            if (temp.getData().getAge() >= min && temp.getData().getAge()<= max){
                counter++;

            }
            temp = temp.getNext();

        }
        return counter;
    }

    public void forwardPositions(String identification, int positions) {
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
                    }
                }
            }
        }
        return;
    }

    public void afterwardsPositions(String identification, int positions){
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
                }
            }
            return;


        }
    }

    private void addByPosition(Kid data, int i) {
    }
}


