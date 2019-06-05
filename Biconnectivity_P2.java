/*Write a Java Program to check graph is biconnected or not and Identify Articulation Points

Sample output-

Enter the number of nodes in the graph
4
Enter the input in the form of adjacency matrix
0 1 0 0
0 0 1 0
0 0 0 1
0 0 0 0
Enter the source for the graph
1

 
Articulation Points :-[1, 2]
Articulations Points =2
The Given Graph is Not BiConnected
*/

import java.util.Queue;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Set;
import java.util.Stack;
 
public class Biconnectivity_P2
{
    private Queue<Integer> queue;
    private Stack<Integer> stack;
    private int numberOfNodes;
    private Set<Integer> articulationPoints;
    private int[] parent;
    private int[] visited;
    private int[][] graph;
    //private boolean *ap ;

    //Declare Constructor
    public Biconnectivity_P2(int numberOfNodes)
    {
        
        queue = new LinkedList<Integer>();
        this.stack = new Stack<Integer>();
        this.articulationPoints = new HashSet<Integer>();
        this.numberOfNodes = numberOfNodes;
        
        this.parent = new int[numberOfNodes + 1];
        this.visited = new int[numberOfNodes + 1];
        this.graph = new int[numberOfNodes + 1][numberOfNodes + 1];
        //this.ap = new boolean[number_of_nodes];
  }
    

    private boolean bfs(int graph[][], int source)
    {
        boolean connected = true;
        int number_of_nodes = graph[source].length - 1;
        int[] visited = new int[number_of_nodes + 1];
        int i, element;
        visited[source] = 1;
        queue.add(source);
        while (!queue.isEmpty())
        {
            element = queue.remove();
            i = element;
            while (i <= number_of_nodes)
            {
                if (graph[element][i] == 1 && visited[i] == 0)
                {
                    queue.add(i);
                    visited[i] = 1;
                }
                i++;
            }
        } 
 
        for (int vertex = 1; vertex <= number_of_nodes; vertex++)
        {   
            if (visited[vertex] == 1)
            {
                continue;
               
            }
            else
            {
                connected = false;
                break;
            }
        }
 
        return connected;
    }
 
    //Calculate total number or articulation point and identify them   
    private int numberOfArticulationPoint(int graph[][], int source)
    {
        int children = 0;
        int element, destination;
        stack.push(source);
        visited[source] = 1;
 
        for (int sourceVertex = 1; sourceVertex <= numberOfNodes; sourceVertex++)
        {
            for (int destinationVertex = 1; destinationVertex <= numberOfNodes; destinationVertex++)
            {
                this.graph[sourceVertex][destinationVertex]
                     = graph[sourceVertex][destinationVertex];
            }
        }
 
        while (!stack.isEmpty())
        {
            element = stack.peek();
            destination = element;
            while (destination <= numberOfNodes)
            {
                if (this.graph[element][destination] == 1 && visited[destination] == 0)
                {
                    stack.push(destination);
                    visited[destination] = 1;
                    parent[destination] = element;
                    if (element == source)
                    {
                        children++;
                    }
                    if (!isLeaf(this.graph, destination))
                    {
                        if (children > 1)
                        {
                            articulationPoints.add(source);
                        }
                        if(isArticulationPoint(this.graph, destination))
                        {
                            articulationPoints.add(destination);
                        }
                    }
                    element = destination;
                    destination = 1;
                    continue;
                }
                destination++;
            }
            stack.pop();
        }
        if(articulationPoints.size() > 0)
            System.out.println("\n \nArticulation Points :-"+articulationPoints);
        return articulationPoints.size();
    }
 
    //check given vertex is articulation point or not.
    public boolean isArticulationPoint(int [][]graph, int root)
    {
        int explored[] = new int[numberOfNodes + 1];
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(root);
        int element = 0,destination = 0;
 
        while(!stack.isEmpty())
        {
            element = stack.peek();
            destination = 1;
            while (destination <= numberOfNodes)
            {
                if ( element != root)
                {
                    if (graph[element][destination] == 1 && visited[destination] == 1)
                    {
                        if (this.stack.contains(destination))
                        {
                            if (destination <= parent[root])
                            {
                                return false;

                            }
                            return true;
                        }
                    }
                }
                if ((graph[element][destination] == 1 && explored[destination] == 0 )
                       && visited[destination] == 0)
                {
                    stack.push(destination);
                    explored[destination] = 1;
                    graph[destination][element] = 0;
                    element = destination;
                    destination = 1;
                    continue;
                }
                destination++;
            }
            stack.pop();
        }	
        return true;
    }
 
    private boolean isLeaf(int graph[][], int node)
    {
        boolean isLeaf = true;
        for (int vertex = 1; vertex <= numberOfNodes; vertex++)
        {
            if (graph[node][vertex] == 1 && visited[vertex] == 1)
            {
                isLeaf = true;
            }else if (graph[node][vertex] == 1 && visited[vertex] == 0)
            {
                isLeaf = false;
                break;
            }
        }
        return isLeaf;
    }
 
    public boolean isBiconnected(int graph[][], int source)
    {
        boolean biconnected = false;
        int count=numberOfArticulationPoint(graph, source);


        if (bfs(graph, source) && count == 0)
        { 
            biconnected = true;
        }
        System.out.println("Articulations Points ="+count);
        return biconnected;
    } 
 
    public static void main(String... arg)
    {
        int number_of_nodes, source;

        Scanner scanner = null;
        try
        {
            System.out.println("Enter the number of nodes in the graph");
            scanner = new Scanner(System.in);
            number_of_nodes = scanner.nextInt();
            
 
            int graph[][] = new int[number_of_nodes + 1][number_of_nodes + 1];
            System.out.println("Enter the input in the form of adjacency matrix");
            for (int i = 1; i <= number_of_nodes; i++)
                for (int j = 1; j <= number_of_nodes; j++)
                    graph[i][j] = scanner.nextInt();
 
            System.out.println("Enter the source for the graph");
            source = scanner.nextInt();
 
            Biconnectivity_P2 biconnectedGraph = new Biconnectivity_P2(number_of_nodes);
            if (biconnectedGraph.isBiconnected(graph, source))
            {
                System.out.println("The Given Graph is BiConnected");
            }else
            {
                System.out.println("The Given Graph is Not BiConnected");
            }
        } catch (InputMismatchException inputMismatch)
        {
            System.out.println("Incorrect Input format");
        }
        scanner.close();
    }	
}