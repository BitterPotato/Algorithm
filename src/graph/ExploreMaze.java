package graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * TODO ExploreMaze can extend DeepFirstSearch
 * @author Yang Weijie
 *
 */
public class ExploreMaze {
    /**
    *
    * @param vertexList
    * @param startDesc the description of start vertex
    * @param endDesc the description of end vertex
    * @return whether a path will be found or not
    */
    public boolean execute(List<AdjacencyVertex> vertexList, String startDesc, String endDesc){
        AdjacencyVertex startVertex = AdjacencyVertexUtil.findVertexWithDesc(startDesc, vertexList);
        AdjacencyVertex endVertex = AdjacencyVertexUtil.findVertexWithDesc(endDesc, vertexList);
        if(startVertex == null || endVertex == null) {
            throw new VertexNotFoundException();
        }

        Stack<AdjacencyVertex> vertexStack = new Stack<AdjacencyVertex>();
        vertexStack.push(startVertex);
        List<AdjacencyVertex> visitedVertexList = new LinkedList<AdjacencyVertex>();

        while(true) {
            List<AdjacencyVertex> adjVertexList = vertexStack.peek().getAdjacentList();
            AdjacencyVertex unVisitedVertex = findUnVisitedVertex(adjVertexList, visitedVertexList);
            if(unVisitedVertex == null) {
                // it contains the start vertex, so its size must be bigger than one
                if(vertexStack.size() > 1) {
                    vertexStack.pop();
                }
                else {
                    return false;
                }
            }
            else {
                if(unVisitedVertex != endVertex) {
                    visitedVertexList.add(unVisitedVertex);
                    vertexStack.push(unVisitedVertex);
                }
                else {
//                    vertexStack.push(unVisitedVertex);
//                    printExplorePath(vertexStack);
                    return true;
                }
            }
        }
    }

    private AdjacencyVertex findUnVisitedVertex(List<AdjacencyVertex> adjVertexList, List<AdjacencyVertex> visitedVertexList) {
        adjVertexList.removeAll(visitedVertexList);
        if(!adjVertexList.isEmpty()) {
            return adjVertexList.get(0);
        }
        else {
            return null;
        }
    }

    private void printExplorePath(Stack<AdjacencyVertex> stack) {
        while(!stack.isEmpty()) {
            System.out.println(stack.pop().getDesc());
        }
    }
    class VertexNotFoundException extends RuntimeException {

    }
}
