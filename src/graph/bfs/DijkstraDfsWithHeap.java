package graph.bfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import graph.AdjacencyVertex;
import struc.BinaryHeap;
import struc.BinaryHeapFactory;
import struc.swapOrderRepsonder;

public class DijkstraDfsWithHeap implements swapOrderRepsonder{

    private static final int INFINITE = 1024;

    private List<VertexWithDist> vertexDistList;
    private VertexWithDist startVertexDist;

    private HashMap<VertexWithDist, VertexWithDist> preVertex;
    private HashMap<VertexWithDist, Integer> vertexDist;

    private BinaryHeap binaryHeap;

    public DijkstraDfsWithHeap(List<AdjacencyVertex> vertexList, AdjacencyVertex startVertex) {
        vertexDistList = new LinkedList<VertexWithDist>();
        for(AdjacencyVertex vertex : vertexList) {
            VertexWithDist vertexWithDist = new VertexWithDist();
            vertexWithDist.vertex = vertex;
            vertexDistList.add(vertexWithDist);

            if(vertex == startVertex) {
                startVertexDist = vertexWithDist;
            }
        }

    }

    public HashMap<VertexWithDist, Integer> execute() {
        // 存放该顶点最短路径的上个顶点
        preVertex = new HashMap<VertexWithDist, VertexWithDist>();
        // 存放startVertex到该顶点的最短路径数值
        vertexDist = new HashMap<VertexWithDist, Integer>();

        binaryHeap = new BinaryHeapFactory().getMinBinaryHeap(this);

        int l_posi = 0;
        // 填充默认数值
        for(VertexWithDist vertexWithDist : vertexDistList) {
            preVertex.put(vertexWithDist, startVertexDist);
            vertexDist.put(vertexWithDist, INFINITE);
        }

        vertexDist.remove(startVertexDist);
        vertexDist.put(startVertexDist, 0);

        // 松弛操作更新其余各顶点到startVertex的最短路径数值
        makeHeap();

        while(binaryHeap.isEmpty()) {
            VertexWithDist vertexWithDist = (VertexWithDist)binaryHeap.deleteTopObject();
            // 确定最短路径
            vertexDist.put(vertexWithDist, vertexWithDist.fromSourceLen);

            // 松弛操作
            for(AdjacencyVertex vertex : vertexWithDist.vertex.getAdjacentMapWithWeight().keySet()) {
                int pathDisPassCur = vertexWithDist.fromSourceLen + vertexWithDist.vertex.getAdjacentMapWithWeight().get(vertex);
                // TODO 如何找到对应vertex的vertexWithDist 效率低
                // getAdjacentMapWithWeight中改成vertexWIthDist最为重要
//                if(vertex.dist > pathDisPassCur) {
//                    tempDists[index] = pathDisPassCur;
//                    preVertex.put(vertex, vertexU);
//                }
            }
        }

        // 打印结果
        for(VertexWithDist vertexWithDist : vertexDist.keySet()) {
            if(vertexWithDist == startVertexDist) {
                continue;
            }
            System.out.print(vertexDist.get(vertexWithDist) + " : ");

            StringBuilder reversePath =  new StringBuilder();
            VertexWithDist reverseVertex = vertexWithDist;
            do {
                reversePath.insert(0, " -> " + reverseVertex.vertex.getDesc());
                reverseVertex = preVertex.get(reverseVertex);
            }
            while(preVertex.get(reverseVertex) != startVertexDist);
            reversePath.insert(0, startVertexDist.vertex.getDesc());
            System.out.println(reversePath);
        }

        return vertexDist;
    }

    private void makeHeap() {
        int count = 0;
        for(VertexWithDist vertexWithDist : vertexDistList) {
            vertexWithDist.fromSourceLen = INFINITE;
            vertexWithDist.position = count;

            binaryHeap.insertObject(vertexWithDist);
            count ++;
        }
    }

    @Override
    public void swapOrder(Comparable a, int curPosA, Comparable b, int curPosB) {
        if(a instanceof VertexWithDist &&
                b instanceof VertexWithDist) {
            ((VertexWithDist)a).position = curPosA;
            ((VertexWithDist)b).position = curPosB;
        }
    }

    class VertexWithDist implements Comparable{
        AdjacencyVertex vertex;

        // 从startVertex到vertex的路径长度
        int fromSourceLen;

        // 在堆中的位置
        int position;

        @Override
        public int compareTo(Object o) {
            if(o instanceof VertexWithDist) {
                return fromSourceLen - ((VertexWithDist)o).fromSourceLen;
            }

            return 0;
        }
    }

}
