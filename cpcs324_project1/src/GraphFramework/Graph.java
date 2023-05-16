package GraphFramework;

import PhoneNetworkApp.Line;
import PhoneNetworkApp.Office;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Random;

public class Graph {

    public static int vertexNo;
    public static int edgeNo;
    public static boolean isDiagraph;
    public static ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    
    public Graph(int edgeNo, boolean isDiagraph) {
        this.edgeNo = edgeNo;
        this.isDiagraph = isDiagraph;
    }

    public Graph() {
    }
    
//    public static Office createVertex(char label ){
//        return new Office(label );
//    }
//    public static Line createEdge(Vertex source, Vertex target , int weight){
//        return new Line(source,  target ,  weight);
//    }
     
    // method n takes as parameters the number of vertices and the number of edges
    // It is responsible for creating a graph object with the specified parameters 
    // and randomly initializes the vertices’ labels, creating edges that connects the created vertices randomly
    // and assigning them random weights alson Makeing sure that the resulting graph is connected. 
    public static void makeGraph(int vno, int eno) {

        Graph g = new Graph();

        char ch = 'A';

        // Adding the vertices to its list in the graph 
        for (int i = 0; i < vno; i++) {
            
            g.vertices.add(Office.createVertex(i));

            // incrementing the vertices number as requested ! 
            vertexNo++;
        }

        // creating random object to get random weights for the edges 
        Random rand = new Random();

        // Adding edges between random vertices
        for (int i = 0; i < eno; i++) {
            
            // getting a random index to choose by it in the list 
            int v1Index = rand.nextInt(vno);  
            int v2Index = rand.nextInt(vno);
            
            // getting the random vertices from the list 
            Vertex v1 = vertices.get(v1Index);
            Vertex v2 = vertices.get(v2Index);
            
            
            // if both vetices are not the same vertex AND they are not connected already 
            if (v1 != v2 && ! areConnected(v1, v2)) {
                
                //getting a random weight from 1 - 40 
                int weight = rand.nextInt(40) + 1;
               
                // adding the new edge 
                addEdge(v1, v2, weight);
                
                // marking the two vetices as visited to not go through it again ! 
                v1.isVisited = true;
                v2.isVisited = true;
            }
        }

        // Adding extra edges to ensure connectivity
        // going through all the vetices to check they are connected in the graph 
        for (int i = 0; i < vno; i++) {
            
            // if there is a unvisited vertex
            if (vertices.get(i).isVisited == false) {
                
                // going through all the vetices again to make sure to connect the unvisted vertex with random vertex
                for (int j = 0; j < vno; j++) {
                   
                    // getting a random index to choose by it in the list 
                    int vIndex = rand.nextInt(vno);
                    
                    // getting the random vertex from the list 
                    Vertex v = vertices.get(vIndex);
                    
                    // if both vetices are not the same vertex AND they are not connected already && v.isVisited == false
                    //**********
                    if (vertices.get(i) != v && v.isVisited == false) {
                       
                        // adding the new edge 
                        addEdge(vertices.get(i), v, rand.nextInt(40) + 1);
                        
                        // marking the two vetices as visited to not go through it again ! 
                        vertices.get(i).isVisited = true;
                        v.isVisited = true;
                        
                        break;
                    }
                }

            }

        }
        
        // return the valuse to the default to use later
        for (int i = 0; i < vno; i++) {
            vertices.get(i).isVisited = false ;
        }

    }

    // method to check if 2 vetices are connect with an edge 
    public static boolean areConnected(Vertex v1, Vertex v2) {

            // getting the size of the adjList that store the edges to ba able to through it 
            int size = v1.adjLists.size();
           
            // loop to got through the edges of a specefic vertex 
            for (int j = 0; j < size; j++) {
                
                // if one if the vertices is a source and the other is a target then they are connected (:
                if ((v1.adjLists.get(j).source == v1 && v1.adjLists.get(j).target == v2)
                        || (v1.adjLists.get(j).source == v2 && v1.adjLists.get(j).target == v1)) {
                    return true;
                }

            }
        
        return false;
    }

    // method that reads the edges and vertices from the text file whose name is
    // specified by the parameter filename and place the data in the Graph
    public static void readGraphFromFile(String fileName) throws FileNotFoundException {

        int eno = 0, vno = 0;
        
        Graph g = new Graph();
        
        // checking if the file exist and print 
        File f = new File(fileName);
        if (!f.exists()) {
            System.out.println("File Does not exist !");
        }
        
        // creating 2 scanner object
        // scanner opject to read the data of the each edge
        Scanner input = new Scanner(f);
        
        // scanner object to read the labels of each vertex 
        Scanner input2 = new Scanner(f);

        input2.nextLine();
        input2.nextLine();
        
        input.next();

        isDiagraph = input.nextInt() ==0  ? false : true;
        
        eno = input.nextInt();
        vno = input.nextInt();
        
        // list to store the labels 
        int[] listLabels = new int[vno];
        
        
        // counter act like index for the label list
        int counter = 0;
        
        // loop to store the distinct labels of each edge 
        for (int i = 0; i < eno; i++) {
            
            // reading the label 
            char ch = input2.next().charAt(0);
           
            // a flag to know wether to add the label to the list or not 
            boolean canAdd = true;
            
            // loop to go through the list labels
            // to check if the label is already exist
            for (int j = 0; j < listLabels.length; j++) {
               
                if (listLabels[j] == ( ch-'A')) {
                  
                    canAdd = false;
                    break;
                }
            }
            
            input2.nextLine();
            
            // if the flag = true , then we can add the label 
            // and increment the index to the next place for another label 
            if (canAdd) {
                listLabels[counter] = ch-'A';
                counter++;
            }
        }
        
        // Adding the vertices to its list in the graph
        for (int i = 0; i < vno; i++) {
          
            g.vertices.add(Office.createVertex(listLabels[i]));
            
            // incrementing the vertices number as requested ! 
            vertexNo++;
        }
     
        
        // Adding the edges 
        // creating and initializing the needed variables 
        char label1, label2;
        
        Vertex v1 = null, v2 = null;
        
        // loop to go through each edge in the file 
        for (int i = 0; i < eno; i++) {
           
            // reading the labels for the source and target vetices 
            label1 = input.next().charAt(0);
            label2 = input.next().charAt(0);

            // loop to go through the vetices list 
            // to get the vetices objects with same readen labels
            for (int j = 0; j < vertices.size(); j++) {
                
                // if the current vertixs’s labels is the same labels 
                if (vertices.get(j).label == label1 - 'A') 
                    
                    v1 = vertices.get(j);
                    
               else if (vertices.get(j).label == label2-'A') 
                   
                    v2 = vertices.get(j);
                
            }
           
            // checking if both variables are not null , to not cause null pointer exeption !
            if (v1 != null && v2 != null) {
                
                // adding the new edge   
             
                addEdge(v1, v2, input.nextInt());
               

            }

        }

    }

    public static void addEdge(Vertex v, Vertex u, int w) {

        // creating the edge from the source to the target 
        Edge edge1 = Line.createEdge(v, u, w);

        // creating the edge from the target to the source in case of undirected 
        Edge edge2 = Line.createEdge(u, v, w);

        // if the graph is dirceted graph then only assign from source to taget --> 
        if (isDiagraph) {
            v.adjLists.add(edge1);
            edgeNo++;
        } // if the graph is dirceted graph then both assign from source to taget
        // and from taget to source --> <--
        else {
            v.adjLists.add(edge1);
            u.adjLists.add(edge2);
            edgeNo += 2;
        }

    }
    

}
