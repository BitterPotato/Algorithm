package graph;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * representation of graphs:
 * 	the adjacency list
 * @author Yang Weijie
 *
 */
/**
 * @author Yang Weijie
 *
 */
public class AdjacencyVertex {
    // -------- Extra Data -----
    private String desc;
    private int inDegree;
    private int topoNum;

    // -------- Adjacent list ----
    /**
     * Default Adjacent list capacity.
     */
    private static final int DEFAULT_CAPACITY = 5;
    private ArrayList<AdjacencyVertex> adjacentList;
    private HashMap<AdjacencyVertex, Integer> adjacentMapWithWeight;

    public AdjacencyVertex() {
        adjacentList = new ArrayList<AdjacencyVertex>(DEFAULT_CAPACITY);
        adjacentMapWithWeight = new HashMap<AdjacencyVertex, Integer>(DEFAULT_CAPACITY);
    }

    public AdjacencyVertex(String desc) {
        this();
        this.desc = desc;
    }

    public int getInDegree() {
        return inDegree;
    }

    public void setInDegree(int inDegree) {
        this.inDegree = inDegree;
    }

    public int getTopoNum() {
        return topoNum;
    }

    public void setTopoNum(int topoNum) {
        this.topoNum = topoNum;
    }

    public ArrayList<AdjacencyVertex> getAdjacentList() {
        return adjacentList;
    }

    public void setAdjacentList(ArrayList<AdjacencyVertex> adjacentList) {
        this.adjacentList = adjacentList;
    }

    public HashMap<AdjacencyVertex, Integer> getAdjacentMapWithWeight() {
        return adjacentMapWithWeight;
    }

    public void setAdjacentMapWithWeight(HashMap<AdjacencyVertex, Integer> adjacentMapWithWeight) {
        this.adjacentMapWithWeight = adjacentMapWithWeight;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}
