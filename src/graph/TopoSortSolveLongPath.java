package graph;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import graph.dfs.DfsSolveTopoSort;
import graph.dfs.DfsWithAbility;

public class TopoSortSolveLongPath {

private static final int INFINITE = 1024;

    public HashMap<AdjacencyVertex, Integer> execute(List<AdjacencyVertex> vertexList, AdjacencyVertex startVertex) {

        HashMap<AdjacencyVertex, Integer>  dists = new HashMap<AdjacencyVertex, Integer>();
        HashMap<AdjacencyVertex, AdjacencyVertex> preVertex = new HashMap<AdjacencyVertex, AdjacencyVertex>();

        for(AdjacencyVertex vertex : vertexList) {
            dists.put(vertex, INFINITE);
            // do not need to init preVertex
        }
        dists.put(startVertex, 0);

        DfsSolveTopoSort dfsSolveTopoSort = new DfsWithAbility();
        Stack<AdjacencyVertex> topoSortVertexStack = dfsSolveTopoSort.topoSort(vertexList);

        while(!topoSortVertexStack.isEmpty()) {
            AdjacencyVertex vertex = topoSortVertexStack.pop();
            for(AdjacencyVertex adjVertex : vertex.getAdjacentMapWithWeight().keySet()) {
                // 计入每条边权值对应的负值
                int includeVertexLen = dists.get(vertex) - vertex.getAdjacentMapWithWeight().get(adjVertex);
                if(dists.get(adjVertex) > includeVertexLen) {
                    dists.put(adjVertex, includeVertexLen);
                    preVertex.put(adjVertex, vertex);
                }
            }
        }

        // 打印结果
        for(AdjacencyVertex vertex : dists.keySet()) {
            if(vertex == startVertex) {
                continue;
            }
            System.out.print(-dists.get(vertex) + " : ");

            StringBuilder reversePath =  new StringBuilder();
            AdjacencyVertex reverseVertex = vertex;
            do {
                reversePath.insert(0, " -> " + reverseVertex.getDesc());
                reverseVertex = preVertex.get(reverseVertex);
            }
            while(reverseVertex != startVertex);
            reversePath.insert(0, startVertex.getDesc());
            System.out.println(reversePath);
        }

        return dists;
    }
}
