package graph.dfs;

import java.util.List;
import java.util.Stack;

import graph.AdjacencyVertex;

public class DfsWithAbility extends DeepFirstSearch implements DfsSolveTopoSort{

    /**
    * indicate the clock information when a vertex was visited
    */
    private int clock = 0;

    /**
     * record the clock of pre/post visited of every vertex
     */
    private int[] preClocks;
    private int[] postClocks;

    private List<AdjacencyVertex> localVertexList;

    private boolean toPrintTopoSortResult = true;
    private Stack<AdjacencyVertex> topoSortVertexStack;

    @Override
    public void execute(List<AdjacencyVertex> vertexList) {
        this.toPrintTopoSortResult = false;

        this.executeProcess(vertexList);

        System.out.println("ok");
    }

    @Override
    public void topoSort(List<AdjacencyVertex> vertexList) {
        this.toPrintTopoSortResult = true;
        topoSortVertexStack = new Stack<AdjacencyVertex>();

        this.executeProcess(vertexList);

        printTopoSortResult();
    }

    /**
     *
     * @param vertexList
     */
    private void executeProcess(List<AdjacencyVertex> vertexList) {
        this.toPrintTopoSortResult = toPrintTopoSortResult;
        if(toPrintTopoSortResult) {

        }

        int size = vertexList.size();
        preClocks = new int[size];
        postClocks = new int[size];

        localVertexList = vertexList;
        super.execute(vertexList);
    }

    @Override
    protected void previsit(AdjacencyVertex vertex) {
        preClocks[localVertexList.indexOf(vertex)] = clock;
        clock++;
    }

    @Override
    protected void judgeBackEdge(AdjacencyVertex vertex, AdjacencyVertex adjVertex) {
        int index = localVertexList.indexOf(vertex);
        int adjIndex = localVertexList.indexOf(adjVertex);
        // the clock condition which indicates the back edge
        if(postClocks[adjIndex] == 0
                && preClocks[index] > preClocks[adjIndex]) {
            throw new BackEdgeFoundException(vertex.getDesc(), adjVertex.getDesc());
        }
    }
    @Override
    protected void postvisit(AdjacencyVertex vertex) {
        if(toPrintTopoSortResult) {
            topoSortVertexStack.push(vertex);
        }
        postClocks[localVertexList.indexOf(vertex)] = clock;
        clock++;
    }

    /**
     * that is, output the postClocks in the descend order
     * > the order is recored in the stack
     */
    private void printTopoSortResult() {
        while(!topoSortVertexStack.isEmpty()) {
            AdjacencyVertex vertex = topoSortVertexStack.pop();
            System.out.println(vertex.getDesc());
        }
    }

    class BackEdgeFoundException extends RuntimeException {
        BackEdgeFoundException(String startVertexDesc, String endVertexDesc) {
            System.out.println(startVertexDesc + " -> " + endVertexDesc + "form a back edge");
        }
    }
}
