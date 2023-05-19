package GraphFramework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class KruskalAlg extends MSTAlgorithm {

    public KruskalAlg(Graph graph){
        this.graph = graph ;
    }
    // method to perform the KRUSKAL Algorithem 
    // method that takes an `ArrayList` of `Vertex` objects as its input parameter.
    // The method doesn't return anything, 
    // but instead updates the `MSTResultList` and `totalCost` variables 
    // to store the edges and cost of the resulting MST
    public void kruskal() {
        MSTResultList = new ArrayList<GraphFramework.Edge>();
        // keeps the sets and their roots     
        Map<Vertex, Vertex> parent = new HashMap<>();

        // keeps track of the depth of each set using the `rank` map
        Map<Vertex, Integer> rank = new HashMap<>();

        // variable that stores the sum of weights of all edges in the MST
        int totalCost = 0;

        // list to keep the edges in increasing order 
        ArrayList<Edge> edges = new ArrayList<>();

        // loop to add the edges of each vertex
        for (int i = 0; i < graph.vertices.size(); i++) {

            // getting the size of the list of edges for the current vetex 
            int size = graph.vertices.get(i).adjLists.size();

            // going through the list of edges 
            for (int j = 0; j < size; j++) {

                // adding the edges to edge list of the kruskal algorithm 
                edges.add(graph.vertices.get(i).adjLists.get(j));

            }
        }

        // sorting the edges according to their weights 
        // so that the edge with the smallest weight is always considered first in the algorithm.
        Collections.sort(edges, Comparator.comparingInt(edge -> edge.weight));

        // for every vertex creates a singleton set
        for (int i = 0; i < graph.vertices.size(); i++) {
            makeSet(graph.vertices.get(i), parent, rank);
        }

        // loop to go through each edge
        for (int i = 0; i < edges.size(); i++) {

            // finding the roots for the source and target vertices of the current edge 
            Vertex root1 = find(edges.get(i).source, parent);
            Vertex root2 = find(edges.get(i).target, parent);

            // if there is no contradiction --> 2 different sets  
            if (root1 != root2) {

                // add this edge in the resulting edges
                MSTResultList.add(edges.get(i));

                // add its weight to the total cost 
                totalCost += edges.get(i).weight;

                // join the 2 sets together 
                union(edges.get(i).source, edges.get(i).target, parent, rank);

            }

        }

        // calling the method that will print the results 
        displayResultingMST(MSTResultList);
    }

    //---------------------------------------------------------------------------------------------
    // method that initializes a singleton set for a given vertex 
    // This method takes three arguments,
    // a `Vertex` object representing the vertex we want to create a set for ,
    // and a `Map<Vertex, Vertex>` object representing the parent of each vertex in the graph. 
    // and a `Map<Vertex, Integer>` object representing rank of each set. 
    // The method returns the root of the tree containing the specified vertex
    public void makeSet(Vertex vertex, Map<Vertex, Vertex> parent, Map<Vertex, Integer> rank) {

        // assigns the vertex itself as its parent 
        parent.put(vertex, vertex);

        // and sets its rank to 0.
        rank.put(vertex, 0);

    }

    //---------------------------------------------------------------------------------------------
    // method to find the parent ( ROOT ) for a specified vetex
    // This method takes two arguments,
    // a `Vertex` object representing the vertex whose parent is to be found,
    // and a `Map<Vertex, Vertex>` object representing the parent of each vertex in the graph. 
    // The method returns the root of the tree containing the specified vertex
    public Vertex find(Vertex vertex, Map<Vertex, Vertex> parent) {

        // If the parent of the input vertex is itself
        // ex. the vertex is the root of the tree, return the vertex.
        if (parent.get(vertex).equals(vertex)) {
            return vertex;
        }

        //Otherwise, recursively find the root of the tree containing the parent of the specified vertex.
        // by traversing up the parent chain until it reaches the root node, which is the vertex whose parent is itself
        Vertex root = find(parent.get(vertex), parent);

        // Update the parent of the specified vertex to be the root of the tree that was ust found.(PATH COMMPRESSION)
        parent.put(vertex, root);

        // Return the root of the tree.
        return root;
    }

//---------------------------------------------------------------------------------------------
    // The method joins the sets of the two specified vertices together.
    //This method takes four arguments, 
    // two `Vertex` objects representing the vertices whose sets are to be joined,
    // and two `Map` objects representing the parent and rank of each vertex in the graph. 
    public void union(Vertex vertex1, Vertex vertex2, Map<Vertex, Vertex> parent, Map<Vertex, Integer> rank) {

        // finding the root of each vertix 
        Vertex root1 = find(vertex1, parent);
        Vertex root2 = find(vertex2, parent);

        // If the two vertices have the same root, it means the vertices are already in the same set,
        // no action needs to be taken, so return.
        if ((root1.label + "").equals(root2.label + "")) {
            return;
        }

        // Otherwise, the method merges the two sets by making the parent of one root node point to the other root node.
        // getting the rank for each root  ( rank is possibly is the number of nodes in each sets )
        int rank1 = rank.get(root1);
        int rank2 = rank.get(root2);

        // mergeing the two trees by making the root of the tree with lower rank a child of the root of the tree with higher rank.
        if (rank1 < rank2) {

            parent.put(root2, root1);
        } else {

            parent.put(root1, root2);

            // If the ranks of the two root nodes are the same, 
            // then the rank of one of the root nodes is increased by one to maintain the balance of the tree.
            // increment the rank of the root that became a child.
            rank.put(root1, rank1 + 1);

        }

    }

    //---------------------------------------------------------------------------------------------
    // method to diplay the MST information 
    // method that takes an `ArrayList` of `Edge` objects as its input parameter.
    // and an integer variable that stores the sum of weights of all edges in the MST
    // The method doesn't return anything, 
    // but instead print the information of the `MSTResultList` and `totalCost` variables 
    @Override
    public void displayResultingMST(ArrayList<Edge> MSTResultList) {

        // variable to store the cost of MSTResultList 
        int totalCost = 0;

        System.out.println("The phone network (minimum spanning tree) generated by Kruskal algorithm is as follows:\n");

        // loop to go throuh the list that save the resulting edges 
        for (int i = 0; i < MSTResultList.size(); i++) {
            System.out.println("Office No." + MSTResultList.get(i).source.displayInfo() + " - Office No. " + MSTResultList.get(i).target.displayInfo()
                    + " : line length: " + MSTResultList.get(i).displayInfo());
            totalCost += MSTResultList.get(i).displayInfo();
        }

        System.out.println("Total cost: " + totalCost + "\n");

    }
}
