package main.java.models.threedee.matrix;

/**
 * Represents a mathematical 3x3 matrix where every value is a {@code float}. It
 * is used for complex 3D calculations.
 *
 * @author Elwin Slokker
 * @version 0.1
 */
public class Matrix3f
{
    /**
     * Should always be 3.
     */
    final static int WIDTH = 3;
    /**
     * Should always be 3.
     */
    final static int HEIGHT = 3;
    /**
     * Stores the actual matrix.
     */
    private final float[][] m = new float[WIDTH][HEIGHT];

    public Matrix3f()
    {

    }
    /**
     * A constructor meant for calculating the determinant of a {@see main.java.models.matrix.Matrix4f}.
     * 
     * @param matrix
     * @param excludedRow the row that should not be put in the matrix (all 4 can't fit anyway).
     */
    public Matrix3f(Matrix4f matrix, int excludedRow)
    {
        int yCounter = 0;
        for(int y = 0; y < Matrix4f.HEIGHT; y++)
        {
            if(y == excludedRow)
            {
                continue;
            }
            for(int x = 1; x < Matrix4f.WIDTH; x++)
            {
                this.m[x - 1][yCounter] = matrix.get(x, y);
            }
            yCounter++;
        }
    }
    /**
     * @param x the column of the value.
     * @param y the row of the value.
     * @return the value at ({@code x}, {@code y}).
     */
    public float get(int x, int y)
    {
        return m[x][y];
    }

    /**
     * @return the determinant number of this matrix.
     */
    public float determinant()
    {
        return ((m[0][0] * m[1][1] * m[2][2] +
                m[1][0] * m[2][1] * m[0][2] +
                m[2][0] * m[0][1] * m[1][2]) - 
                (m[2][0] * m[1][1] * m[0][2] +
                m[1][0] * m[0][1] * m[2][2] +
                m[0][0] * m[2][1] * m[1][2]));
    }
}
