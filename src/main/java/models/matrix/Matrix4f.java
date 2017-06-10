package main.java.models.matrix;

import main.java.models.*;
/**
 * 
 * @author Elwin Slokker
 * @version 0.2
 */
public class Matrix4f implements Cloneable
{
    /**
     * 
     */
    final static int WIDTH = 4;
    /**
     * 
     */
    final static int HEIGHT = 4;
    /**
     * 
     */
    private final float[][] m = new float[4][4];

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
     * 
     * @param r
     * @return 
     */
    public Matrix4f mul(Matrix4f r)
    {
        Matrix4f res = new Matrix4f();
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                res.set(i, j, m[i][0] * r.get(0, j)
                        + m[i][1] * r.get(1, j)
                        + m[i][2] * r.get(2, j)
                        + m[i][3] * r.get(3, j));
            }
        }
        return res;
    }
    /**
     * 
     * @return 
     */
    public float[][] getM()
    {
        float[][] res = new float[4][4];

        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                res[i][j] = m[i][j];
            }
        }

        return res;
    }
    /**
     * 
     * @param x
     * @param y
     * @return 
     */
    public float get(int x, int y)
    {
        return m[x][y];
    }

    /**
     * 
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
