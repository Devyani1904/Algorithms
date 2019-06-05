/* Write a Java Program to count out Number of Island 

Running Steps-
- Step to run program-

Enter Row value
4
Enter Column value
5
Enter 20 Array Elements : 
Enter Grid Value
1 1 0 0 0
1 1 0 0 0
0 0 1 0 0
0 0 0 1 1

Grid Is
1 1 0 0 0 
1 1 0 0 0 
0 0 1 0 0 
0 0 0 1 1 
Number of islands are 3

*/

import java.util.Scanner;
public class Graph 
{
    private int h;
    private int w;

    public static void main(String[] args) 
    {
        // Initialization
        int row,col,result;
        Graph m= new Graph();
        Scanner scan= new Scanner(System.in);
        
        System.out.println("Enter Row value");
        row= scan.nextInt();

        System.out.println("Enter Column value");
        col= scan.nextInt();
        char grid[][]=new char[row][col];
     
        // enter array elements.
        System.out.println("Enter " +(row*col)+ " Array Elements : ");
        //Enter Grid Value
        System.out.println("Enter Grid Value");
        for(int i=0 ; i< row;i++)
        {
            for(int j=0;j<col;j++)
            {
                grid[i][j]=scan.next().charAt(0);
            }
            
        }
         
        System.out.println("\nGrid Is");
        for(int i=0 ; i<row;i++)
        {
            for(int j=0;j<col;j++)
            {
                System.out.print(grid[i][j]+" ");
            }
        System.out.println();
        }
        System.out.println("Number of islands are " + new Graph().numIslands(grid));
    }

    public int numIslands(char[][] grid) 
    {
        h = grid.length;
        if (h == 0)
            return 0;
        w = grid[0].length;
        boolean[][] visited = new boolean[h][w];
        int islandCount = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    visitedkNeigbors(grid, visited, i, j);
                    ++islandCount;
                }
            }
        }
        return islandCount;
    }

    private void visitedkNeigbors(char[][] grid, boolean[][] visited, int x, int y) 
    {
        if (x < 0 || x >= h || y < 0 || y >= w || visited[x][y] || grid[x][y] != '1')
            return;
        visited[x][y] = true;
        visitedkNeigbors(grid, visited, x + 1, y);
        visitedkNeigbors(grid, visited, x - 1, y);
        visitedkNeigbors(grid, visited, x, y + 1);
        visitedkNeigbors(grid, visited, x, y - 1);

    }

}