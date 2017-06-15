package main.java.models.threedee;

/**
 * Represents a 4 dimensional vector consisting of 32-bit floating point numbers.
 * This object is immutable and every operation will return a new vector or a float number.
 * 
 * @author Elwin Slokker
 * @author Benny Bobaganoosh
 * @version 0.2
 */
public class Vector4f
{
    private final float x;
    private final float y;
    private final float z;
    private final float w;

    /**
     * Constructs a vector by providing all components.
     * 
     * @param x the x component of the vector.
     * @param y the y component of the vector.
     * @param z the z component of the vector.
     * @param w the w component of the vector.
     */
    public Vector4f(float x, float y, float z, float w)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    /**
     * Constructs a vector without specifying w, which will be set to 1.
     * 
     * @param x the x component of the vector.
     * @param y the y component of the vector.
     * @param z the z component of the vector.
     */
    public Vector4f(float x, float y, float z)
    {
        this(x, y, z, 1.0f);
    }

    /**
     * @return the length of the vector.
     */
    public float length()
    {
        return (float) Math.sqrt(x * x + y * y + z * z + w * w);
    }

    /**
     * @return the biggest component in this vector.
     */
    public float max()
    {
        return Math.max(Math.max(x, y), Math.max(z, w));
    }

    /**
     * 
     * @param r
     * @return 
     */
    public float dotProduct(Vector4f r)
    {
        return x * r.getX() + y * r.getY() + z * r.getZ() + w * r.getW();
    }

    /**
     * 
     * @param r
     * @return 
     */
    public Vector4f crossProduct(Vector4f r)
    {
        float x_ = y * r.getZ() - z * r.getY();
        float y_ = z * r.getX() - x * r.getZ();
        float z_ = x * r.getY() - y * r.getX();

        return new Vector4f(x_, y_, z_, 0);
    }

    /**
     * @return a new vector shortened or elongated so that {@code .length() = 0}.
     */
    public Vector4f normalized()
    {
        float length = length();
        return new Vector4f(x / length, y / length, z / length, w / length);
    }

    public Vector4f rotate(Vector4f axis, float angle)
    {
        float sinAngle = (float) Math.sin(-angle);
        float cosAngle = (float) Math.cos(-angle);

        return this.crossProduct(axis.multiply(sinAngle)).add((this.multiply(cosAngle)).add(axis.multiply(this.dotProduct(axis.multiply(1 - cosAngle))))); //Rotation on local Y
    }

    /**
     * Rotates this vector as a point according to the {@code rotation}.
     * q x V x q* = Vr
     * 
     * @param rotation a quaternion rotation.
     * @return 
     */
    public Vector4f rotate(Quaternion rotation)
    {
        Quaternion conjugate = rotation.conjugate();
        Quaternion temp = rotation.multiply(this).multiply(conjugate);
        return new Vector4f(temp.getX(), temp.getY(), temp.getZ(), 1.0f);
    }

    public Vector4f linearlyInterpolate(Vector4f dest, float lerpFactor)
    {
        return dest.subtract(this).multiply(lerpFactor).add(this);
    }

    public Vector4f add(Vector4f r)
    {
        return new Vector4f(x + r.getX(), y + r.getY(), z + r.getZ(), w + r.getW());
    }

    public Vector4f add(float r)
    {
        return new Vector4f(x + r, y + r, z + r, w + r);
    }

    public Vector4f subtract(Vector4f r)
    {
        return new Vector4f(x - r.getX(), y - r.getY(), z - r.getZ(), w - r.getW());
    }

    public Vector4f subtract(float r)
    {
        return new Vector4f(x - r, y - r, z - r, w - r);
    }

    public Vector4f multiply(Vector4f r)
    {
        return new Vector4f(x * r.getX(), y * r.getY(), z * r.getZ(), w * r.getW());
    }

    public Vector4f multiply(float r)
    {
        return new Vector4f(x * r, y * r, z * r, w * r);
    }

    public Vector4f divide(Vector4f r)
    {
        return new Vector4f(x / r.getX(), y / r.getY(), z / r.getZ(), w / r.getW());
    }

    public Vector4f divide(float r)
    {
        return new Vector4f(x / r, y / r, z / r, w / r);
    }

    public Vector4f absoluteValue()
    {
        return new Vector4f(Math.abs(x), Math.abs(y), Math.abs(z), Math.abs(w));
    }

    @Override
    public String toString()
    {
        return "<" + x + ", " + y + ", " + z + ", " + w + ">";
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public float getZ()
    {
        return z;
    }

    public float getW()
    {
        return w;
    }

    /**
     * Compares any object to this vector to see if they are equal vectors.
     * 
     * @param other
     * @return 
     * @throws ClassCastException if {@code other} is not a Vector4f.
     */
    @Override
    public boolean equals(Object other) throws ClassCastException
    {
        Vector4f otherVector = (Vector4f) other;
        return this.equals(otherVector);
    }
    /**
     * 
     * @param r
     * @return 
     */
    public boolean equals(Vector4f r)
    {
        return x == r.getX() && y == r.getY() && z == r.getZ() && w == r.getW();
    }
}
