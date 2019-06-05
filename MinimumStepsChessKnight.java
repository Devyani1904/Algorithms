package edu.gwu.csci6212;

import java.util.LinkedList;



public class MinimumStepsChessKnight {

    private boolean [][] board;
    private Position start;
    private Position goal;
    private LinkedList<Position> queue;
    private boolean gameOver;
    private Position[] knightMovements;
    private int size;
    private int totalSteps;

    MinimumStepsChessKnight(){
        this.queue = new LinkedList<>();
        this.gameOver = false;

        this.knightMovements = new Position [] {
                new Position(1,2,1),
                new Position(1,-2,1),
                new Position(2,1,1),
                new Position(2,-1,1),
                new Position(-2,1,1),
                new Position(-2,-1,1),
                new Position(-1,2,1),
                new Position(-1,-2,1),
        };
    }

    public class Position {
        public int x;
        public int y;
        public int steps;

        Position(){
            this.x = 0;
            this.y = 0;
            this.steps = 0;
        }
        Position(int x, int y, int steps){
            this.x = x;
            this.y = y;
            this.steps = steps;
        }

        public void add(Position p1){
            this.x += p1.x;
            this.y += p1.y;
            this.steps  += p1.steps;
        }
        public boolean equals(Position p2){
            return (this.x == p2.x && this.y == p2.y);
        }
    }

    public int startGame(int size, int[] start_array, int[] goal_array){
        this.size = size;
        board = new boolean[size][size];
        start = new Position(start_array[0], start_array[1], 0);
        goal = new Position(goal_array[0], goal_array[1], 0);

        if (outOfBounds()) return -1;

        if (start.equals(goal)){
            return 0;
        }
        else{
            board [start.x][start.y] = true;
            queue.add(start);
            while(!gameOver){
                calculateSquares(queue.remove());
            }
            return totalSteps;
        }
    }


    public void calculateSquares(Position origin){
        for (Position knightMove : knightMovements){
            Position temp = new Position();
            calculateMovement(temp, origin, knightMove);
            if(isInsideLimits(temp)){
                if(!isUsed(temp)){
                    makeMovement(temp);
                }
            }
            isGameOver(temp);
        }
    }

    private boolean outOfBounds(){
        return !isInsideLimits(start) || !isInsideLimits(goal);
    }

    private boolean isInsideLimits(Position temp){
        return temp.x < size && temp.y < size && temp.x > 0 && temp.y > 0;
    }

    private boolean isUsed(Position temp){
        return (board[temp.x][temp.y] == true);
    }

    private void makeMovement(Position temp){
        queue.add(temp);
        board[temp.x][temp.y] = true;
    }

    private void calculateMovement(Position temp, Position origin, Position knightMove){
        temp.add(origin);
        temp.add(knightMove);
    }

    private void isGameOver(Position temp){
        if(temp.equals(goal)){
            gameOver = true;
            totalSteps = temp.steps;
        }
    }
}

