package sz.shopmapp;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by Sckipper on 23.04.2017.
 */
public class AStarPathFinder {

    public static int[][] blocked ;

    public static void initializeBlocked() {
        ArrayList<Integer> colider = new ArrayList<>();

        for (int i = 0; i <= 12; i++) { //
            colider.add(i);
            colider.add(12);
        }
        for (int i = 13; i <= 85; i++) {
            colider.add(12);
            colider.add(i);
        }
        for (int i = 12; i <= 85; i++) {
            colider.add(i);
            colider.add(85);
        }
        for (int i = 86; i <= 100; i++) {
            colider.add(85);
            colider.add(i);
        }

        for(int i=0; i<=17;i++) {//raioane centru
            colider.add(42);
            colider.add(i);
        }
        for(int i=33; i<=42;i++) {//raioane centru
            colider.add(i);
            colider.add(17);
        }
        for(int i=17; i<=59;i++) {//raioane centru
            colider.add(33);
            colider.add(i);
        }

        for(int i=33; i<=58;i++) {//raioane centru
            colider.add(i);
            colider.add(59);
        }

        for(int i=21; i<=59;i++) {//raioane centru
            colider.add(58);
            colider.add(i);
        }

        for(int i=58; i<=64;i++) {//raioane centru
            colider.add(i);
            colider.add(21);
        }

        for(int i=0; i<=21;i++) {//raioane centru
            colider.add(64);
            colider.add(i);
        }

        for (int i = 24; i <= 43; i++) { // frigider stanga
            colider.add(i);
            colider.add(65);
            colider.add(i);
            colider.add(76);
        }
        for (int i = 66; i <= 76; i++) { // frigider stanga
            colider.add(43);
            colider.add(i);
            colider.add(24);
            colider.add(i);
        }

        for (int i = 54; i <= 74; i++) { // frigider dreapta
            colider.add(i);
            colider.add(65);
            colider.add(i);
            colider.add(76);
        }
        for (int i = 65; i <= 76; i++) { // frigider dreapta
            colider.add(54);
            colider.add(i);
            colider.add(74);
            colider.add(i);
        }

         blocked = new int[colider.size()][2];
        for (int i = 0; i < colider.size() / 2 ; i++)
            for (int j = 0; j < 2; j++)
                blocked[i][j]=colider.get(i*2+j);
    }

    public static final int DIAGONAL_COST = 14;
    public static final int V_H_COST = 10;

    static class Cell {
        int heuristicCost = 0; //Heuristic cost
        int finalCost = 0; //G+H
        int i, j;
        Cell parent;

        Cell(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public String toString() {
            return "[" + this.i + ", " + this.j + "]";
        }
    }

    //Blocked cells are just null Cell values in grid
    static Cell[][] grid = new Cell[5][5];

    static PriorityQueue<Cell> open;

    static boolean closed[][];
    static int startI, startJ;
    static int endI, endJ;

    public static void setBlocked(int i, int j) {
        grid[i][j] = null;
    }

    public static void setStartCell(int i, int j) {
        startI = i;
        startJ = j;
    }

    public static void setEndCell(int i, int j) {
        endI = i;
        endJ = j;
    }

    static void checkAndUpdateCost(Cell current, Cell t, int cost) {
        if (t == null || closed[t.i][t.j]) return;
        int t_final_cost = t.heuristicCost + cost;

        boolean inOpen = open.contains(t);
        if (!inOpen || t_final_cost < t.finalCost) {
            t.finalCost = t_final_cost;
            t.parent = current;
            if (!inOpen) open.add(t);
        }
    }

    public static void AStar() {

        //add the start location to open list.
        open.add(grid[startI][startJ]);

        Cell current;

        while (true) {
            current = open.poll();
            if (current == null) break;
            closed[current.i][current.j] = true;

            if (current.equals(grid[endI][endJ])) {
                return;
            }

            Cell t;
            if (current.i - 1 >= 0) {
                t = grid[current.i - 1][current.j];
                checkAndUpdateCost(current, t, current.finalCost + V_H_COST);

                if (current.j - 1 >= 0) {
                    t = grid[current.i - 1][current.j - 1];
                    checkAndUpdateCost(current, t, current.finalCost + DIAGONAL_COST);
                }

                if (current.j + 1 < grid[0].length) {
                    t = grid[current.i - 1][current.j + 1];
                    checkAndUpdateCost(current, t, current.finalCost + DIAGONAL_COST);
                }
            }

            if (current.j - 1 >= 0) {
                t = grid[current.i][current.j - 1];
                checkAndUpdateCost(current, t, current.finalCost + V_H_COST);
            }

            if (current.j + 1 < grid[0].length) {
                t = grid[current.i][current.j + 1];
                checkAndUpdateCost(current, t, current.finalCost + V_H_COST);
            }

            if (current.i + 1 < grid.length) {
                t = grid[current.i + 1][current.j];
                checkAndUpdateCost(current, t, current.finalCost + V_H_COST);

                if (current.j - 1 >= 0) {
                    t = grid[current.i + 1][current.j - 1];
                    checkAndUpdateCost(current, t, current.finalCost + DIAGONAL_COST);
                }

                if (current.j + 1 < grid[0].length) {
                    t = grid[current.i + 1][current.j + 1];
                    checkAndUpdateCost(current, t, current.finalCost + DIAGONAL_COST);
                }
            }
        }
    }

    /*
    Params :
    tCase = test case No.
    x, y = Board's dimensions
    si, sj = start location's x and y coordinates
    ei, ej = end location's x and y coordinates
    int[][] blocked = array containing inaccessible cell coordinates
    */
    public static Cell getPath(int x, int y, int si, int sj, int ei, int ej) {

        initializeBlocked();
        //Reset
        grid = new Cell[x][y];
        closed = new boolean[x][y];
        open = new PriorityQueue(16, new Comparator<Cell>() {
            @Override
            public int compare(Cell c1, Cell c2) {
                return c1.finalCost < c2.finalCost ? -1 :
                        c1.finalCost > c2.finalCost ? 1 : 0;
            }
        });
        //Set start position
        setStartCell(si, sj);  //Setting to 0,0 by default. Will be useful for the UI part

        //Set End Location
        setEndCell(ei, ej);

        for (int i = 0; i < x; ++i) {
            for (int j = 0; j < y; ++j) {
                grid[i][j] = new Cell(i, j);
                grid[i][j].heuristicCost = Math.abs(i - endI) + Math.abs(j - endJ);
            }
        }
        grid[si][sj].finalCost = 0;

           /*
             Set blocked cells. Simply set the cell values to null
             for blocked cells.
           */
        for (int i = 0; i <  blocked.length; ++i) { // TODO: 23.04.2017 de rezolvat 100
            setBlocked(blocked[i][0], blocked[i][1]);
        }

        AStar();

        if (closed[endI][endJ]) {
            //Trace back the path
            System.out.println("Path: ");
            Cell current = grid[endI][endJ];
            return current;

        } else return null;
    }

}