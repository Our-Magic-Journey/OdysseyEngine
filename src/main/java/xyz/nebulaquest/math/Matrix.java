package xyz.nebulaquest.math;

/**
 * Represents a matrix
 */
public class Matrix {
  protected int rows;
  protected int columns;
  protected double[][] data;
  
  /**
   * Constructor for creating a matrix from a 2D array.
   *
   * @param array The 2D array representing the matrix.
   */
  public Matrix(double[][] array) {
    this.rows = array.length;
    this.columns = array[0].length;
    this.data = new double[rows][columns];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        this.data[i][j] = array[i][j];
      }
    }
  }

  /**
   * Sets the value at the specified position in the matrix.
   *
   * @param row    The row index.
   * @param column The column index.
   * @param value  The value to set.
   */
  public void set(int row, int column, double value) {
    if (row >= 0 && row < rows && column >= 0 && column < columns) {
      data[row][column] = value;
    }
  }

  /**
   * Gets the value at the specified position in the matrix.
   *
   * @param row    The row index.
   * @param column The column index.
   * @return The value at the specified position or null if the indices are out of bounds.
   */
  public Double get(int row, int column) {
    if (row >= 0 && row < rows && column >= 0 && column < columns) {
      return data[row][column];
    } 

    return null;
  }
}
