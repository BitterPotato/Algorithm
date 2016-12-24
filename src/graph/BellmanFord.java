package graph;

import java.util.HashMap;
import java.util.List;

public class BellmanFord {

    private static final int INFINITE = 1024;

    public HashMap<AdjacencyVertex, Integer> execute(List<AdjacencyVertex> vertexList, AdjacencyVertex startVertex) {
        int iteration = vertexList.size() - 1;

        HashMap<AdjacencyVertex, Integer>  dists = new HashMap<AdjacencyVertex, Integer>();
        HashMap<AdjacencyVertex, AdjacencyVertex> preVertex = new HashMap<AdjacencyVertex, AdjacencyVertex>();

        for(AdjacencyVertex vertex : vertexList) {
            dists.put(vertex, INFINITE);
            // do not need to init preVertex
        }
        dists.put(startVertex, 0);

        // 记录该轮迭代是否发生松弛操作
        boolean turnRelaxed = true;
        for(int index = 0; index < iteration && turnRelaxed; index++) {
            turnRelaxed = false;
            // 两个for累计|E|次
            for(AdjacencyVertex vertex : vertexList) {
                for(AdjacencyVertex adjVertex : vertex.getAdjacentMapWithWeight().keySet()) {
                    int includeVertexLen = dists.get(vertex) + vertex.getAdjacentMapWithWeight().get(adjVertex);
                    if(dists.get(adjVertex) > includeVertexLen) {
                        dists.put(adjVertex, includeVertexLen);
                        preVertex.put(adjVertex, vertex);
                        turnRelaxed = true;
                    }
                }
            }

        }

        // 打印结果
        for(AdjacencyVertex vertex : dists.keySet()) {
            if(vertex == startVertex) {
                continue;
            }
            System.out.print(dists.get(vertex) + " : ");

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
