package com.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Write a description of class Life here.
 *
 * @author Kevin Liang
 * @version 2021-01-13
 */

public class Life
{
    public static final int ROWS = 200;
    public static final int COLS = 200;

    /**
     * The intializeCells static method sets up the initial cells with a
     * List input.
     *
     * @param cells a Cells, typically empty
     * @param input list of live cell
     */
    public static void intializeCells(Cells cells, List<Integer[]> input)
    {
        Map<String, Integer> map = new HashMap<>();
        for (Integer[] cellKey : input)
        {
            map.put(String.format("%s,%s", cellKey[0], cellKey[1]), 1);
        }
        for (int r = 0; r < ROWS; r++)
        {
            for (int c = 0; c < COLS; c++)
            {
                String key = String.format("%s,%s", r, c);
                if (map.get(key) != null && map.get(key) == 1)
                {
                    cells.set(r, c, 1);
                }
                else
                {
                    cells.set(r, c, 0);
                }
            }
        }
    }


    /**
     * The static displayCells method displays the cells on screen.
     *
     * @param cells the cells to be displayed
     */

    public static void displayCells(Cells cells)
    {
        List<String> cellList = new ArrayList<String>();
        for (int r = 0; r < ROWS; r++)
        {
            for (int c = 0; c < COLS; c++)
            {
                if (cells.get(r, c) == 1)
                {
                    cellList.add(String.format("[%s, %s]", r, c));
                }
            }
        }
        String result = cellList.stream().collect(Collectors.joining(","));
        System.out.println(String.format("[%s]", result));
    }

    /**
     * The static calculateNextGeneration method takes the current cells and
     * a new (empty) cells and calculates the next generation for that second
     * cells based on the standard rules of Conway's Life:
     * 1. Any live cell with fewer than two live neighbours dies as if caused by underpopulation.
     * 2. Any live cell with two or three live neighbours lives on to the next generation.
     * 3. Any live cell with more than three live neighbours dies, as if by overpopulation.
     * 4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
     *
     * @param cells     the current cells
     * @param nextCells a cells with the new generation on it
     */
    public static void calculateNextGeneration(Cells cells, Cells nextCells)
    {
        for (int r = 0; r < ROWS; r++)
        {
            for (int c = 0; c < COLS; c++)
            {
                int cellVal = cells.get(r, c);
                int neighborCount = countNeighbors(r, c, cells);
                if (cellVal == 1 && neighborCount < 2)
                {
                    nextCells.set(r, c, 0);
                }
                else if (cellVal == 1 && neighborCount < 4)
                {
                    nextCells.set(r, c, 1);
                }
                else if (cellVal == 1 && neighborCount > 3)
                {
                    nextCells.set(r, c, 0);
                }
                else if (cellVal == 0 && neighborCount == 3)
                {
                    nextCells.set(r, c, 1);
                }
                else
                {
                    nextCells.set(r, c, 0);
                }
            }
        }
    }

    /**
     * The static method countNeighbors counts the eight cells around a given
     * cell, making sure not to count outside of the bounds of the array and
     * not to count the current cell itself!
     *
     * @param row   the row of the current cell
     * @param col   the col of the current cell
     * @param cells the cells we're investigating
     * @return the number of non-zero neighbors (minimum 0, maximum 8)
     */
    public static int countNeighbors(int row, int col, Cells cells)
    {
        int count = 0;
        for (int r = row - 1; r <= row + 1; r++)
        {
            for (int c = col - 1; c <= col + 1; c++)
            {
                if (r >= 0 && r < ROWS && c >= 0 && c < COLS && !(r == row && c == col) && cells.get(r, c) == 1)
                {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * The static method transferNextToCurrent takes the cells with the
     * next generation and copies it to the cells for this generation so
     * that we can continue displaying and analyzing generations.
     *
     * @param cells     the current cells that we will copy to
     * @param nextCells the next cells containing a calculated new generation
     */
    public static void transferNextToCurrent(Cells cells, Cells nextCells)
    {
        for (int r = 0; r < ROWS; r++)
        {
            for (int c = 0; c < COLS; c++)
            {
                cells.set(r, c, nextCells.get(r, c));
            }
        }
    }


    public static void main(String[] args)
    {
        Cells cells = new Cells(ROWS, COLS);
        Cells nextCells = new Cells(ROWS, COLS);
        List<Integer[]> input = new ArrayList<>();
        intializeCells(cells, input);
        System.out.println("Test Case1");
        for (int i = 0; i < 100; i++)
        {
            calculateNextGeneration(cells, nextCells);
            transferNextToCurrent(cells, nextCells);
            displayCells(cells);
        }

        System.out.println("Test Case2");
        List<Integer[]> input2 =
            Arrays.asList(new Integer[]{5, 5}, new Integer[]{6, 5}, new Integer[]{7, 5}, new Integer[]{5, 6}, new Integer[]{6, 6},
                new Integer[]{7, 6});
        intializeCells(cells, input2);
        for (int i = 0; i < 100; i++)
        {
            calculateNextGeneration(cells, nextCells);
            transferNextToCurrent(cells, nextCells);
            displayCells(cells);
        }
    }
}
