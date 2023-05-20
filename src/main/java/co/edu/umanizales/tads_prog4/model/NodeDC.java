package co.edu.umanizales.tads_prog4.model;

import lombok.Data;

@Data
public class NodeDC {

    private Pet dataDC;
    private NodeDC nextDC;
    private NodeDC prev;

    public NodeDC(Pet data) {
        this.dataDC = data;
    }
}
