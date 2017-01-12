package graph.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//8 6
//0 1 3
//0 2 2
//1 2 1
//1 3 3
//1 4 4
//2 4 4
//3 5 2
//4 5 3
public class NetWorkFlowEk {

    // 邻接矩阵
    private int[][] vertexes;
    //
    private int[] preVertex;
    //
    private int[] visited;

    private static final int INFINITE = 1024;

    public int maxflow(int s, int t) {
        int flow = 0;
        while(true) {
            boolean findPath = false;

            for(int index = 0; index < visited.length; index++) {
                visited[index] = 0;
            }
            visited[s] = 1;
            Queue<Integer> vertexQueue = new LinkedList<Integer>();
            vertexQueue.offer(s);

            // 找到一条s到t的路径
            while(!vertexQueue.isEmpty()) {
                int vertex = vertexQueue.poll();
                for(int toVertex = 0; toVertex < vertexes[vertex].length; toVertex ++) {
                    // 未访问过的邻接顶点
                    if(vertexes[vertex][toVertex] > 0 && visited[toVertex] == 0) {
                        preVertex[toVertex] = vertex;
                        visited[toVertex] = 1;

                        if(toVertex == t) {
                            vertexQueue.clear();
                            findPath = true;
                            break;
                        }
                        else {
                            vertexQueue.offer(toVertex);
                        }
                    }
                }
            }

            if(!findPath) {
                return flow;
            }

            // traverse the flow path and work out the capacity
            int flowPathCapa = INFINITE;
            for(int temp = t; temp != s; temp = preVertex[temp]) {
                int edgeCapa = vertexes[(preVertex[temp])][temp];
                flowPathCapa = flowPathCapa >  edgeCapa ? edgeCapa : flowPathCapa;
            }

            // solve the results
            flow += flowPathCapa;
            for(int temp = t; temp != s; temp = preVertex[temp]) {
                vertexes[preVertex[temp]][temp] -= flowPathCapa;
                // 反向弧
                vertexes[temp][preVertex[temp]] += flowPathCapa;
            }
        }
    }


    public void init(int pointSize) {
        vertexes = new int[pointSize][pointSize];
        preVertex = new int[pointSize];
        visited = new int[pointSize];
    }

    public void addEdge(int self, int to, int capa) {
        vertexes[self][to] = capa;
    }
//    public void init(InputStream inputStream) {
//        Scanner scanner = new Scanner(System.in);
//        int edgeSize = scanner.nextInt();
//        int pointSize = scanner.nextInt();
//
//        vertexes = new int[pointSize][pointSize];
//        preVertex = new int[pointSize];
//
//        while(edgeSize-- > 0) {
//            int self = scanner.nextInt();
//            int to = scanner.nextInt();
//            int capa = scanner.nextInt();
//            vertexes[self][to] = capa;
//        }
//    }

    public static void main(String[] args) {
//        NetWorkFlowEk netWorkFlow = new NetWorkFlowEk();
//        netWorkFlow.init(System.in);
//        int maxFlow = netWorkFlow.maxflow(0, 5);
//        System.out.println(maxFlow);

        Scanner scanner = new Scanner(System.in);
        int edgeSize = scanner.nextInt();
        int pointSize = scanner.nextInt();

        NetWorkFlowEk netWorkFlow = new NetWorkFlowEk();
        netWorkFlow.init(pointSize);

        while(edgeSize-- > 0) {
            int self = scanner.nextInt();
            int to = scanner.nextInt();
            int capa = scanner.nextInt();
            netWorkFlow.addEdge(self, to, capa);
        }

        int maxFlow = netWorkFlow.maxflow(0, 5);
        System.out.println(maxFlow);
    }
}
