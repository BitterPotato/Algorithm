package greedy.mst;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


//9 6
//0 1 4
//0 2 1
//0 3 3
//1 2 4
//1 3 4
//2 3 2
//2 5 4
//3 5 6
//4 5 5
public class KruskalMST {

    class Node {
        int id;
        Node p;
        int rank;

        Node(int id, Node p, int rank) {
            this.id= id;
            this.p = p;
            this.rank = rank;
        }
    }

    class Edge {
        Node nodeOne;
        Node nodeTwo;
        int weight;
    }

    private Node[] nodes;
    private List<Edge> edges = new ArrayList<Edge>();
    private List<Edge> resultEdges = new ArrayList<Edge>();

    public void initNodes(int pointSize) {
        nodes = new Node[pointSize];
        for(int i = 0 ; i < pointSize; i++) {
            nodes[i] = makeset(i);
        }
    }

    public void addEdge(int idOne, int idTwo, int weight) {
        Edge edge = new Edge();
        edge.nodeOne = nodes[idOne];
        edge.nodeTwo = nodes[idTwo];
        edge.weight = weight;
        edges.add(edge);
    }
    private Node makeset(int id) {
        Node node = new Node(id, null, 0);
        node.p = node;
        return node;
    }

    private Node find(Node node) {
        if(node != node.p) {
            node.p = find(node.p);
        }
        return node.p;
    }

    private void union(Node nodeOne, Node nodeTwo) {
        Node nodeOneR = find(nodeOne);
        Node nodeTwoR = find(nodeTwo);

        // 两个节点处于同个分离集中
        if(nodeOneR == nodeTwoR) {
            return;
        }

        if(nodeOneR.rank > nodeTwoR.rank)
            nodeTwoR.p = nodeOneR;
        else if(nodeOneR.rank == nodeTwoR.rank){
            nodeOneR.p = nodeTwo;
            nodeTwo.rank += 1;
        }
        else
            nodeOneR.p = nodeTwo;
    }

    class myComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }

    }

    public void formMST() {
        // sort
        edges.sort(new myComparator());

        //
        for(Edge edge : edges) {
            if(find(edge.nodeOne) != find(edge.nodeTwo)) {
                resultEdges.add(edge);

                union(edge.nodeOne, edge.nodeTwo);
            }
        }

        for(Edge edge : resultEdges) {
            System.out.println(edge.nodeOne.id + " - " + edge.nodeTwo.id + " : " + edge.weight);
        }
    }

    //
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int edgeSize = scanner.nextInt();
        int pointSize = scanner.nextInt();

        KruskalMST k = new KruskalMST();
        k.initNodes(pointSize);

        while(edgeSize-- > 0) {
            int one = scanner.nextInt();
            int two = scanner.nextInt();
            int weight = scanner.nextInt();
            k.addEdge(one, two, weight);
        }

        k.formMST();
    }
}
