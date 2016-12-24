package graph.dfs;

import java.util.List;
import java.util.Stack;

import graph.AdjacencyVertex;

public interface DfsSolveTopoSort {
    public Stack<AdjacencyVertex> topoSort(List<AdjacencyVertex> vertexList);
}
