package xyz.nebulaquest.math;

/**
 * Represents a 2x2 matrix
 */
public class Matrix2x2 extends Matrix {
  public Matrix2x2(double a, double b, double c, double d) {
    super(new double[][] {
      {a, b},
      {c, d}
    });
  }

  /**
   * Multiplies the matrix by a 2D vector.
   *
   * @param vec The vector to be multiplied.
   * @return A new vector representing the result of the matrix-vector multiplication.
   */
  public Vector multiplyByVector(Vector vec) {
    return new Vector(
      data[0][0] * vec.getX() + data[0][1] * vec.getY(),
      data[1][0] * vec.getX() + data[1][1] * vec.getY()
    );
  }
}
