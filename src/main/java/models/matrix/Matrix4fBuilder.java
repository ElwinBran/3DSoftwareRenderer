
package main.java.models.matrix;

import main.java.models.threedee.Vector4f;

/**
 * A singleton able to initialize matrices (for instance a perspective matrix).
 * It is based on the code of Benny Bobaganoosh. The matrix class contained all 
 * this code before, but that makes it harder to maintain.
 * 
 * @author Elwin Slokker
 * @version 0.1
 */
public class Matrix4fBuilder
{
    /**
     * 
     */
    private static final Matrix4fBuilder INSTANCE = new Matrix4fBuilder();
    /**
     * Get the {@code Matrix4fBuilder} singleton.
     * @return the builder.
     */
    public static Matrix4fBuilder getInstance()
    {
        return INSTANCE;
    }
    /**
     * Hiding the constructor because the builder is a singleton.
     */
    private Matrix4fBuilder()
    {    
    }
    /**
     * Creates an empty matrix.
     * 
     * @return a new and empty {@code Matrix4f}.
     */
    public Matrix4f initEmpty()
    {
        return new Matrix4f();
    }
    /**
     * Changes this matrix into an identity matrix (only diagonally 1's and everything else 0).
     * 
     * @return a new identity matrix.
     */
    public Matrix4f initIdentity()
    {
        Matrix4f matrix = new Matrix4f();
        for(int m = 0; m < 4; m++)
        {
            for(int n = 0; n < 4; n++)
            {
                matrix.set(n, m,
                        //set to 1 in a diagonal line.
                        (m == n)? 1 : 0
                );
            }
        }
        return matrix;
    }
    /**
     * Makes a matrix that can be used to translate values based on the provided x, y and z.
     * 
     * @param x
     * @param y
     * @param z
     * @return 
     */
    public Matrix4f initTranslation(float x, float y, float z)
    {
        Matrix4f matrix = initIdentity();
        matrix.set(0, 3, x);
        matrix.set(1, 3, y);
        matrix.set(2, 3, z);
        return matrix;
    }
    /**
     * 
     * @param halfWidth representing half of the width of the screen.
     * @param halfHeight representing half the height of the screen.
     * @return 
     */
    public Matrix4f initScreenSpaceTransform(float halfWidth, float halfHeight)
    {
        Matrix4f matrix = new Matrix4f();
        matrix.set(0, 0, halfWidth);
        matrix.set(0, 1, 0);
        matrix.set(0, 2, 0);
        matrix.set(0, 3, halfWidth - 0.5f);//0.5f is some work around to make sure things dont go out of bounds
        matrix.set(1, 0, 0);
        matrix.set(1, 1, -halfHeight);
        matrix.set(1, 2, 0);
        matrix.set(1, 3, halfHeight - 0.5f);
        matrix.set(2, 0, 0);
        matrix.set(2, 1, 0);
        matrix.set(2, 2, 1);
        matrix.set(2, 3, 0);
        matrix.set(3, 0, 0);
        matrix.set(3, 1, 0);
        matrix.set(3, 2, 0);
        matrix.set(3, 3, 1);
        return matrix;
    }
    /**
     * 
     * @param x
     * @param y
     * @param z
     * @param angle the amount of rotation, in radians.
     * @return 
     */
    public Matrix4f initRotation(float x, float y, float z, float angle)
    {
        float sin = (float) Math.sin(angle);
        float cos = (float) Math.cos(angle);
        Matrix4f matrix = new Matrix4f();
        matrix.set(0, 0, cos + x * x * (1 - cos));
        matrix.set(0, 1, x * y * (1 - cos) - z * sin);
        matrix.set(0, 2, x * z * (1 - cos) + y * sin);
        matrix.set(0, 3, 0);
        matrix.set(1, 0, y * x * (1 - cos) + z * sin);
        matrix.set(1, 1, cos + y * y * (1 - cos));
        matrix.set(1, 2, y * z * (1 - cos) - x * sin);
        matrix.set(1, 3, 0);
        matrix.set(2, 0, z * x * (1 - cos) - y * sin);
        matrix.set(2, 1, z * y * (1 - cos) + x * sin);
        matrix.set(2, 2, cos + z * z * (1 - cos));
        matrix.set(2, 3, 0);
        matrix.set(3, 0, 0);
        matrix.set(3, 1, 0);
        matrix.set(3, 2, 0);
        matrix.set(3, 3, 1);
        return matrix;
    }
    /**
     * TODO
     * 
     * @param x
     * @param y
     * @param z
     * @return 
     */
    public Matrix4f initRotation(float x, float y, float z)
    {
        Matrix4f rx = initIdentity();
        Matrix4f ry = initIdentity();
        Matrix4f rz = initIdentity();
        rz.set(0, 0, (float) Math.cos(z));
        rz.set(0, 1, -(float) Math.sin(z));
        rz.set(1, 0, (float) Math.sin(z));
        rz.set(1, 1, (float) Math.cos(z));
        
        rx.set(1, 1, (float) Math.cos(x));
        rx.set(1, 2, -(float) Math.sin(x));
        rx.set(2, 1, (float) Math.sin(x));
        rx.set(2, 2, (float) Math.cos(x));
        
        ry.set(0, 0, (float) Math.cos(y));
        ry.set(0, 2, -(float) Math.sin(y));
        ry.set(2, 0, (float) Math.sin(y));
        ry.set(2, 2, (float) Math.cos(y));

        return rz.mulitply(ry.mulitply(rx));
    }
    /**
     * 
     * @param x
     * @param y
     * @param z
     * @return 
     */
    public Matrix4f initScale(float x, float y, float z)
    {
        Matrix4f matrix = new Matrix4f();
        matrix.set(0, 0, x);
        matrix.set(1, 1, y);
        matrix.set(2, 2, z);
        return matrix;
    }
    /**
     * 
     * @param fov
     * @param aspectRatio
     * @param zNear
     * @param zFar
     * @return 
     */
    public Matrix4f initPerspective(float fov, float aspectRatio, float zNear, float zFar)
    {
        Matrix4f matrix = new Matrix4f();
        float tanHalfFOV = (float) Math.tan(fov / 2);
        float zRange = zNear - zFar;
        matrix.set(0, 0, 1.0f / (tanHalfFOV * aspectRatio));
        matrix.set(0, 1, 0);
        matrix.set(0, 2, 0);
        matrix.set(0, 3, 0);
        matrix.set(1, 0, 0);
        matrix.set(1, 1, 1.0f / tanHalfFOV);
        matrix.set(1, 2, 0);
        matrix.set(1, 3, 0);
        matrix.set(2, 0, 0);
        matrix.set(2, 1, 0);
        matrix.set(2, 2, (-zNear - zFar) / zRange);
        matrix.set(2, 3, 2 * zFar * zNear / zRange);
        matrix.set(3, 0, 0);
        matrix.set(3, 1, 0);
        matrix.set(3, 2, 1);
        matrix.set(3, 3, 0);
        return matrix;
    }
    /**
     * Makes a matrix that transforms polyogons in a orthographic fashion.
     * TODO lessen the amount of parameters, 6 is too much!
     * @param left
     * @param right
     * @param bottom
     * @param top
     * @param near
     * @param far
     * @return 
     */
    public Matrix4f initOrthographic(float left, float right, float bottom, float top, float near, float far)
    {
        float width = right - left;
        float height = top - bottom;
        float depth = far - near;
        Matrix4f matrix = initIdentity();
        matrix.set(0, 0, 2 / width);
        matrix.set(0, 3, -(right + left) / width);
        matrix.set(1, 1, 2 / height);
        matrix.set(1, 3, -(top + bottom) / height);
        matrix.set(2, 2, -2 / depth);
        matrix.set(2, 3, -(far + near) / depth);
        return matrix;
    }
    /**
     * 
     * @param forward
     * @param up
     * @return 
     */
    public Matrix4f initRotation(Vector4f forward, Vector4f up)
    {
        Vector4f f = forward.normalized();

        Vector4f r = up.normalized();
        r = r.crossProduct(f);

        Vector4f u = f.crossProduct(r);

        return initRotation(f, u, r);
    }
    
    /**
     * 
     * @param forward
     * @param up
     * @param right
     * @return 
     */
    public Matrix4f initRotation(final Vector4f forward, final Vector4f up, final Vector4f right)
    {
        //Vector4f f = forward;
        //Vector4f r = right;
        //Vector4f u = up;
        final Vector4f[] vector = new Vector4f[3];
        vector[0] = right; vector[1] = up; vector[2] = forward;
        Matrix4f matrix = initIdentity();
        for(int m = 0; m < 3; m++)
        {
            for(int n = 0; n < 3; n++)
            {
                switch(m)
                {
                case 0: matrix.set(n, m, vector[n].getX());
                    break;
                case 1: matrix.set(n, m, vector[n].getY());
                    break;
                case 2: matrix.set(n, m, vector[n].getZ());
                    break;
                }
            }
        }
        return matrix;
    }
}