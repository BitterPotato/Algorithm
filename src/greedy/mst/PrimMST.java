package greedy.mst;

import java.util.HashMap;
import java.util.List;

import graph.AdjacencyVertex;
import graph.bfs.BreadthFirstSearch;

public class PrimMST extends BreadthFirstSearch {

    private static final int INFINITE = 1024;
    private int INVALID = -1;

    public HashMap<AdjacencyVertex, Integer> execute(List<AdjacencyVertex> vertexList, AdjacencyVertex startVertex) {
        // 存放该顶点最短路径的上个顶点
        HashMap<AdjacencyVertex, AdjacencyVertex> preVertex = new HashMap<AdjacencyVertex, AdjacencyVertex>();
        // 存放startVertex到该顶点的最短路径数值
        HashMap<AdjacencyVertex, Integer> vertexDist = new HashMap<AdjacencyVertex, Integer>();

        // 填充默认数值
        for (AdjacencyVertex vertex : vertexList) {
            vertexDist.put(vertex, INFINITE);
        }

        vertexDist.remove(startVertex);
        vertexDist.put(startVertex, 0);

        // 其余各顶点到startVertex的最短路径数值（待松弛操作更新）
        int[] tempDists = makeArray(vertexList, startVertex);

        while (!isEmpty(tempDists)) {
            int curMinIndex = getArrayMin(tempDists);
            int curVerDis = tempDists[curMinIndex];
            // 剔除该顶点
            tempDists[curMinIndex] = INVALID;

            AdjacencyVertex vertexU = vertexList.get(curMinIndex);
            // 确定最短路径
            vertexDist.put(vertexU, curVerDis);

            // 松弛操作
            for (AdjacencyVertex vertex : vertexU.getAdjacentMapWithWeight().keySet()) {
                int index = vertexList.indexOf(vertex);
                int pathDisPassCur = vertexU.getAdjacentMapWithWeight().get(vertex);
                if (tempDists[index] != INVALID && tempDists[index] > pathDisPassCur) {
                    tempDists[index] = pathDisPassCur;
                    preVertex.put(vertex, vertexU);
                }
            }
        }

        // 打印结果
        for (AdjacencyVertex vertex : vertexDist.keySet()) {
            if (vertex == startVertex) {
                continue;
            }
            System.out.print(vertexDist.get(vertex) + " : ");

            StringBuilder reversePath = new StringBuilder();
            AdjacencyVertex reverseVertex = vertex;
            do {
                reversePath.insert(0, " -> " + reverseVertex.getDesc());
                reverseVertex = preVertex.get(reverseVertex);
            } while (reverseVertex != startVertex);
            reversePath.insert(0, startVertex.getDesc());
            System.out.println(reversePath);
        }

        return vertexDist;
    }

    private int[] makeArray(List<AdjacencyVertex> vertexList, AdjacencyVertex startVertex) {
        int[] dist = new int[vertexList.size()];

        for (int index = 0; index < dist.length; index++) {
            dist[index] = INFINITE;
        }

        // 源点到源点的路径为0
        int startIndex = vertexList.indexOf(startVertex);
        dist[startIndex] = 0;

        return dist;
    }

    /**
    * 返回数组当前距离s最小的顶点的索引
    *
    * @param array
    * @return
    */
    private int getArrayMin(int[] dist) {
        int minIndex = -1;
        for (int index = 0; index < dist.length; index++) {
            if (dist[index] != INVALID && (minIndex == -1 || dist[index] < dist[minIndex])) {
                minIndex = index;
            }
        }
        return minIndex;
    }

    private boolean isEmpty(int[] dist) {
        for (int index = 0; index < dist.length; index++) {
            if (dist[index] != INVALID && dist[index] != INFINITE) {
                return false;
            }
        }
        return true;
    }

}
