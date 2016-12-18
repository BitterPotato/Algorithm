package graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Yang Weijie
 *
 */
public class TopologySort {
    /**
    * find the vertex whose indegree is zero and return
    * @param vertexList
    * @return
    */
    private Queue<AdjacencyVertex> findAllIndegreeZeroVertexes(List<AdjacencyVertex> vertexList) {
        Queue<AdjacencyVertex> vertexQueue = new LinkedList<AdjacencyVertex>();
        for(AdjacencyVertex vertex : vertexList) {
            if(vertex.getInDegree() == 0) {
                vertexQueue.offer(vertex);
            }
        }
        return vertexQueue;
    }

    public void execute(List<AdjacencyVertex> vertexList){
        final int NUM_VERTEXES = vertexList.size();
        Queue<AdjacencyVertex> vertexQueue = findAllIndegreeZeroVertexes(vertexList);
        if(vertexQueue.size() == 0) {
            throw new CycleFoundException();
        }

        for(int count = 0; count < NUM_VERTEXES; count ++) {
            AdjacencyVertex vertex = vertexQueue.poll();
            vertex.setTopoNum(count);
            System.out.println(vertex.getDesc());
            for(AdjacencyVertex adjVertex : vertex.getAdjacentList()) {
                AdjacencyVertexAdapter.getInstance().decreaseInDegree(adjVertex);
                if(adjVertex.getInDegree() == 0) {
                    vertexQueue.offer(adjVertex);
                }
            }
        }
    }

    /**
     * the exception which indicates a circle was found
     * @author Yang Weijie
     *
     */
    class CycleFoundException extends RuntimeException {

    }

}
