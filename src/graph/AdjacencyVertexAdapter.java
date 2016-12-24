package graph;

public class AdjacencyVertexAdapter {

    private static AdjacencyVertexAdapter adjacencyVertexAdapter = new AdjacencyVertexAdapter();

    private AdjacencyVertexAdapter() {}

    public static AdjacencyVertexAdapter getInstance() {
        return adjacencyVertexAdapter;
    }

    /**
     * add the adjacent vertex to the adjacent list of toVertex
     * @param toVertex
     * @param vertex
     */
    public void addAdjacencyVertexTo(AdjacencyVertex toVertex, AdjacencyVertex vertex) {
        if(toVertex != null && vertex != null) {
            if(!toVertex.getAdjacentList().contains(vertex)) {
                toVertex.getAdjacentList().add(vertex);
            }
        }
    }

    /**
     * the version with weight
     * @param toVertex
     * @param vertex
     * @param weight
     */
    public void addAdjacencyVertexWithWeightTo(AdjacencyVertex toVertex, AdjacencyVertex vertex, int weight) {
        if(toVertex != null && vertex != null) {
            if(!toVertex.getAdjacentMapWithWeight().keySet().contains(vertex)) {
                toVertex.getAdjacentMapWithWeight().put(vertex, weight);
            }
        }
    }

    /**
    * @param vertex
    * @return the decreased inDegree
    */
    public int decreaseInDegree(AdjacencyVertex vertex) {
        int curDegree = vertex.getInDegree() - 1;
        vertex.setInDegree(curDegree);
        return curDegree;
    }

    public int increaseInDegree(AdjacencyVertex vertex) {
        int curDegree = vertex.getInDegree() + 1;
        vertex.setInDegree(curDegree);
        return curDegree;
    }

    public void removeAdjacencyVertexFrom(AdjacencyVertex fromVertex, AdjacencyVertex vertex) {
        if(fromVertex != null && vertex != null) {
            if(fromVertex.getAdjacentList().contains(vertex)) {
                fromVertex.getAdjacentList().remove(vertex);
            }
        }
    }
}
