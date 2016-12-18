package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import graph.dfs.DeepFirstSearch;
import graph.dfs.DfsSolveTopoSort;
import graph.dfs.DfsWithAbility;

//test data 1:
//1,2,3,4,5,6,7
//1 2,3,4
//2 4,5
//3 6
//4 3,5,6,7
//5 7
//7 6
//test data 2 (for graph circle problem):
//1,2,3,4,5,6,7
//1 2,3,4
//2 4,5
//3 6
//4 3,5,6,7
//5 7
//7 1,6
public class MainEntry {

    public static void main(String[] argc) {
        Scanner scanner = new Scanner(System.in);
        // 读取所有顶点
        List<AdjacencyVertex> vertexList = new ArrayList<AdjacencyVertex>();
        String firstLine = scanner.nextLine();
        String[] vertexes = firstLine.split(",");
        for(int i=0; i<vertexes.length; i++) {
            vertexList.add(new AdjacencyVertex(vertexes[i]));
        }
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.equals("")) {
                break;
            }
            String vertexDesc = line.split(" ")[0];
            String[] adjacentVertexes = line.split(" ")[1].split(",");

            AdjacencyVertex toVertex = AdjacencyVertexUtil.findVertexWithDesc(vertexDesc, vertexList);
            for(int index = 0; index < adjacentVertexes.length; index ++) {
                AdjacencyVertex vertex = AdjacencyVertexUtil.findVertexWithDesc(adjacentVertexes[index], vertexList);
                AdjacencyVertexAdapter.getInstance().addAdjacencyVertexTo(toVertex, vertex);
                // 增加入度
                AdjacencyVertexAdapter.getInstance().increaseInDegree(vertex);
            }
        }

        // -------拓扑排序问题------
//        new TopologySort().execute(vertexList);
//        DfsSolveTopoSort dfsSolveTopoSort = new DfsWithAbility();
//        dfsSolveTopoSort.topoSort(vertexList);

        // -------迷宫探索问题------
//        new ExploreMaze().execute(vertexList, "1", "7");

        // -------有向图检测环-------
        DeepFirstSearch dfs = new DfsWithAbility();
        dfs.execute(vertexList);
    }
}
