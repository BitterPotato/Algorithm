package graph.bfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import graph.AdjacencyVertex;

public class BreadthFirstSearch {

    private static final int INFINITE = 1024;

    public HashMap<AdjacencyVertex, Integer> execute(List<AdjacencyVertex> vertexList, AdjacencyVertex startVertex) {
        HashMap<AdjacencyVertex, Integer> vertexDist = new HashMap<AdjacencyVertex, Integer>();

        for(AdjacencyVertex vertex : vertexList) {
            vertexDist.put(vertex, INFINITE);
        }

        vertexDist.remove(startVertex);
        vertexDist.put(startVertex, 0);

        Queue<AdjacencyVertex> vertexQueue = new LinkedList<AdjacencyVertex>();
        vertexQueue.offer(startVertex);

        while(!vertexQueue.isEmpty()) {
            AdjacencyVertex vertexU = vertexQueue.poll();
            for(AdjacencyVertex vertex : vertexU.getAdjacentList()) {
                if(vertexDist.get(vertex) == INFINITE) {
                    vertexQueue.offer(vertex);

                    vertexDist.remove(vertex);
                    vertexDist.put(vertex, vertexDist.get(vertexU) + 1);
                }
            }
        }

        return vertexDist;
    }

    public void printBfsResult(HashMap<AdjacencyVertex, Integer> bfsResult) {
        for(AdjacencyVertex vertex : bfsResult.keySet()) {
            System.out.println(vertex.getDesc() + " : " + bfsResult.get(vertex));
        }
    }
}
