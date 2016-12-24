package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import graph.bfs.BreadthFirstSearch;
import graph.bfs.DijkstraDfsWithArray;
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
        // ·��Ȩֵ���������
        Random random = new Random(System.currentTimeMillis());

        Scanner scanner = new Scanner(System.in);
        // ��ȡ���ж���
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
                AdjacencyVertexAdapter.getInstance().addAdjacencyVertexWithWeightTo(toVertex, vertex, random.nextInt(20) + 1);
                // �������
                AdjacencyVertexAdapter.getInstance().increaseInDegree(vertex);
            }
        }

        // -------������������------
//        new TopologySort().execute(vertexList);
//        DfsSolveTopoSort dfsSolveTopoSort = new DfsWithAbility();
//        dfsSolveTopoSort.topoSort(vertexList);

        // -------�Թ�̽������------
//        new ExploreMaze().execute(vertexList, "1", "7");

        // -------����ͼ��⻷-------
//        DeepFirstSearch dfs = new DfsWithAbility();
//        dfs.execute(vertexList);

        // ------�����������--------
//        BreadthFirstSearch bfs = new BreadthFirstSearch();
//        bfs.printBfsResult(bfs.execute(vertexList, vertexList.get(0)));

        // ------ ��Դ���·�� -------
        DijkstraDfsWithArray dbfs = new DijkstraDfsWithArray();
        dbfs.execute(vertexList, vertexList.get(0));

//        BellmanFord bf = new BellmanFord();
//        bf.execute(vertexList, vertexList.get(0));
//
//        TopoSortSolveShortPath tsssp = new TopoSortSolveShortPath();
//        tsssp.execute(vertexList, vertexList.get(0));

        // ------- ��Դ�·�� -------
        TopoSortSolveLongPath tsslp = new TopoSortSolveLongPath();
        tsslp.execute(vertexList, vertexList.get(0));
    }

}
