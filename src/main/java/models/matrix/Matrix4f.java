package main.java.models.matrix;

import main.java.models.threedee.Vector4f;
/**
 * Represents a mathematical 4x4 matrix where every value is a {@code float}.
 * It is used for complex 3D calculations.
 * 
 * @author Elwin Slokker
 * @version 0.2
 */
public class Matrix4f implements Cloneable
{
    /**
     * Should always be 4.
     */
    final static int WIDTH = 4;
    /**
     * Should always be 4.
     */
    final static int HEIGHT = 4;
    /**
     * Stores the actual matrix.
     */
    private final float[][] m = new float[WIDTH][HEIGHT];

    /**
     * 
     * @param r
     * @return 
     */
    public Vector4f transform(Vector4f r)
    {
        return new Vector4f(m[0][0] * r.getX() + m[0][1] * r.getY() + m[0][2] * r.getZ() + m[0][3] * r.getW(),
                m[1][0] * r.getX() + m[1][1] * r.getY() + m[1][2] * r.getZ() + m[1][3] * r.getW(),
                m[2][0] * r.getX() + m[2][1] * r.getY() + m[2][2] * r.getZ() + m[2][3] * r.getW(),
                m[3][0] * r.getX() + m[3][1] * r.getY() + m[3][2] * r.getZ() + m[3][3] * r.getW());
    }
    /**
     * The dot product of {@code this} and {@code r}.
     * Because two identity matrices of the same size are multiplied, the calculation has been simplified.
     * 
     * @param r
     * @return the product of the matrices.
     */
    public Matrix4f mulitply(Matrix4f r)
    {
        Matrix4f result = new Matrix4f();
        for (int i = 0; i < WIDTH; i++)
        {
            for (int j = 0; j < HEIGHT; j++)
            {
                result.set(i, j, m[i][0] * r.get(0, j)
                        + m[i][1] * r.get(1, j)
                        + m[i][2] * r.get(2, j)
                        + m[i][3] * r.get(3, j));
            }
        }
        return result;
    }
    /**
     * @return a copy of the array that is used to store the matrix.
     */
    public float[][] getM()
    {
        float[][] res = new float[WIDTH][HEIGHT];

        for (int i = 0; i < WIDTH; i++)
        {
            for (int j = 0; j < HEIGHT; j++)
            {
                res[i][j] = m[i][j];
            }
        }

        return res;
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
     * Overrides the internal matrix with {@code m}.
     * @param m 
     */
    public void setM(float[][] m)
    {
        /*
        if(m.length != 4 || m[0].length != 4)
        {
            throw new Exception();
        }
        */
        Matrix4f matrix = new Matrix4f();
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                matrix.set(i, j, m[i][j]);
            }
        }
    }
    /**
     * 
     * @param x
     * @param y
     * @param value 
     */
    public void set(int x, int y, float value)
    {
        m[x][y] = value;
    }
    /**
     * 
     * @return 
     */
    public float determinant()
    {
        float determinant = 0;
        for(int y = 0; y < WIDTH; y++)
        {
            determinant += (y % 2 == 0? 1: -1) * //alternating + and - signs.
                    (m[0][y] * (new Matrix3f(this, y)).determinant());
        }
        return determinant;
    }
    /**
     * Creates a copy of this matrix.
     * Since {@code Matrix4f} is immutable, this might be necessary.
     * 
     * @return a deep copy of {@code this}.
     */
    public Matrix4f clone()
    {
        Matrix4f matrix = new Matrix4f();
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                matrix.set(i, j, this.get(i, j));
            }
        }
        return matrix;
    }
}
