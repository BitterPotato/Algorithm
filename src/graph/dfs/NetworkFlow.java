package graph.dfs;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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

// how to input

public class NetworkFlow {

    class FlowVertex {
        int toVertex;
        // 容量
        int cap;
        // 结果
        int result;
        // 剩余
        int remain;
        // 反向
        int rev;
    }

    class adjVertex{
        List<FlowVertex> list;

        adjVertex() {
            list = new ArrayList<FlowVertex>();
        }
    }

    adjVertex[] g_graph;

    public static void main(String[] args) {
        NetworkFlow flow = new NetworkFlow();

        flow.init(System.in);
        System.out.println(flow.max());
    }

    public void init(InputStream inputStream) {
        Scanner scanner = new Scanner(System.in);
        int edgeSize = scanner.nextInt();
        int pointSize = scanner.nextInt();
        g_graph = new adjVertex[pointSize];
        while(edgeSize-- > 0) {
            int self = scanner.nextInt();
            int to = scanner.nextInt();
            int capa = scanner.nextInt();
            addVertex(self, to, capa);
        }

    }
    
    public int max() {
    	int max = 0;
    	// find a path
    	for(FlowVertex flowVertex : g_graph[0].list) {
    		int num = dfs(flowVertex, max);
    		if(num > 0) {
    			max += num; 
    		}
    		else {
    			return max;
    		}
    	}
    	return max;
    }
    
    private int dfs(FlowVertex vertex, int flowNum) {
//    	if()
    	return 0;
    }

    private void addVertex(int self, int to, int capa) {
        FlowVertex flowVertex = new FlowVertex();
        flowVertex.toVertex = to;
        flowVertex.cap = capa;
        flowVertex.result = 0;
        flowVertex.remain = capa;
        flowVertex.rev = 0;

        g_graph[self].list.add(flowVertex);
    }

}
