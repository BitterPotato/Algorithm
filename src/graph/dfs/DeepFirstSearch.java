package graph.dfs;

import java.util.LinkedList;
import java.util.List;

import graph.AdjacencyVertex;

/**
 * MYNOTICE:
 * 	use boolean instead of visitedList to express visit condition will be better
 * @author Yang Weijie
 *
 */
public abstract class DeepFirstSearch {
    private List<AdjacencyVertex> visitedVertexList;

    public void execute(List<AdjacencyVertex> vertexList) {
        visitedVertexList = new LinkedList<AdjacencyVertex>();

        for(AdjacencyVertex vertex : vertexList) {
            if(!visitedVertexList.contains(vertex)) {
                explore(vertex);
            }
        }
    }

    private void explore(AdjacencyVertex vertex) {
        visitedVertexList.add(vertex);

        previsit(vertex);

        for(AdjacencyVertex adjVertex : vertex.getAdjacentList()) {
            if(!visitedVertexList.contains(adjVertex)) {
                explore(adjVertex);
            }
            else {
                // 判断回边：从一个顶点指向其祖先的边，其祖先必定已被访问
                judgeBackEdge(vertex, adjVertex);
            }
        }

        postvisit(vertex);
    }

    /**
     * do something when the vertex was firstly visited
     * @param vertex
     */
    protected abstract void previsit(AdjacencyVertex vertex);

    /**
     * judge if a back edge exists or not
     * @param vertex
     * @param adjVertex
     */
    protected abstract void judgeBackEdge(AdjacencyVertex vertex, AdjacencyVertex adjVertex);
    /**
     * do something when the vertex was lastly visited
     * @param vertex
     */
    protected abstract void postvisit(AdjacencyVertex vertex);
}
