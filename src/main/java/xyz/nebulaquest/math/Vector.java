package xyz.nebulaquest.math;

import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * Represents a 2D vector with x and y components.
 */
public class Vector {
  private double x;
  private double y;

  /**
   * Constructs a new Vector with both components set to 0.
   */
  public Vector() {
    this(0);
  }

  /**
   * Constructs a new Vector with both components set to the given value.
   *
   * @param value The value for both x and y components.
   */
  public Vector(double value) {
    this(value, value);
  }

  /**
   * Constructs a new Vector with the same components as the provided vector.
   *
   * @param vector The vector to copy.
   */
  public Vector(Vector vector) {
    this(vector.x, vector.y);
  }

  /**
   * Constructs a new Vector from a Point2D object.
   *
   * @param point The Point2D object to convert to a vector.
   */
  public Vector(Point2D point) {
    this(point.getX(), point.getY());
  }

  /**
   * Constructs a new Vector with the specified x and y components.
   *
   * @param x The x-component of the vector.
   * @param y The y-component of the vector.
   */
  public Vector(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Converts the vector to a Point object by rounding the x and y components.
   *
   * @return A new Point object with coordinates rounded from the x and y components.
   */
  public Point toPoint() {
    return new Point(getRoundX(), getRoundY()); 
  }

  /**
   * Converts the vector to a Point object by rounding down the x and y components to the nearest integer.
   *
   * @return A new Point object with coordinates rounded down from the x and y components of the vector.
   */
  public Point toPointFloor() {
    return new Point(getFloorX(), getFloorY()); 
  }

  /**
   * Converts the vector to a Point object by rounding up the x and y components to the nearest integer.
   *
   * @return A new Point object with coordinates rounded up from the x and y components of the vector.
   */
  public Point toPointCeil() {
    return new Point(getCeilX(), getCeilY()); 
  }

  /**
   * Gets the x-component of the vector.
   *
   * @return The x-component.
   */
  public double getX() {
    return x;
  }

  /**
   * Gets the y-component of the vector.
   *
   * @return The y-component.
   */
  public double getY() {
    return y;
  }

  /**
   * Converts the x-component by rounding it down.
   *
   * @return The largest integer less than or equal to the x-component.
   */
  public int getFloorX() {
    return (int)Math.floor(x);
  }

  /**
   * Converts the y-component by rounding it down.
   *
   * @return The largest integer less than or equal to the y-component.
   */
  public int getFloorY() {
    return (int)Math.floor(y);
  }

  /**
   * Converts the x-component by rounding it.
   *
   * @return The rounded value of the x-component.
   */
  public int getRoundX() {
    return (int)Math.round(x);
  }

  /**
   * Converts the y-component by rounding it.
   *
   * @return The rounded value of the y-component.
   */
  public int getRoundY() {
    return (int)Math.round(y);
  }

  /**
   * Converts the x-component by rounding it up.
   *
   * @return The smallest integer greater than or equal to the x-component.
   */
  public int getCeilX() {
    return (int)Math.ceil(x);
  }

  /**
   * Converts the y-component by rounding it up.
   *
   * @return The smallest integer greater than or equal to the y-component.
   */
  public int getCeilY() {
    return (int)Math.ceil(y);
  }


  /**
   * Sets the components of this vector to match those of another vector.
   *
   * @param value The vector to copy.
   */
  public void set(Vector value) {
    this.x = value.getX();
    this.y = value.getY();
  }

  /**
   * Sets the components of this vector to the specified values.
   *
   * @param x The new x-component.
   * @param y The new y-component.
   */
  public void set(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Sets the x component of the vector.
   *
   * @param x The new x value
   */
  public void setX(double x) {
    this.x = x;
  }

  /**
   * Sets the y component of the vector.
   *
   * @param y The new y value
   */
  public void setY(double y) {
    this.y = y;
  }

  /**
   * Creates and returns a new vector that is a copy of this vector.
   *
   * @return A new vector that is a copy of this vector.
   */
  public Vector clone() {
    return new Vector(this);
  }

  /**
   * Adds the components of another vector to this vector, modifying the current vector.
   *
   * @param vector The vector to add.
   * @return This vector after addition.
   */
  public Vector add(Vector vector) {
    add(vector.getX(), vector.getY());

    return this;
  }

  /**
   * Adds the components of another vector to this vector, modifying the current vector.
   *
   * @param x The value to add from the x-component.
   * @param y The value to add from the y-component.
   * @return This vector after addition. 
   */
  public Vector add(double x, double y) {
    this.x += x;
    this.y += y;

    return this;
  }

  /**
   * Subtracts the components of another vector from this vector, modifying the current vector.
   *
   * @param vector The vector to subtract.
   * @return This vector after subtraction.
   */
  public Vector subtract(Vector vector) {
    subtract(vector.getX(), vector.getY());

    return this;
  }

  /**
   * Subtracts the specified values from the x and y components of this vector, modifying the current vector.
   *
   * @param x The value to subtract from the x-component.
   * @param y The value to subtract from the y-component.
   * @return This vector after subtraction.
   */
  public Vector subtract(double x, double y) {
    this.x -= x;
    this.y -= y;

    return this;
  }

  /**
   * Multiplies the components of this vector by a scalar, modifying the current vector.
   *
   * @param scalar The scalar value to multiply by.
   * @return This vector after multiplication.
   */
  public Vector multiply(double scalar) {
    this.x *= scalar;
    this.y *= scalar;

    return this;
  }

  /**
   * Divides the components of this vector by a scalar, modifying the current vector.
   *
   * @param scalar The scalar value to divide by.
   * @return This vector after division.
   */
  public Vector divide(double scalar) {
    this.x /= scalar;
    this.y /= scalar;

    return this;
  }

  /**
   * Computes the length of this vector.
   *
   * @return The length of the vector.
   */
  public double length() {
    return Math.sqrt(x * x + y * y);
  }

  /**
   * Clamps the components of this vector to be within the specified range.
   * The components are modified in-place.
   *
   * @param min The minimum value for each component.
   * @param max The maximum value for each component.
   * @return This vector after clamping.
   */
  public Vector clamp(int min, int max) {
    this.x = Math.clamp(this.x, min, max);
    this.y = Math.clamp(this.y, min, max);

    return this;
  }

  /**
   * Clamps the components of this vector to be within the specified range.
   * The components are modified in-place.
   *
   * @param min The minimum vector values for each component.
   * @param max The maximum vector values for each component.
   * @return This vector after clamping.
   */
  public Vector clamp(Vector min, Vector max) {
    this.x = Math.clamp(this.x, min.getX(), max.getX());
    this.y = Math.clamp(this.y, min.getY(), max.getY());

    return this;
  }

  /**
   * Normalizes this vector by dividing each component by its length.
   */
  public void normalize() {
    double length = length();

    this.x /= length;
    this.y /= length;
  }

  /**
   * Returns a normalized copy of this vector.
   *
   * @return A normalized copy of this vector.
   */
  public Vector normalized() {
    double length = length();
    return new Vector(x / length, y / length);
  }

  /**
   * Computes the dot product of this vector with another vector.
   *
   * @param vec The other vector.
   * @return The dot product of the two vectors.
   */
  public double dotProduct(Vector vec) {
    return dotProduct(vec.getX(), vec.getY());
  }

  /**
   * Computes the dot product of this vector with specified values.
   *
   * @param x The x-component of the other vector.
   * @param y The y-component of the other vector.
   * @return The dot product of the two vectors.
   */
  public double dotProduct(double x, double y) {
    return this.x * x + this.y * y;
  }
  
  /**
   * Computes the cross product of this vector with another vector.
   *
   * @param vec The other vector.
   * @return The cross product of the two vectors.
   */
  public double crossProduct(Vector vec) {
    return crossProduct(vec.getX(), vec.getY());
  }

  /**
   * Computes the cross product of this vector with specified values.
   *
   * @param x The x-component of the other vector.
   * @param y The y-component of the other vector.
   * @return The cross product of the two vectors.
   */
  public double crossProduct(double x, double y) {
    return this.x * y - this.y * x;
  }

  /**
   * Computes the absolute values of the x and y components, creating a new Vector with the absolute values.
   *
   * @return A new Vector with the absolute values of the x and y components of this vector.
   */
  public Vector abs() {
    return new Vector(Math.abs(this.x), Math.abs(this.y));
  }

  /**
   * Checks if this vector is equal to another vector.
   *
   * @param vec The vector to compare.
   * @return True if the vectors are equal, false otherwise.
   */
  public boolean equal(Vector vec) {
    return equal(vec.getX(), vec.getY());
  }

  /**
   * Checks if the x and y components of this vector are equal to the specified values.
   *
   * @param x The value to compare with the x-component.
   * @param y The value to compare with the y-component.
   * @return True if both components are equal, false otherwise.
   */
  public boolean equal(double x, double y) {
    return doubleEqual(this.x, x) && doubleEqual(this.y, y);
  }

  /**
   * Checks if the x and y components of this vector are greater than the specified values.
   *
   * @param x The value to compare with the x-component.
   * @param y The value to compare with the y-component.
   * @return True if both components are greater, false otherwise.
   */
  private boolean doubleEqual(double a, double b) {
    return Math.abs(a - b) <= Double.MIN_NORMAL;
  }

  /**
   * Checks if the x and y components of this vector are greater than the corresponding components of the provided vector.
   *
   * @param vec The vector to compare with.
   * @return True if both components are greater, false otherwise.
   */
  public boolean greater(Vector vec) {
    return greater(vec.getX(), vec.getY());
  }

  /**
   * Checks if the x and y components of this vector are greater than the corresponding components of the provided vector.
   *
   * @param vec The vector to compare with.
   * @return True if both components are greater, false otherwise.
   */
  public boolean greater(double x, double y) {
    return this.x > x && this.y > y;
  }
  
  /**
   * Checks if the x and y components of this vector are less than the specified values.
   *
   * @param x The value to compare with the x-component.
   * @param y The value to compare with the y-component.
   * @return True if both components are less, false otherwise.
   */
  public boolean less(Vector vec) {
    return less(vec.getX(), vec.getY());
  }

  /**
   * Checks if the x and y components of this vector are less than the corresponding components of the provided vector.
   *
   * @param vec The vector to compare with.
   * @return True if both components are less, false otherwise.
   */
  public boolean less(double x, double y) {
    return this.x < x && this.y < y;
  }

  /**
   * Checks if the x and y components of this vector are greater than or equal to the corresponding components of the provided vector.
   *
   * @param vec The vector to compare with.
   * @return True if both components are greater or equal, false otherwise.
   */
  public boolean greaterOrEqual(Vector vec) {
    return greaterOrEqual(vec.getX(), vec.getY());
  }

  /**
   * Checks if the x and y components of this vector are greater than or equal to the specified values.
   *
   * @param x The value to compare with the x-component.
   * @param y The value to compare with the y-component.
   * @return True if both components are greater or equal, false otherwise.
   */
  public boolean greaterOrEqual(double x, double y) {
    return this.greater(x, y) || this.equal(x, y);
  }

  /**
   * Checks if the x and y components of this vector are less than or equal to the corresponding components of the provided vector.
   *
   * @param vec The vector to compare with.
   * @return True if both components are less or equal, false otherwise.
   */
  public boolean lessOrEqual(Vector vec) {
    return lessOrEqual(vec.getX(), vec.getY());
  }

  /**
   * Checks if the x and y components of this vector are less than or equal to the specified values.
   *
   * @param x The value to compare with the x-component.
   * @param y The value to compare with the y-component.
   * @return True if both components are less or equal, false otherwise.
   */
  public boolean lessOrEqual(double x, double y) {
    return this.less(x, y) || this.equal(x, y);
  }

  /**
   * Converts this vector to a string representation.
   *
   * @return A string representation of the vector in the form "(x, y)".
   */
  @Override
  public String toString() {
    return "(" + x + ", " + y + ")";
  }

  /**
   * Creates a Vector from a Point2D object.
   *
   * @param point The Point2D object to convert.
   * @return A new Vector with components from the Point2D object.
   */
  public static Vector fromPoint(Point2D point) {
    return new Vector(point);
  } 

  /**
   * Performs vector addition by creating a new vector with components equal to the sum of the corresponding components of vectors a and b.
   *
   * @param a The first vector.
   * @param b The second vector.
   * @return A new vector representing the sum of vectors a and b.
   */
  public static Vector addition(Vector a, Vector b) {
    Vector copy = new Vector(a);
    
    copy.add(b);

    return copy;
  }

  /**
   * Performs vector subtraction by creating a new vector with components equal to the difference between the corresponding components of vectors a and b.
   *
   * @param a The vector to subtract from.
   * @param b The vector to subtract.
   * @return A new vector representing the result of subtracting vector b from vector a.
   */
  public static Vector subtraction(Vector a, Vector b) {
    Vector copy = new Vector(a);
    
    copy.subtract(b);

    return copy;
  }

  /**
   * Performs scalar multiplication on a vector by creating a new vector with components equal to the product of each component of the vector and the scalar.
   *
   * @param vec The vector to multiply.
   * @param scalar The scalar value.
   * @return A new vector representing the result of multiplying the vector by the scalar.
   */
  public static Vector multiplication(Vector vec, double scalar) {
    Vector copy = new Vector(vec);
    
    copy.multiply(scalar);

    return copy;
  }

  /**
   * Performs scalar division on a vector by creating a new vector with components equal to the division of each component of the vector by the scalar.
   *
   * @param vec The vector to divide.
   * @param scalar The scalar value.
   * @return A new vector representing the result of dividing the vector by the scalar.
   */
  public static Vector division(Vector vec, double scalar) {
    Vector copy = new Vector(vec);
    
    copy.divide(scalar);

    return copy;
  }

  /**
   * Normalizes a vector by creating a new vector with the same direction but a magnitude of 1.
   *
   * @param vec The vector to normalize.
   * @return A new vector representing the normalized form of the input vector.
   */
  public static Vector normalize(Vector vec) {
    return vec.normalized();
  }

  /**
   * Computes the absolute values of the x and y components of a vector, creating a new vector with the absolute values.
   *
   * @param vec The vector for which to compute absolute values.
   * @return A new vector with the absolute values of the x and y components.
   */
  public static Vector abs(Vector vec) {
    return vec.abs();
  }

  /**
   * Computes the dot product of two vectors.
   *
   * @param vecA The first vector.
   * @param vecB The second vector.
   * @return The dot product of vecA and vecB.
   */
  public static double dotProduct(Vector vecA, Vector vecB) {
    return vecA.dotProduct(vecB);
  }

  /**
   * Computes the cross product of two vectors.
   *
   * @param vecA The first vector.
   * @param vecB The second vector.
   * @return The cross product of vecA and vecB.
   */
  public static double crossProduct(Vector vecA, Vector vecB) {
    return vecA.crossProduct(vecB);
  }

  /**
   * Checks if two vectors are equal by comparing their x and y components.
   *
   * @param vecA The first vector.
   * @param vecB The second vector.
   * @return True if the vectors are equal, false otherwise.
   */
  public static boolean equal(Vector vecA, Vector vecB) {
    return vecA.equal(vecB);
  }

  /**
   * Checks if the components of the first vector are greater than the corresponding components of the second vector.
   *
   * @param vecA The first vector.
   * @param vecB The second vector.
   * @return True if the components of vecA are greater than the corresponding components of vecB, false otherwise.
   */
  public static boolean greater(Vector vecA, Vector vecB) {
    return vecA.greater(vecB);
  }

  /**
   * Checks if the components of the first vector are less than the corresponding components of the second vector.
   *
   * @param vecA The first vector.
   * @param vecB The second vector.
   * @return True if the components of vecA are less than the corresponding components of vecB, false otherwise.
   */
  public static boolean less(Vector vecA, Vector vecB) {
    return vecA.less(vecB);
  }

  /**
   * Checks if the components of the first vector are greater than or equal to the corresponding components of the second vector.
   *
   * @param vecA The first vector.
   * @param vecB The second vector.
   * @return True if the components of vecA are greater than or equal to the corresponding components of vecB, false otherwise.
   */
  public static boolean greaterOrEqual(Vector vecA, Vector vecB) {
    return vecA.greaterOrEqual(vecB);
  }

  /**
   * Checks if the components of the first vector are less than or equal to the corresponding components of the second vector.
   *
   * @param vecA The first vector.
   * @param vecB The second vector.
   * @return True if the components of vecA are less than or equal to the corresponding components of vecB, false otherwise.
   */
  public static boolean lessOrEqual(Vector vecA, Vector vecB) {
    return vecA.lessOrEqual(vecB);
  }

  /**
   * Multiplies a 2D vector by a 2x2 matrix.
   *
   * @param vec The vector to be multiplied.
   * @param matrix The 2x2 matrix to multiply with the vector.
   * @return A new vector representing the result of the matrix-vector multiplication.
   */
  public static Vector multiplyByMatrix(Vector vec, Matrix2x2 matrix) {
    return matrix.multiplyByVector(vec);
  }

  /**
   * Creates a new vector by cloning the provided vector and clamping its components to be within the specified range.
   *
   * @param vector The vector to be cloned and clamped.
   * @param min    The minimum value for each component.
   * @param max    The maximum value for each component.
   * @return A new vector with clamped components.
   */
  public static Vector clamp(Vector vector, int min, int max) {
    return vector.clone().clamp(min, max);
  }

  /**
  * Creates a new vector by cloning the provided vector and clamping its components to be within the specified range.
  *
  * @param vector The vector to be cloned and clamped.
  * @param min    The minimum vector values for each component.
  * @param max    The maximum vector values for each component.
  * @return A new vector with clamped components.
  */
  public static Vector clamp(Vector vector, Vector min, Vector max) {
    return vector.clone().clamp(min, max);
  }
}
