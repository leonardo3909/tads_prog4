package co.edu.umanizales.tads_prog4.model;

import lombok.Data;

@Data
public class NodeDE {

    private Pet data2;
    private NodeDE nextDE;
    private NodeDE prev;

    public NodeDE(Pet data) {
        this.data2 = data;
    }
}
