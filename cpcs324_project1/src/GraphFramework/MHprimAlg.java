/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphFramework;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author cvs
 */
public class MHprimAlg extends MSTAlgorithm{

    public MHprimAlg(Graph graph) {
        this.graph = graph ;
    }

    
    public void displayResultingMST(ArrayList<Edge> MSTResultList ,  boolean isReq1) {

       
        

        // loop to go throuh the list that save the resulting edges 
        for (int i = 0; i < MSTResultList.size(); i++) {
            System.out.println( MSTResultList.get(i).getSource().displayInfo(isReq1) + " - " + MSTResultList.get(i).getTarget().displayInfo(isReq1)
                    + " : " + MSTResultList.get(i).displayInfo());
            
        }

       

    }
     
      class HeapNode {
        Vertex vertex;
        int key;
    }


  

     class ResultSet {
        Vertex parent;
        Vertex destination ;
        int weight;
       
      
    }

  

 

        public int primMST( boolean isReq1 ) {
            
             MSTResultList = new ArrayList<GraphFramework.Edge>();
            boolean[] inHeap = new boolean[graph.getVertices().size()];
            ResultSet[] resultSet = new ResultSet[graph.getVertices().size()];
            //keys[] used to store the key to know whether min hea update is required
            int[] key = new int[graph.getVertices().size()];
            //create heapNode for all the vertices
            HeapNode[] heapNodes = new HeapNode[graph.getVertices().size()];
            for (int i = 0; i < graph.getVertices().size(); i++) {
                heapNodes[i] = new HeapNode();
                heapNodes[i].vertex = graph.createVertex(i);
                heapNodes[i].key = Integer.MAX_VALUE;
                resultSet[i] = new ResultSet();
                resultSet[i].parent = null;
                inHeap[i] = true;
                key[i] = Integer.MAX_VALUE;
            }

            //decrease the key for the first index
            heapNodes[0].key = 0;

            //add all the vertices to the MinHeap
            MinHeap minHeap = new MinHeap(graph.getVertices().size());
            //add all the vertices to priority queue
            for (int i = 0; i < graph.getVertices().size(); i++) {
                minHeap.insert(heapNodes[i]);
            }

            //while minHeap is not empty
            while (!minHeap.isEmpty()) {
                //extract the min
                HeapNode extractedNode = minHeap.extractMin();

                //extracted vertex
                Vertex extractedVertex = extractedNode.vertex;
                inHeap[extractedVertex.getLabel()] = false;

                //iterate through all the adjacent vertices
                LinkedList<Edge> list = graph.getAdjacencylist(extractedVertex.getLabel());
                for (int i = 0; i < list.size(); i++) {
                    Edge edge = list.get(i);
                    //only if edge destination is present in heap
                    if (inHeap[edge.getTarget().getLabel()]) {
                        
                        Vertex destination = edge.getTarget();
                        int newKey = edge.getWeight();
                        //check if updated key < existing key, if yes, update if
                        if (key[destination.getLabel()] > newKey) {
                            decreaseKey(minHeap, newKey, destination);
                            //update the parent node for destination
                            resultSet[destination.getLabel()].parent = extractedVertex;
                            resultSet[destination.getLabel()].destination = edge.getTarget();
                            resultSet[destination.getLabel()].weight = newKey;
                        
                            key[destination.getLabel()] = newKey;
                        }
                    }
                }
                // Add edges from resultSet to MSTResultList
    
            
            }
           
           
            int cost =0 ; 
            for (int i = 1; i < graph.getVertices().size(); i++) {
                  
                MSTResultList.add(graph.createEdge(resultSet[i].parent ,resultSet[i].destination ,resultSet[i].weight/5));
            
            }
            
            for (int i = 0; i < MSTResultList.size(); i++) {
                
                cost+= MSTResultList.get(i).getWeight();
            }

            displayResultingMST(MSTResultList , isReq1);
            return cost ;
        }
       

        
        public void decreaseKey(MinHeap minHeap, int newKey, Vertex vertex) {

            //get the index which key's needs a decrease;
            int index = minHeap.indexes[vertex.getLabel()];

            //get the node and update its value
            HeapNode node = minHeap.mH[index];
            node.key = newKey;
            minHeap.bubbleUp(index);
        }

    

     class MinHeap {
        int capacity;
        int currentSize;
        HeapNode[] mH;
        int[] indexes; //will be used to decrease the key


        public MinHeap(int capacity) {
            this.capacity = capacity;
            mH = new HeapNode[capacity + 1];
            indexes = new int[capacity];
            mH[0] = new HeapNode();
            mH[0].key = Integer.MIN_VALUE;
            mH[0].vertex = null;
            currentSize = 0;
        }

        public void display() {
            for (int i = 0; i <= currentSize; i++) {
                System.out.println(" " + mH[i].vertex.getLabel() + "   key   " + mH[i].key);
            }
            System.out.println("________");
        }

        public void insert(HeapNode x) {
            currentSize++;
            int idx = currentSize;
            mH[idx] = x;
            indexes[x.vertex.getLabel()] = idx;
            bubbleUp(idx);
        }

        public void bubbleUp(int pos) {
            int parentIdx = pos / 2;
            int currentIdx = pos;
            while (currentIdx > 0 && mH[parentIdx].key > mH[currentIdx].key) {
                HeapNode currentNode = mH[currentIdx];
                HeapNode parentNode = mH[parentIdx];

                //swap the positions
                indexes[currentNode.vertex.getLabel()] = parentIdx;
                indexes[parentNode.vertex.getLabel()] = currentIdx;
                swap(currentIdx, parentIdx);
                currentIdx = parentIdx;
                parentIdx = parentIdx / 2;
            }
        }


public HeapNode extractMin() {
    HeapNode min = mH[1];
    HeapNode lastNode = mH[currentSize];
    indexes[lastNode.vertex.getLabel()] = 1;
    mH[1] = lastNode;
    mH[currentSize] = null;
    sinkDown(1);
    currentSize--;
    return min;
}

public void sinkDown(int k) {
    int smallest = k;
    int leftChildIdx = 2 * k;
    int rightChildIdx = 2 * k+1;
    if (leftChildIdx < heapSize() && mH[smallest].key > mH[leftChildIdx].key) {
        smallest = leftChildIdx;
    }
    if (rightChildIdx < heapSize() && mH[smallest].key > mH[rightChildIdx].key) {
        smallest = rightChildIdx;
    }
    if (smallest != k) {
        Edge smallestEdge = mH[smallest].vertex.getMinEdge();
        Edge kEdge = mH[k].vertex.getMinEdge();
        
        Vertex smallestVertex =  mH[smallest].vertex;
        Vertex kVertex =mH[k].vertex;

        indexes[smallestVertex.getLabel()] = k;
        indexes[kVertex.getLabel()] = smallest;
        swap(k, smallest);
        sinkDown(smallest);
    }
}


        public void swap(int a, int b) {
            HeapNode temp = mH[a];
            mH[a] = mH[b];
            mH[b] = temp;
        }

        public boolean isEmpty() {
            return currentSize == 0;
        }

        public int heapSize(){
            return currentSize;
        }
    
     
     }
}
