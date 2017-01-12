package greedy.mst;

import java.util.HashMap;
import java.util.List;

import graph.AdjacencyVertex;
import graph.bfs.BreadthFirstSearch;

public class PrimMST extends BreadthFirstSearch {

    private static final int INFINITE = 1024;
    private int INVALID = -1;

    public HashMap<AdjacencyVertex, Integer> execute(List<AdjacencyVertex> vertexList, AdjacencyVertex startVertex) {
        // ��Ÿö������·�����ϸ�����
        HashMap<AdjacencyVertex, AdjacencyVertex> preVertex = new HashMap<AdjacencyVertex, AdjacencyVertex>();
        // ���startVertex���ö�������·����ֵ
        HashMap<AdjacencyVertex, Integer> vertexDist = new HashMap<AdjacencyVertex, Integer>();

        // ���Ĭ����ֵ
        for (AdjacencyVertex vertex : vertexList) {
            vertexDist.put(vertex, INFINITE);
        }

        vertexDist.remove(startVertex);
        vertexDist.put(startVertex, 0);

        // ��������㵽startVertex�����·����ֵ�����ɳڲ������£�
        int[] tempDists = makeArray(vertexList, startVertex);

        while (!isEmpty(tempDists)) {
            int curMinIndex = getArrayMin(tempDists);
            int curVerDis = tempDists[curMinIndex];
            // �޳��ö���
            tempDists[curMinIndex] = INVALID;

            AdjacencyVertex vertexU = vertexList.get(curMinIndex);
            // ȷ�����·��
            vertexDist.put(vertexU, curVerDis);

            // �ɳڲ���
            for (AdjacencyVertex vertex : vertexU.getAdjacentMapWithWeight().keySet()) {
                int index = vertexList.indexOf(vertex);
                int pathDisPassCur = vertexU.getAdjacentMapWithWeight().get(vertex);
                if (tempDists[index] != INVALID && tempDists[index] > pathDisPassCur) {
                    tempDists[index] = pathDisPassCur;
                    preVertex.put(vertex, vertexU);
                }
            }
        }

        // ��ӡ���
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

        // Դ�㵽Դ���·��Ϊ0
        int startIndex = vertexList.indexOf(startVertex);
        dist[startIndex] = 0;

        return dist;
    }

    /**
    * �������鵱ǰ����s��С�Ķ��������
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
