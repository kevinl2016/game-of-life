package com.demo;

/**
 * Write a description of class Cells here.
 *
 * @author Kevin Liang
 * @version 2022-01-13
 */
public class Cells
{
    int[][] cells;

    /**
     * Constructor for objects of class Cells
     * An empty cells is initialized to 0s.
     *
     * @param rows the number of rows in the Cells
     * @param cols the number of columns in the Cells
     */
    public Cells(int rows, int cols)
    {
        // initialise instance variables
        cells = new int[rows][cols];
    }

    /**
     * The get method returns the value stored at the specified
     * row,col location.
     *
     * @param row The row of the grid
     * @param col The column of the grid
     * @return the int value stored at that row,col
     */
    public int get(int row, int col)
    {
        return cells[row][col];
    }

    /**
     * The set method sets the specified row,col location to
     * the specified value
     *
     * @param row   The row of the grid
     * @param col   The column of the grid
     * @param value The int value to be stored at row,col
     */

    public void set(int row, int col, int value)
    {
        cells[row][col] = value;
    }

    /**
     * The getRows method returns the number of rows (the height)
     * of the grid
     *
     * @return the rows (height) of the grid
     */
    public int getRows()
    {
        return cells.length;
    }

    /**
     * The getCols method returns the number of columns (the width)
     * of the grid
     *
     * @return the columns (width) of the grid
     */

    public int getCols()
    {
        return cells[0].length;
    }

    /**
     * The toString method returns a String that can be printed to
     * display the grid
     *
     * @return a String representing the grid
     */
    public String toString()
    {
        String result = "";
        for (int r = 0; r < getRows(); r++)
        {
            for (int c = 0; c < getCols(); c++)
            {
                result += String.valueOf(cells[r][c]);
            }
            result += "\n";
        }
        return result;
    }

}
