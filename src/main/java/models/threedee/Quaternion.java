package main.java.models.threedee;

import main.java.models.threedee.matrix.Matrix4fUtilities;
import main.java.models.threedee.matrix.Matrix4f;


/**
 * A class representing the mathematical quaternion.
 * This complex construction can represent any rotation.
 * 
 * @author Benny Bobaganoosh
 * @author Elwin Slokker
 * @version 0.2
 */
public class Quaternion
{
    private float m_x;
    private float m_y;
    private float m_z;
    private float m_w;

    /**
     * Makes a quaternion by providing all components.
     * 
     * @param x
     * @param y
     * @param z
     * @param w 
     */
    public Quaternion(float x, float y, float z, float w)
    {
        this.m_x = x;
        this.m_y = y;
        this.m_z = z;
        this.m_w = w;
    }

    /**
     * 
     * @param axis
     * @param angle 
     */
    public Quaternion(Vector4f axis, float angle)
    {
        float sinHalfAngle = (float) Math.sin(angle / 2);
        float cosHalfAngle = (float) Math.cos(angle / 2);

        this.m_x = axis.getX() * sinHalfAngle;
        this.m_y = axis.getY() * sinHalfAngle;
        this.m_z = axis.getZ() * sinHalfAngle;
        this.m_w = cosHalfAngle;
    }

    /**
     * Makes a quaternion from a rotation matrix.
     * From Ken Shoemake's "Quaternion Calculus and Fast Animation" article.
     * 
     * @param rot 
     */
    public Quaternion(Matrix4f rot)
    {
        float trace = rot.get(0, 0) + rot.get(1, 1) + rot.get(2, 2);

        if (trace > 0)
        {
            float s = 0.5f / (float) Math.sqrt(trace + 1.0f);
            m_w = 0.25f / s;
            m_x = (rot.get(1, 2) - rot.get(2, 1)) * s;
            m_y = (rot.get(2, 0) - rot.get(0, 2)) * s;
            m_z = (rot.get(0, 1) - rot.get(1, 0)) * s;
        } else if (rot.get(0, 0) > rot.get(1, 1) && rot.get(0, 0) > rot.get(2, 2))
        {
            float s = 2.0f * (float) Math.sqrt(1.0f + rot.get(0, 0) - rot.get(1, 1) - rot.get(2, 2));
            m_w = (rot.get(1, 2) - rot.get(2, 1)) / s;
            m_x = 0.25f * s;
            m_y = (rot.get(1, 0) + rot.get(0, 1)) / s;
            m_z = (rot.get(2, 0) + rot.get(0, 2)) / s;
        } else if (rot.get(1, 1) > rot.get(2, 2))
        {
            float s = 2.0f * (float) Math.sqrt(1.0f + rot.get(1, 1) - rot.get(0, 0) - rot.get(2, 2));
            m_w = (rot.get(2, 0) - rot.get(0, 2)) / s;
            m_x = (rot.get(1, 0) + rot.get(0, 1)) / s;
            m_y = 0.25f * s;
            m_z = (rot.get(2, 1) + rot.get(1, 2)) / s;
        } else
        {
            float s = 2.0f * (float) Math.sqrt(1.0f + rot.get(2, 2) - rot.get(0, 0) - rot.get(1, 1));
            m_w = (rot.get(0, 1) - rot.get(1, 0)) / s;
            m_x = (rot.get(2, 0) + rot.get(0, 2)) / s;
            m_y = (rot.get(1, 2) + rot.get(2, 1)) / s;
            m_z = 0.25f * s;
        }

        float length = (float) Math.sqrt(m_x * m_x + m_y * m_y + m_z * m_z + m_w * m_w);
        m_x /= length;
        m_y /= length;
        m_z /= length;
        m_w /= length;
    }
    /**
     * 
     * @return 
     */
    public float length()
    {
        return (float) Math.sqrt(m_x * m_x + m_y * m_y + m_z * m_z + m_w * m_w);
    }

    /**
     * 
     * @return 
     */
    public Quaternion normalized()
    {
        float length = length();

        return new Quaternion(m_x / length, m_y / length, m_z / length, m_w / length);
    }

    /**
     * @return a new {@code Quaternion} that is the conjugated.
     */
    public Quaternion conjugate()
    {
        return new Quaternion(-m_x, -m_y, -m_z, m_w);
    }

    public Quaternion multiply(float r)
    {
        return new Quaternion(m_x * r, m_y * r, m_z * r, m_w * r);
    }

    public Quaternion multiply(Quaternion r)
    {
        float w_ = m_w * r.getW() - m_x * r.getX() - m_y * r.getY() - m_z * r.getZ();
        float x_ = m_x * r.getW() + m_w * r.getX() + m_y * r.getZ() - m_z * r.getY();
        float y_ = m_y * r.getW() + m_w * r.getY() + m_z * r.getX() - m_x * r.getZ();
        float z_ = m_z * r.getW() + m_w * r.getZ() + m_x * r.getY() - m_y * r.getX();

        return new Quaternion(x_, y_, z_, w_);
    }

    public Quaternion multiply(Vector4f r)
    {
        float w_ = -m_x * r.getX() - m_y * r.getY() - m_z * r.getZ();
        float x_ = m_w * r.getX() + m_y * r.getZ() - m_z * r.getY();
        float y_ = m_w * r.getY() + m_z * r.getX() - m_x * r.getZ();
        float z_ = m_w * r.getZ() + m_x * r.getY() - m_y * r.getX();

        return new Quaternion(x_, y_, z_, w_);
    }

    public Quaternion subtract(Quaternion r)
    {
        return new Quaternion(m_x - r.getX(), m_y - r.getY(), m_z - r.getZ(), m_w - r.getW());
    }

    public Quaternion add(Quaternion r)
    {
        return new Quaternion(m_x + r.getX(), m_y + r.getY(), m_z + r.getZ(), m_w + r.getW());
    }

    public Matrix4f toRotationMatrix()
    {
        Vector4f forward = new Vector4f(2.0f * (m_x * m_z - m_w * m_y), 2.0f * (m_y * m_z + m_w * m_x), 1.0f - 2.0f * (m_x * m_x + m_y * m_y));
        Vector4f up = new Vector4f(2.0f * (m_x * m_y + m_w * m_z), 1.0f - 2.0f * (m_x * m_x + m_z * m_z), 2.0f * (m_y * m_z - m_w * m_x));
        Vector4f right = new Vector4f(1.0f - 2.0f * (m_y * m_y + m_z * m_z), 2.0f * (m_x * m_y - m_w * m_z), 2.0f * (m_x * m_z + m_w * m_y));

        return Matrix4fUtilities.initRotation(forward, up, right);
    }

    /**
     * 
     * @param r
     * @return a value between 0.0 and 1.0 that represents the angle between the
     * quaternions.
     */
    public float dotProduct(Quaternion r)
    {
        return m_x * r.getX() + m_y * r.getY() + m_z * r.getZ() + m_w * r.getW();
    }

    public Quaternion nLerp(Quaternion dest, float lerpFactor, boolean shortest)
    {
        Quaternion correctedDest = dest;

        //absoluting the quaternion?
        if (shortest && this.dotProduct(dest) < 0)
        {
            correctedDest = new Quaternion(-dest.getX(), -dest.getY(), -dest.getZ(), -dest.getW());
        }

        return correctedDest.subtract(this).multiply(lerpFactor).add(this).normalized();
    }

    public Quaternion sLerp(Quaternion dest, float lerpFactor, boolean shortest)
    {
        final float EPSILON = 1e3f;

        float cos = this.dotProduct(dest);
        Quaternion correctedDest = dest;

        if (shortest && cos < 0)
        {
            cos = -cos;
            correctedDest = new Quaternion(-dest.getX(), -dest.getY(), -dest.getZ(), -dest.getW());
        }

        if (Math.abs(cos) >= 1 - EPSILON)
        {
            return nLerp(correctedDest, lerpFactor, false);
        }

        float sin = (float) Math.sqrt(1.0f - cos * cos);
        float angle = (float) Math.atan2(sin, cos);
        float invSin = 1.0f / sin;

        float srcFactor = (float) Math.sin((1.0f - lerpFactor) * angle) * invSin;
        float destFactor = (float) Math.sin((lerpFactor) * angle) * invSin;

        return this.multiply(srcFactor).add(correctedDest.multiply(destFactor));
    }
    
    public Vector4f getForward()
    {
        return new Vector4f(0, 0, 1, 1).rotate(this);
    }

    public Vector4f getBack()
    {
        return new Vector4f(0, 0, -1, 1).rotate(this);
    }

    public Vector4f getUp()
    {
        return new Vector4f(0, 1, 0, 1).rotate(this);
    }

    public Vector4f getDown()
    {
        return new Vector4f(0, -1, 0, 1).rotate(this);
    }

    public Vector4f getRight()
    {
        return new Vector4f(1, 0, 0, 1).rotate(this);
    }

    public Vector4f getLeft()
    {
        return new Vector4f(-1, 0, 0, 1).rotate(this);
    }

    public float getX()
    {
        return m_x;
    }

    public float getY()
    {
        return m_y;
    }

    public float getZ()
    {
        return m_z;
    }

    public float getW()
    {
        return m_w;
    }

    public boolean equals(Quaternion r)
    {
        return m_x == r.getX() && m_y == r.getY() && m_z == r.getZ() && m_w == r.getW();
    }
}
