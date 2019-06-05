/*Program for maze
Enter Row value
5
Enter Column value
5
Enter 25 Array Elements : 
Enter Grid Value
0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Grid Is
0 0 1 0 0 
0 0 0 0 0 
0 0 0 1 0 
1 1 0 1 1 
0 0 0 0 0 
Enter Start Coordinate
0 4
Enter destination Cordinate 
4 4
Path Exists true

*/
import java.util.Scanner;

class Maze_P4 {

public static void main(String[] args) 
{
  int row,col,result;
  Maze_P4 m= new Maze_P4();
  Scanner scan= new Scanner(System.in);
        
  System.out.println("Enter Row value");
  row= scan.nextInt();

  System.out.println("Enter Column value");
  col= scan.nextInt();
  int grid[][]=new int[row][col];
  
  // enter array elements.
  System.out.println("Enter " +(row*col)+ " Array Elements : ");
  //Enter Grid Value
  System.out.println("Enter Grid Value");
      for(int i=0 ; i< row;i++)
        {
            for(int j=0;j<col;j++)
            {
                grid[i][j]=scan.nextInt();
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
         System.out.println("Enter Start Coordinate");
         int start[] = new int[2];
         start[0] = scan.nextInt();
         start[1] = scan .nextInt();

         System.out.println("Enter destination Cordinate ");
         int destination [] = new int[2];
         destination[0] = scan.nextInt();
         destination[1] = scan.nextInt();

        System.out.println("Path Exists " + new Maze_P4().isPath(grid,start,destination));
    }
    public boolean isPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return search(maze, start, destination, visited);
    }

    public boolean search(int[][] maze, int[] position, int[] destination, boolean[][] visited) {
        if (visited[position[0]][position[1]]) {
            return false;
        }
        if (position[0] == destination[0] && position[1] == destination[1]) {
            return true;
        }
        // mark the point has been visited
        visited[position[0]][position[1]] = true;

      
        int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        for (int[] dir : dirs) {
            int x = position[0];
            int y = position[1];
            while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                x += dir[0];
                y += dir[1];
            }
            
            if (search(maze, new int[]{x - dir[0], y - dir[1]}, destination, visited)) {
                return true;
            }
        }

        return false;
    }
}