package co.edu.umanizales.tads_prog4.model;

import lombok.Data;

@Data
public class Node {

    private Kid data;
    private Node next;
    private Node prev;

    public Node(Kid data) {
        this.data = data;
    }

}
