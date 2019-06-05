
/*Program To find bipartite Graph

Step to run the program is as follows:-


Enter the number of vertices: 
4
Enter the number of edges: 
4
Enter the edges: <to> <from>
1 3
0 2
1 3
0 2
Output = True
 */

import java.util.*;

// Data structure to store graph edges
class Edge
{
	int source, dest;

	public Edge(int source, int dest) {
		this.source = source;
		this.dest = dest;
	}
};

// Class to represent a graph object
class Graph
{
	// An array of Lists to represent adjacency list
	List<List<Integer>> adjList = null;

	// Constructor
	Graph(List<Edge> edges, int N) {
		adjList = new ArrayList<>(N);
		for (int i = 0; i < N; i++) {
			adjList.add(i, new ArrayList<>());
		}

		// add edges to the undirected graph
		for (int i = 0; i < edges.size(); i++)
		{
			int src = edges.get(i).source;
			int dest = edges.get(i).dest;

			// add an edge from source to destination
			adjList.get(src).add(dest);

			// add an edge from destination to source
			adjList.get(dest).add(src);
		}
	}
}

class Bipartite
{
	// Perform BFS on graph starting from vertex v
	public static boolean BFS(Graph graph, int v, int N)
	{
		// stores vertex is discovered or not
		boolean[] discovered = new boolean[N];

		// stores level of each vertex in BFS
		int[] level = new int[N];

		// mark source vertex as discovered and
		// set its level to 0
		discovered[v] = true;
		level[v] = 0;

		// create a queue to do BFS and enqueue
		// source vertex in it
		Queue<Integer> q = new ArrayDeque<>();
		q.add(v);

		// run till queue is not empty
		while (!q.isEmpty())
		{
			// pop front node from queue and print it
			v = q.poll();

			// do for every edge (v -> u)
			for (int u : graph.adjList.get(v))
			{
				// if vertex u is explored for first time
				if (!discovered[u])
				{
					// mark it discovered
					discovered[u] = true;

					// set level as level of parent node + 1
					level[u] = level[v] + 1;

					// push the vertex into the queue
					q.add(u);
				}
				// if the vertex is already been discovered and
				// level of vertex u and v are same, then the
				// graph contains an odd-cycle & is not biparte
				else if (level[v] == level[u])
					return false;
			}
		}
		return true;
	}

	public static void main(String[] args)
	{
		Scanner sc= new Scanner(System.in);
		
		final int N ;
		int v, e, count = 1, to = -1, from = -1;

         List<Edge> edges= new ArrayList<Edge>();
		 System.out.println("Enter the number of vertices: ");
            N = sc.nextInt();
            System.out.println("Enter the number of edges: ");
            e = sc.nextInt();
          System.out.println("Enter the edges: <to> <from>");
            while(count <= e)
            {
                to = sc.nextInt();
                from = sc.nextInt();
 				 edges =Arrays.asList(new Edge(to, from));
 				count++;
            }
            

		// create a graph from edges
		Graph graph = new Graph(edges, N);

		// Do BFS traversal starting from vertex 1
		if (BFS(graph, 1, N))
			System.out.println("Output = True ");
		else
			System.out.println("Not a Bipartite Graph");
	}
}