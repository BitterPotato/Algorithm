package graph;

import java.util.List;

public class AdjacencyVertexUtil {

    public static AdjacencyVertex findVertexWithDesc(String desc, List<AdjacencyVertex> vertexes) {
        for(AdjacencyVertex vertex : vertexes) {
            if(vertex.getDesc().equals(desc)) {
                return vertex;
            }
        }
        return null;
    }
}
